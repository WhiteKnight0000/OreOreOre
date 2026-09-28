package com.whiteknight.oreoreore.game.elements.blockentity.entity;

import com.whiteknight.oreoreore.game.elements.item.EarthFaith;
import com.whiteknight.oreoreore.game.register.ModRitualItems;
import com.whiteknight.oreoreore.game.elements.screen.EarthAltarMenu;
import com.whiteknight.oreoreore.game.util.ModDataMaps;
import com.whiteknight.oreoreore.game.util.ModTranslatable;
import com.whiteknight.oreoreore.game.util.component.ModDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class EarthAltarEntity extends BlockEntity implements IDroppableEntity, MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int INPUT_SIZE = 4;
    private static final int OUTPUT_INDEX = 4;

    public EarthAltarEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModRitualItems.EARTH_ALTAR_BE.get(), pPos, pBlockState);
    }

    @Override
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return ModTranslatable.EARTH_ALTAR.getComponent();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new EarthAltarMenu(i, inventory, this);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("itemHandler", itemHandler.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("itemHandler"));
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void process(){
        int total = 0;
        for (int i = 0; i < INPUT_SIZE; i++) {
            var stack = itemHandler.getStackInSlot(i);
            if (stack.getItemHolder().getData(ModDataMaps.ENERGY_VALUE) instanceof Integer energy) {
                total += stack.getCount() * energy;
            }
        }
        var stackOut = itemHandler.getStackInSlot(OUTPUT_INDEX);
        if(stackOut.isEmpty()){
            return;
        }
        if(stackOut.getItem() instanceof EarthFaith faith){
            var modifiedStackOut = stackOut.copy();
            int current = modifiedStackOut.getOrDefault(ModDataComponents.ENERGY,0);
            int add = Math.min(total, faith.getMaxCharge() - current);
            modifiedStackOut.set(ModDataComponents.ENERGY,current + add);
            itemHandler.setStackInSlot(OUTPUT_INDEX, modifiedStackOut);
            for (int i = 0; i < INPUT_SIZE; i++) {
                var stack = itemHandler.getStackInSlot(i);
                if (stack.getItemHolder().getData(ModDataMaps.ENERGY_VALUE) instanceof Integer energy) {
                    if(add <= 0){
                        break;
                    }
                    int useCount = Math.min(stack.getCount(), (int) Math.ceil((double) add/(double) energy));
                    add -= energy * useCount;
                    itemHandler.extractItem(i, useCount, false);
                }
            }
        }
    }
}
