package com.whiteknight.oreoreore.game.util.recipe.modifier;

import com.whiteknight.oreoreore.game.util.recipe.RecipeModifier;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;

/**
 * 残留アイテムを可能にする
 * @param remainItem
 */
public record RemainItemModifier(Item remainItem) implements RecipeModifier {

    @Override
    public String getType() {
        return "remain_item";
    }

    @Override
    public void applyRemainingItems(CraftingInput input, NonNullList<ItemStack> remaining) {
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty() && stack.is(this.remainItem)) {
                ItemStack leftover = stack.copy();
                leftover.setCount(1);
                remaining.set(i, leftover);
            }
        }
    }

    public static final MapCodec<RemainItemModifier> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(RemainItemModifier::remainItem)
    ).apply(inst, RemainItemModifier::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, RemainItemModifier> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.registry(Registries.ITEM), RemainItemModifier::remainItem,
            RemainItemModifier::new
    );

    @Override
    public MapCodec<? extends RecipeModifier> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ? extends RecipeModifier> streamCodec() {
        return STREAM_CODEC;
    }
}
