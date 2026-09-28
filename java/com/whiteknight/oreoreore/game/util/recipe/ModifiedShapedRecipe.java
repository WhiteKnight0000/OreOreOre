package com.whiteknight.oreoreore.game.util.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class ModifiedShapedRecipe implements CraftingRecipe {
    private final ShapedRecipe compose;
    private final List<RecipeModifier> modifiers;

    public ModifiedShapedRecipe(ShapedRecipe compose, List<RecipeModifier> modifiers) {
        this.compose = compose;
        this.modifiers = modifiers;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (!this.compose.matches(input, level)) return false;
        return this.modifiers.stream().allMatch(mod -> mod.checkRequirements(input, level));
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider provider) {
        ItemStack result = this.compose.assemble(input, provider).copy();
        for (RecipeModifier modifier : this.modifiers) {
            result = modifier.modifyResult(result, input, provider);
        }
        return result;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remaining = this.compose.getRemainingItems(input);
        for (RecipeModifier modifier : this.modifiers) {
            modifier.applyRemainingItems(input, remaining);
        }
        return remaining;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return this.compose.canCraftInDimensions(width, height);
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        var result = this.compose.getResultItem(provider);
        for (RecipeModifier modifier : this.modifiers) {
            result = modifier.modifyResultItemOnRecipe(result);
        }
        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.MODIFIED_SHAPED_SERIALIZER.get();
    }

    @Override
    public CraftingBookCategory category() {
        return this.compose.category();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        var ingredients = this.compose.getIngredients();
        for (RecipeModifier modifier : this.modifiers) {
            ingredients = modifier.modifyIngredientOnRecipe(ingredients);
        }
        return ingredients;
    }

    public static class Serializer implements RecipeSerializer<ModifiedShapedRecipe> {
        public static final MapCodec<ModifiedShapedRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                RecipeSerializer.SHAPED_RECIPE.codec().codec().fieldOf("compose").forGetter(r -> r.compose),
                ModifierCodecs.DISPATCH_CODEC.listOf().fieldOf("modifiers").forGetter(r -> r.modifiers)
        ).apply(inst, ModifiedShapedRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ModifiedShapedRecipe> STREAM_CODEC = StreamCodec.composite(
                RecipeSerializer.SHAPED_RECIPE.streamCodec(), r -> r.compose,
                ModifierCodecs.STREAM_CODEC.apply(ByteBufCodecs.list()), r -> r.modifiers,
                ModifiedShapedRecipe::new
        );

        @Override
        public MapCodec<ModifiedShapedRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ModifiedShapedRecipe> streamCodec() {
            return STREAM_CODEC;
        }


    }
}
