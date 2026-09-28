package com.whiteknight.oreoreore.datagen.recipes;

import com.whiteknight.oreoreore.game.event.ModTags;
import com.whiteknight.oreoreore.game.register.*;
import com.whiteknight.oreoreore.game.util.ModEnchantments;
import com.whiteknight.oreoreore.game.util.recipe.modifier.AnyPotionModifier;
import com.whiteknight.oreoreore.game.util.recipe.modifier.ChargeModifier;
import com.whiteknight.oreoreore.game.util.recipe.modifier.EnchantmentModifier;
import com.whiteknight.oreoreore.game.util.recipe.modifier.RemainItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.neoforge.common.Tags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UniqueRecipeProvider extends ModRecipeProvider{
    public UniqueRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> moddedRegistries) {
        super(output, moddedRegistries);
    }

    private void buildMagicGemRecipes(RecipeOutput recipeOutput){
        // === Diamond ===
        registerModifiedShapeless(
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModUseItems.MAGIC_GEM_OF_DIAMOND.get(), 1)
                        .requires(Items.DIAMOND)
                        .requires(ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("has_diamond", has(Items.DIAMOND)),
                List.of(new ChargeModifier(150)), "magic_gem_diamond", recipeOutput
        );

        // === Emerald ===
        registerModifiedShapeless(
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModUseItems.MAGIC_GEM_OF_EMERALD.get(), 1)
                        .requires(Items.EMERALD)
                        .requires(ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("has_emerald", has(Items.EMERALD)),
                List.of(new ChargeModifier(150)), "magic_gem_emerald", recipeOutput
        );

        // === Aquamarine ===
        registerModifiedShapeless(
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModUseItems.MAGIC_GEM_OF_AQUAMARINE.get(), 1)
                        .requires(ModOres.AQUAMARINE.get())
                        .requires(ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("has_aquamarine", has(ModOres.AQUAMARINE.get())),
                List.of(new ChargeModifier(150)), "magic_gem_aquamarine", recipeOutput
        );

        // === Garnet ===
        registerModifiedShapeless(
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModUseItems.MAGIC_GEM_OF_GARNET.get(), 1)
                        .requires(ModOres.GARNET.get())
                        .requires(ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("has_garnet", has(ModOres.GARNET.get())),
                List.of(new ChargeModifier(150)), "magic_gem_garnet", recipeOutput
        );

        // === Onyx ===
        registerModifiedShapeless(
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModUseItems.MAGIC_GEM_OF_ONYX.get(), 1)
                        .requires(ModOres.ONYX.get())
                        .requires(ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("has_onyx", has(ModOres.ONYX.get())),
                List.of(new ChargeModifier(150)), "magic_gem_onyx", recipeOutput
        );

        // === Topaz ===
        registerModifiedShapeless(
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModUseItems.MAGIC_GEM_OF_TOPAZ.get(), 1)
                        .requires(ModOres.TOPAZ.get())
                        .requires(ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("has_topaz", has(ModOres.TOPAZ.get())),
                List.of(new ChargeModifier(150)), "magic_gem_topaz", recipeOutput
        );

        // === Opal ===
        registerModifiedShapeless(
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModUseItems.MAGIC_GEM_OF_OPAL.get(), 1)
                        .requires(ModOres.OPAL.get())
                        .requires(ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("has_opal", has(ModOres.OPAL.get())),
                List.of(new ChargeModifier(150)), "magic_gem_opal", recipeOutput
        );

        // === Sapphire ===
        registerModifiedShapeless(
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModUseItems.MAGIC_GEM_OF_SAPPHIRE.get(), 1)
                        .requires(ModOres.SAPPHIRE.get())
                        .requires(ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("has_sapphire", has(ModOres.SAPPHIRE.get())),
                List.of(new ChargeModifier(150)), "magic_gem_sapphire", recipeOutput
        );

        // === Spinel ===
        registerModifiedShapeless(
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModUseItems.MAGIC_GEM_OF_SPINEL.get(), 1)
                        .requires(ModOres.SPINEL.get())
                        .requires(ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("has_spinel", has(ModOres.SPINEL.get())),
                List.of(new ChargeModifier(150)), "magic_gem_spinel", recipeOutput
        );

        // === Peridot ===
        registerModifiedShapeless(
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModUseItems.MAGIC_GEM_OF_PERIDOT.get(), 1)
                        .requires(ModOres.PERIDOT.get())
                        .requires(ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("has_peridot", has(ModOres.PERIDOT.get())),
                List.of(new ChargeModifier(150)), "magic_gem_peridot", recipeOutput
        );

        // === Moonstone ===
        registerModifiedShapeless(
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModUseItems.MAGIC_GEM_OF_MOONSTONE.get(), 1)
                        .requires(ModOres.MOONSTONE.get())
                        .requires(ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("has_moonstone", has(ModOres.MOONSTONE.get())),
                List.of(new ChargeModifier(150)), "magic_gem_moonstone", recipeOutput
        );

        // === Agate ===
        registerModifiedShapeless(
                ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModUseItems.MAGIC_GEM_OF_AGATE.get(), 1)
                        .requires(ModOres.AGATE.get())
                        .requires(ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("has_agate", has(ModOres.AGATE.get())),
                List.of(new ChargeModifier(150)), "magic_gem_agate", recipeOutput
        );
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider registries) {
        HolderLookup.RegistryLookup<Enchantment> enchantmentLookup = registries.lookupOrThrow(Registries.ENCHANTMENT);

        buildMagicGemRecipes(recipeOutput);

        var smite = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        smite.set(enchantmentLookup.getOrThrow(Enchantments.SMITE), 1);
        registerEnchantedShaped(
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModEquips.SILVER_SWORD.get())
                        .pattern(" A ")
                        .pattern(" A ")
                        .pattern(" B ")
                        .define('A', ModOres.SILVER_INGOT.get())
                        .define('B', Items.STICK)
                        .unlockedBy("has_ingots", has(ModOres.SILVER_INGOT.get())),
                smite.toImmutable(),"silver_sword",recipeOutput
        );
        registerEnergyShaped(
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModUseItems.HOLY_GRAIL.get())
                        .pattern("I I")
                        .pattern("ICI")
                        .pattern(" I ")
                        .define('I', ModOres.SILVER_INGOT.get())
                        .define('C', ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("has_silver_ingots", has(ModOres.SILVER_INGOT.get())),
                5,"holy_grail", recipeOutput);

        ItemStack holyPotion = PotionContents.createItemStack(Items.POTION, ModPotions.HOLY_POTION);
        registerModifiedShapeless(
                ShapelessRecipeBuilder.shapeless(RecipeCategory.BREWING, holyPotion)
                        .requires(Tags.Items.POTIONS)
                        .requires(ModUseItems.HOLY_GRAIL.get())
                        .unlockedBy("has_potion", has(Items.POTION)),
                List.of(new AnyPotionModifier(), new RemainItemModifier(ModUseItems.HOLY_GRAIL.get())),"holy_potion",recipeOutput
        );
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.BREWING, holyPotion)
//                .requires(Tags.Items.POTIONS)
//                .requires(ModUseItems.HOLY_GRAIL.get())
//                .unlockedBy("has_potion", has(Items.POTION)).save(recipeOutput);

        var rosaryMutableEnchantments = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        rosaryMutableEnchantments.set(enchantmentLookup.getOrThrow(Enchantments.SMITE), 3);
        var rosaryEnchantments = rosaryMutableEnchantments.toImmutable();
        registerModifiedShaped(
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModUseItems.ROSARY.get())
                        .pattern("III")
                        .pattern(" C ")
                        .pattern(" I ")
                        .define('I', ModOres.SILVER_NUGGET.get())
                        .define('C', ModTags.Items.CHARGE_ITEM)
                        .unlockedBy("has_silver", has(ModOres.SILVER_INGOT.get())),
                List.of(new ChargeModifier(50),new EnchantmentModifier(rosaryEnchantments)),"rosary", recipeOutput
        );

        generateArmorRecipes(recipeOutput, ModOres.MITHRIL_INGOT, ModEquips.MITHRIL_HELMET, ModEquips.MITHRIL_CHESTPLATE, ModEquips.MITHRIL_LEGGINGS, ModEquips.MITHRIL_BOOTS);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModUseItems.CRAZY_MUSHROOM_STEW.get())
                .requires(ModOres.AGATE.get())
                .requires(Items.MUSHROOM_STEW)
                .unlockedBy("has_agate", has(ModOres.AGATE.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModEquips.CRIMSON_AXE.get())
                .pattern("###")
                .pattern(" I ")
                .pattern(" I ")
                .define('#', ModOres.GARNET.get())
                .define('I', Items.STICK)
                .unlockedBy("has_garnet", has(ModOres.GARNET.get()))
                .save(recipeOutput);

        ItemStack fireAuraBook = new ItemStack(Items.ENCHANTED_BOOK);
        var fireAura = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
        fireAura.set(enchantmentLookup.getOrThrow(ModEnchantments.FIRE_AURA), 1);
        fireAuraBook.set(DataComponents.STORED_ENCHANTMENTS, fireAura.toImmutable());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, fireAuraBook)
                .requires(ModOres.GARNET.get())
                .requires(ModOres.MITHRIL_INGOT.get())
                .requires(Items.BOOK)
                .unlockedBy("has_garnet", has(ModOres.GARNET.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModEquips.GREEN_SHEARS.get())
                .requires(ModOres.PERIDOT.get())
                .requires(Items.SHEARS)
                .unlockedBy("has_peridot", has(ModOres.PERIDOT.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModEquips.ONYX_DAGGER.get())
                .pattern("O ")
                .pattern(" I")
                .define('O', ModOres.ONYX.get())
                .define('I', Items.STICK)
                .unlockedBy("has_onyx", has(ModOres.ONYX.get()))
                .save(recipeOutput);

//        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModEquips.DEFENCE_BIG_SHIELD.get())
//                .requires(ModOres.SAPPHIRE.get())
//                .requires(Items.SHIELD)
//                .unlockedBy("has_sapphire", has(ModOres.SAPPHIRE.get()))
//                .save(recipeOutput);

        buildAxeRecipe(recipeOutput, ModEquips.TOPAZ_AXE.get(),ModOres.TOPAZ.get());
        buildShovelRecipe(recipeOutput, ModEquips.TOPAZ_SHOVEL.get(),ModOres.TOPAZ.get());
        buildPickaxeRecipe(recipeOutput, ModEquips.TOPAZ_PICKAXE.get(),ModOres.TOPAZ.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModEquips.TOPAZ_PAXEL.get())
                .pattern("ABC")
                .pattern(" # ")
                .pattern(" I ")
                .define('A', ModEquips.TOPAZ_AXE.get())
                .define('B', ModEquips.TOPAZ_SHOVEL.get())
                .define('C', ModEquips.TOPAZ_PICKAXE.get())
                .define('#', ModOres.MITHRIL_INGOT.get())
                .define('I', Items.STICK)
                .unlockedBy("has_mithril", has(ModOres.MITHRIL_INGOT.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModCraftedBlocks.MOON_GLOW_STONE.get())
                .pattern("ABA")
                .pattern("BAB")
                .pattern("ABA")
                .define('A', ModOres.MOONSTONE.get())
                .define('B', Items.GLOWSTONE_DUST)
                .unlockedBy("has_moonstone", has(ModOres.MOONSTONE.get()))
                .save(recipeOutput);

        buildBootsRecipe(recipeOutput, ModOres.SKYTHIUM.get(), ModEquips.SKY_WALKER.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModEquips.BLAST_JUMPER.get())
                .pattern("   ")
                .pattern("G G")
                .pattern("S S")
                .define('G', ModOres.GARNET.get())
                .define('S', ModOres.SKYTHIUM.get())
                .unlockedBy("has_skythium", has(ModOres.SKYTHIUM.get()))
                .save(recipeOutput);


    }
}
