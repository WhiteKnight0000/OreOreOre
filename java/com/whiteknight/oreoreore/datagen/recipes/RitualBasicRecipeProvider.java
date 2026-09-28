package com.whiteknight.oreoreore.datagen.recipes;

import com.whiteknight.oreoreore.game.OreOreOre;
import com.whiteknight.oreoreore.game.event.ModTags;
import com.whiteknight.oreoreore.game.register.ModRitualItems;
import com.whiteknight.oreoreore.game.util.component.ModDataComponents;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class RitualBasicRecipeProvider extends ModRecipeProvider{
    public RitualBasicRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> moddedRegistries) {
        super(output, moddedRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider registries) {
        registerEnergyShaped(
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GOLD_INGOT)
                        .pattern(" I ")
                        .pattern("ICI")
                        .pattern(" I ")
                        .define('I', Items.IRON_NUGGET)
                        .define('C', ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("unconditional", CriteriaTriggers.TICK.createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty()))),
                10,"alchemi", recipeOutput);
        ItemStack res_fresh_green = new ItemStack(ModRitualItems.FRESH_GREEN_FAITH.get());
        res_fresh_green.set(ModDataComponents.ENERGY,1);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, res_fresh_green)
                .pattern(" B ")
                .pattern("BAB")
                .pattern(" B ")
                .define('B', ItemTags.LOGS)
                .define('A', Tags.Items.INGOTS)
                .unlockedBy("unconditional", CriteriaTriggers.TICK.createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty()))).save(recipeOutput);
        ItemStack res_nature = new ItemStack(ModRitualItems.NATURE_FAITH.get());
        res_nature.set(ModDataComponents.ENERGY,10);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, res_nature)
                .pattern(" B ")
                .pattern("BAB")
                .pattern(" B ")
                .define('B', Items.GOLD_INGOT)
                .define('A', Items.APPLE)
                .unlockedBy("unconditional", CriteriaTriggers.TICK.createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty()))).save(recipeOutput, ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, "duprecipes/nature_1"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, res_nature)
                .pattern(" B ")
                .pattern("BAB")
                .pattern(" B ")
                .define('B', Items.DIAMOND)
                .define('A', Items.WHEAT)
                .unlockedBy("unconditional", CriteriaTriggers.TICK.createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty()))).save(recipeOutput, ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, "duprecipes/nature_2"));
        ItemStack res_jade = new ItemStack(ModRitualItems.JADE_FAITH.get());
        res_jade.set(ModDataComponents.ENERGY,10);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, res_jade)
                .pattern(" D ")
                .pattern("CAE")
                .pattern(" B ")
                .define('A', Items.HEART_OF_THE_SEA)
                .define('B', Items.EMERALD)
                .define('C', Items.AMETHYST_SHARD)
                .define('D', Items.DIAMOND)
                .define('E', Items.LAPIS_LAZULI)
                .unlockedBy("unconditional", CriteriaTriggers.TICK.createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty())))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModRitualItems.EARTH_WAND)
                .pattern("C")
                .pattern("I")
                .pattern("I")
                .define('I', Items.STICK)
                .define('C', Tags.Items.NUGGETS)
                .unlockedBy("unconditional", CriteriaTriggers.TICK.createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty())))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModRitualItems.EARTH_ALTAR)
                .pattern(" C ")
                .pattern("C C")
                .pattern(" C ")
                .define('C', Tags.Items.NUGGETS)
                .unlockedBy("unconditional", CriteriaTriggers.TICK.createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty())))
                .save(recipeOutput);
    }
}
