package com.whiteknight.oreoreore.game.util.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public interface RecipeModifier {
    String getType();

    default boolean checkRequirements(CraftingInput input, Level level) {
        return true;
    }

    default ItemStack modifyResult(ItemStack result, CraftingInput input, HolderLookup.Provider provider) {
        return result;
    }

    default void applyRemainingItems(CraftingInput input, NonNullList<ItemStack> remaining) {}

    default NonNullList<Ingredient> modifyIngredientOnRecipe(NonNullList<Ingredient> ingredients){ return ingredients; }

    default ItemStack modifyResultItemOnRecipe(ItemStack stack){ return stack; }

    MapCodec<? extends RecipeModifier> codec();
    StreamCodec<RegistryFriendlyByteBuf, ? extends RecipeModifier> streamCodec();
}
