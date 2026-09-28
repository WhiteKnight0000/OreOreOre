package com.whiteknight.oreoreore.datagen.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class AllRecipeProvider extends RecipeProvider {
    private OresRecipeProvider oresRecipeProvider;
    private RitualBasicRecipeProvider ritualBasicRecipeProvider;
    private UniqueRecipeProvider uniqueRecipeProvider;

    public AllRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
        oresRecipeProvider = new OresRecipeProvider(output, registries);
        ritualBasicRecipeProvider = new RitualBasicRecipeProvider(output, registries);
        uniqueRecipeProvider = new UniqueRecipeProvider(output, registries);
    }

    protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider registries) {
        oresRecipeProvider.buildRecipes(recipeOutput, registries);
        ritualBasicRecipeProvider.buildRecipes(recipeOutput, registries);
        uniqueRecipeProvider.buildRecipes(recipeOutput, registries);
    }
}
