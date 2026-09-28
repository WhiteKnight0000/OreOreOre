package com.whiteknight.oreoreore.datagen.recipes;

import com.whiteknight.oreoreore.game.register.ModOres;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class OresRecipeProvider extends ModRecipeProvider{
    public OresRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> moddedRegistries) {
        super(output, moddedRegistries);
    }

    /**
     * 鉱石、インゴット、ブロックなどの一連の金属レシピを自動生成する汎用メソッド
     *
     * @param recipeOutput レシピ出力用オブジェクト
     * @param metalName    金属の名前（レシピIDの重複回避やグループ名に使用。例: "silver"）
     * @param ore          通常鉱石
     * @param deepslateOre 深層岩鉱石
     * @param rawItem      原石
     * @param ingot        インゴット
     * @param nugget       ナゲット
     * @param block        金属ブロック
     * @param rawBlock     原石ブロック
     * @param smeltingXp   精錬時に得られる経験値
     */
    protected void generateMetalRecipes(
            RecipeOutput recipeOutput,
            String metalName,
            Supplier<? extends ItemLike> ore,
            Supplier<? extends ItemLike> deepslateOre,
            Supplier<? extends ItemLike> rawItem,
            Supplier<? extends ItemLike> ingot,
            Supplier<? extends ItemLike> nugget,
            Supplier<? extends ItemLike> block,
            Supplier<? extends ItemLike> rawBlock,
            float smeltingXp
    ) {
        ItemLike oreItem = ore.get();
        ItemLike deepslateOreItem = deepslateOre.get();
        ItemLike raw = rawItem.get();
        ItemLike ing = ingot.get();
        ItemLike nug = nugget.get();
        ItemLike blk = block.get();
        ItemLike rawBlk = rawBlock.get();

        // --- 1. 精錬・溶鉱 (Smelting & Blasting) ---
        List<ItemLike> smeltables = List.of(oreItem, deepslateOreItem, raw);
        oreSmelting(recipeOutput, smeltables, RecipeCategory.MISC, ing, smeltingXp, 200, metalName);
        oreBlasting(recipeOutput, smeltables, RecipeCategory.MISC, ing, smeltingXp, 100, metalName);

        // --- 2. ブロック ↔ インゴット ---
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blk)
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', ing)
                .unlockedBy("has_" + metalName + "_ingot", has(ing))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ing, 9)
                .requires(blk)
                .unlockedBy("has_" + metalName + "_block", has(blk))
                .save(recipeOutput, metalName + "_ingot_from_" + metalName + "_block");

        // --- 3. 原石ブロック ↔ 原石 ---
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, rawBlk)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', raw)
                .unlockedBy("has_raw_" + metalName, has(raw))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, raw, 9)
                .requires(rawBlk)
                .unlockedBy("has_raw_" + metalName + "_block", has(rawBlk))
                .save(recipeOutput, "raw_" + metalName + "_from_raw_" + metalName + "_block");

        // --- 4. インゴット ↔ ナゲット ---
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ing)
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .define('N', nug)
                .unlockedBy("has_" + metalName + "_nugget", has(nug))
                .save(recipeOutput, metalName + "_ingot_from_nuggets");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, nug, 9)
                .requires(ing)
                .unlockedBy("has_" + metalName + "_ingot", has(ing))
                .save(recipeOutput);
    }

    protected void generateGemRecipes(
            RecipeOutput recipeOutput,
            String gemName,
            List<ItemLike> ores,
            Supplier<? extends ItemLike> gem,
            Supplier<? extends ItemLike> block
    ){
        oreSmelting(recipeOutput, ores, RecipeCategory.MISC, gem.get(), 1.0f, 200, gemName);
        oreBlasting(recipeOutput, ores, RecipeCategory.MISC, gem.get(), 1.0f, 100, gemName);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', gem.get())
                .unlockedBy("has_" + gemName, has(gem.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, gem.get(), 9)
                .requires(block.get())
                .unlockedBy("has_" + gemName + "_block", has(block.get()))
                .save(recipeOutput, gemName + "_from_" + gemName + "_block");
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider registries) {
        generateMetalRecipes(recipeOutput, "silver", ModOres.SILVER_ORE, ModOres.DEEPSLATE_SILVER_ORE, ModOres.RAW_SILVER, ModOres.SILVER_INGOT, ModOres.SILVER_NUGGET, ModOres.SILVER_BLOCK, ModOres.RAW_SILVER_BLOCK, 0.7f);
        generateGemRecipes(recipeOutput, "aquamarine", List.of(ModOres.AQUAMARINE_ORE, ModOres.DEEPSLATE_AQUAMARINE_ORE), ModOres.AQUAMARINE, ModOres.AQUAMARINE_BLOCK);
        generateGemRecipes(recipeOutput, "garnet", List.of(ModOres.NETHER_GARNET_ORE), ModOres.GARNET, ModOres.GARNET_BLOCK);
        generateGemRecipes(recipeOutput, "onyx", List.of(ModOres.ONYX_ORE, ModOres.DEEPSLATE_ONYX_ORE), ModOres.ONYX, ModOres.ONYX_BLOCK);
        generateGemRecipes(recipeOutput, "topaz", List.of(ModOres.TOPAZ_ORE, ModOres.DEEPSLATE_TOPAZ_ORE), ModOres.TOPAZ, ModOres.TOPAZ_BLOCK);
        generateGemRecipes(recipeOutput, "opal", List.of(ModOres.OPAL_ORE, ModOres.DEEPSLATE_OPAL_ORE), ModOres.OPAL, ModOres.OPAL_BLOCK);
        generateGemRecipes(recipeOutput, "sapphire", List.of(ModOres.SAPPHIRE_ORE, ModOres.DEEPSLATE_SAPPHIRE_ORE), ModOres.SAPPHIRE, ModOres.SAPPHIRE_BLOCK);
        generateGemRecipes(recipeOutput, "spinel", List.of(ModOres.SPINEL_ORE, ModOres.DEEPSLATE_SPINEL_ORE), ModOres.SPINEL, ModOres.SPINEL_BLOCK);
        generateGemRecipes(recipeOutput, "peridot", List.of(ModOres.BASALT_PERIDOT_ORE, ModOres.BLACKSTONE_PERIDOT_ORE), ModOres.PERIDOT, ModOres.PERIDOT_BLOCK);
        generateGemRecipes(recipeOutput, "moonstone", List.of(ModOres.MOONSTONE_ORE, ModOres.DEEPSLATE_MOONSTONE_ORE), ModOres.MOONSTONE, ModOres.MOONSTONE_BLOCK);
        generateGemRecipes(recipeOutput, "agate", List.of(ModOres.AGATE_ORE, ModOres.DEEPSLATE_AGATE_ORE), ModOres.AGATE, ModOres.AGATE_BLOCK);
        generateMetalRecipes(recipeOutput, "mithril", ModOres.MITHRIL_ORE, ModOres.DEEPSLATE_MITHRIL_ORE, ModOres.RAW_MITHRIL, ModOres.MITHRIL_INGOT, ModOres.MITHRIL_NUGGET, ModOres.MITHRIL_BLOCK, ModOres.RAW_MITHRIL_BLOCK, 2.8f);
        generateGemRecipes(recipeOutput, "skythium", List.of(ModOres.SKYTHIUM_ORE, ModOres.DEEPSLATE_SKYTHIUM_ORE), ModOres.SKYTHIUM, ModOres.SKYTHIUM_BLOCK);
    }
}
