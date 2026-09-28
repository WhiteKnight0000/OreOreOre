package com.whiteknight.oreoreore.game.util.recipe.modifier;

import com.whiteknight.oreoreore.game.util.recipe.RecipeModifier;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.enchantment.ItemEnchantments;

/**
 * 完成品にエンチャントを自動付与できるようにする
 * @param enchantments
 */
public record EnchantmentModifier(ItemEnchantments enchantments) implements RecipeModifier {
    @Override
    public String getType() { return "enchant"; }

    @Override
    public ItemStack modifyResult(ItemStack result, CraftingInput input, HolderLookup.Provider provider) {
        if (this.enchantments.isEmpty()) return result;

        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(
                result.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY)
        );
        this.enchantments.entrySet().forEach(entry -> mutable.set(entry.getKey(), entry.getIntValue()));
        result.set(DataComponents.ENCHANTMENTS, mutable.toImmutable());

        return result;
    }

    @Override
    public ItemStack modifyResultItemOnRecipe(ItemStack stack) {
        if (this.enchantments.isEmpty()) return stack;

        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(
                stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY)
        );
        this.enchantments.entrySet().forEach(entry -> mutable.set(entry.getKey(), entry.getIntValue()));
        stack.set(DataComponents.ENCHANTMENTS, mutable.toImmutable());

        return stack;
    }

    public static final MapCodec<EnchantmentModifier> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            ItemEnchantments.CODEC.fieldOf("enchantments").forGetter(EnchantmentModifier::enchantments)
    ).apply(inst, EnchantmentModifier::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, EnchantmentModifier> STREAM_CODEC = StreamCodec.composite(
            ItemEnchantments.STREAM_CODEC, EnchantmentModifier::enchantments,
            EnchantmentModifier::new
    );

    @Override
    public MapCodec<? extends RecipeModifier> codec() { return CODEC; }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ? extends RecipeModifier> streamCodec() {
        return STREAM_CODEC;
    }
}
