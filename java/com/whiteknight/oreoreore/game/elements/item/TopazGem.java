package com.whiteknight.oreoreore.game.elements.item;

import com.whiteknight.oreoreore.game.OreOreOre;
import com.whiteknight.oreoreore.game.util.ModStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.List;
import java.util.Optional;

public class TopazGem extends Item {
    public TopazGem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            BlockPos targetPos = player.blockPosition().offset(new Vec3i(-2,0,-2));

            StructureTemplateManager templateManager = serverLevel.getStructureManager();

            ResourceLocation structureId = ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, ModStructures.DIRT_WALL.getPath());

            Optional<StructureTemplate> templateOpt = templateManager.get(structureId);

            if (templateOpt.isPresent()) {
                StructureTemplate template = templateOpt.get();

                StructurePlaceSettings settings = new StructurePlaceSettings()
                        .setMirror(Mirror.NONE)
                        .setRotation(Rotation.NONE)
                        .setIgnoreEntities(false);

                // 引数: ServerLevel, 開始座標, ピボット座標, 設定, RandomSource, ブロックフラグ
                template.placeInWorld(
                        serverLevel,
                        targetPos,
                        targetPos,
                        settings,
                        serverLevel.getRandom(),
                        Block.UPDATE_ALL
                );

                if (!player.isCreative()) {
                    itemstack.shrink(1);
                }

                return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
            }
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, ToolTipUtil.shiftToolTip(this, tooltipComponents), tooltipFlag);
    }
}
