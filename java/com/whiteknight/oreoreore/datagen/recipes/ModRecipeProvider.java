package com.whiteknight.oreoreore.datagen.recipes;

import com.whiteknight.oreoreore.game.OreOreOre;
import com.whiteknight.oreoreore.game.register.*;
//import com.whiteknight.oreoreore.game.util.recipe.ChargeShapedRecipe;
//import com.whiteknight.oreoreore.game.util.recipe.EnchantedShapedRecipe;
import com.whiteknight.oreoreore.game.util.recipe.ModifiedShapedRecipe;
import com.whiteknight.oreoreore.game.util.recipe.ModifiedShapelessRecipe;
import com.whiteknight.oreoreore.game.util.recipe.RecipeModifier;
import com.whiteknight.oreoreore.game.util.recipe.modifier.ChargeModifier;
import com.whiteknight.oreoreore.game.util.recipe.modifier.EnchantmentModifier;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> moddedRegistries) {
        super(output, moddedRegistries);
    }

    /**
     * 特殊な修正を加えられた定形レシピを登録する
     * @param builder 修正部分を除いた定形レシピ
     * @param modifiers
     * @param recipeName
     * @param output
     */
    protected void registerModifiedShaped(ShapedRecipeBuilder builder, List<RecipeModifier> modifiers, String recipeName, RecipeOutput output){
        builder.save(new RecipeOutput() {
            @Override
            public void accept(ResourceLocation id, Recipe<?> recipe, AdvancementHolder advancement) {
                if (recipe instanceof ShapedRecipe shaped) {
                    var wrappedRecipe = new ModifiedShapedRecipe(shaped, modifiers);

                    output.accept(id, wrappedRecipe, advancement);
                }
            }

            @Override
            public void accept(ResourceLocation id, Recipe<?> recipe, AdvancementHolder advancement, ICondition... conditions) {
                if (recipe instanceof ShapedRecipe shaped) {
                    var wrappedRecipe = new ModifiedShapedRecipe(shaped, modifiers);

                    output.accept(id, wrappedRecipe, advancement, conditions);
                }
            }

            @Override
            public Advancement.Builder advancement() {
                return output.advancement();
            }
        }, ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, "modified_shaped/" + recipeName));
    }

    /**
     * 特殊な修正を加えられた不定形レシピを登録する
     * @param builder 修正部分を除いた定形レシピ
     * @param modifiers
     * @param recipeName
     * @param output
     */
    protected void registerModifiedShapeless(ShapelessRecipeBuilder builder, List<RecipeModifier> modifiers, String recipeName, RecipeOutput output){
        builder.save(new RecipeOutput() {
            @Override
            public void accept(ResourceLocation id, Recipe<?> recipe, AdvancementHolder advancement) {
                if (recipe instanceof ShapelessRecipe shapeless) {
                    var wrappedRecipe = new ModifiedShapelessRecipe(shapeless, modifiers);

                    output.accept(id, wrappedRecipe, advancement);
                }
            }

            @Override
            public void accept(ResourceLocation id, Recipe<?> recipe, AdvancementHolder advancement, ICondition... conditions) {
                if (recipe instanceof ShapelessRecipe shapeless) {
                    var wrappedRecipe = new ModifiedShapelessRecipe(shapeless, modifiers);

                    output.accept(id, wrappedRecipe, advancement, conditions);
                }
            }

            // 3. レシピ本解放条件のビルダー
            @Override
            public Advancement.Builder advancement() {
                return output.advancement();
            }
        }, ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, "modified_shapeless/" + recipeName));
    }

    protected void registerEnergyShaped(ShapedRecipeBuilder builder, int energyCost, String recipeName, RecipeOutput output){
        registerModifiedShaped(builder, List.of(new ChargeModifier(energyCost)),recipeName,output);
    }

    protected void registerEnchantedShaped(ShapedRecipeBuilder builder, ItemEnchantments enchantments, String recipeName, RecipeOutput output){
        registerModifiedShaped(builder, List.of(new EnchantmentModifier(enchantments)),recipeName,output);
    }



    protected void generateArmorRecipes(
            RecipeOutput output,
            Supplier<? extends ItemLike> materialSupplier,
            Supplier<? extends ItemLike> helmetSupplier,
            Supplier<? extends ItemLike> chestplateSupplier,
            Supplier<? extends ItemLike> leggingsSupplier,
            Supplier<? extends ItemLike> bootsSupplier) {

        // 素材の取得（素材は必須と仮定）
        ItemLike material = materialSupplier.get();

        // ヘルメットのレシピ
        if (helmetSupplier != null) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmetSupplier.get())
                    .pattern("XXX")
                    .pattern("X X")
                    .define('X', material)
                    .unlockedBy("has_material", has(material))
                    .save(output);
        }

        // チェストプレートのレシピ
        if (chestplateSupplier != null) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplateSupplier.get())
                    .pattern("X X")
                    .pattern("XXX")
                    .pattern("XXX")
                    .define('X', material)
                    .unlockedBy("has_material", has(material))
                    .save(output);
        }

        // レギンスのレシピ
        if (leggingsSupplier != null) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggingsSupplier.get())
                    .pattern("XXX")
                    .pattern("X X")
                    .pattern("X X")
                    .define('X', material)
                    .unlockedBy("has_material", has(material))
                    .save(output);
        }

        // ブーツのレシピ
        if (bootsSupplier != null) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, bootsSupplier.get())
                    .pattern("X X")
                    .pattern("X X")
                    .define('X', material)
                    .unlockedBy("has_material", has(material))
                    .save(output);
        }
    }

    /**
     * ピッケルのレシピを生成
     */
    protected void buildPickaxeRecipe(RecipeOutput output, ItemLike result, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', material)
                .define('#', Items.STICK) // ※必要に応じて Tags.Items.RODS_WOODEN 等に変更してください
                .unlockedBy("has_material", has(material))
                .save(output);
    }

    /**
     * 斧のレシピを生成
     */
    protected void buildAxeRecipe(RecipeOutput output, ItemLike result, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("XX ")
                .pattern("X# ")
                .pattern(" # ")
                .define('X', material)
                .define('#', Items.STICK)
                .unlockedBy("has_material", has(material))
                .save(output);
    }

    /**
     * シャベルのレシピを生成
     */
    protected void buildShovelRecipe(RecipeOutput output, ItemLike result, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern(" X ")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', material)
                .define('#', Items.STICK)
                .unlockedBy("has_material", has(material))
                .save(output);
    }

    /**
     * クワのレシピを生成
     */
    protected void buildHoeRecipe(RecipeOutput output, ItemLike result, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .pattern("XX ")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', material)
                .define('#', Items.STICK)
                .unlockedBy("has_material", has(material))
                .save(output);
    }

    /**
     * 剣のレシピを生成 (カテゴリがCOMBATになります)
     */
    protected void buildSwordRecipe(RecipeOutput output, ItemLike result, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern(" X ")
                .pattern(" X ")
                .pattern(" # ")
                .define('X', material)
                .define('#', Items.STICK)
                .unlockedBy("has_material", has(material))
                .save(output);
    }

    /**
     * ヘルメットの汎用レシピ追加メソッド
     * @param output レシピの出力先
     * @param material 素材となるアイテム
     * @param result 完成品のヘルメットアイテム
     */
    protected void buildHelmetRecipe(RecipeOutput output, ItemLike material, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("XXX")
                .pattern("X X")
                .define('X', material)
                .unlockedBy(getHasName(material), has(material))
                .save(output);
    }

    /**
     * チェストプレートの汎用レシピ追加メソッド
     * @param output レシピの出力先
     * @param material 素材となるアイテム
     * @param result 完成品のチェストプレートアイテム
     */
    protected void buildChestplateRecipe(RecipeOutput output, ItemLike material, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', material)
                .unlockedBy(getHasName(material), has(material))
                .save(output);
    }

    /**
     * レギンスの汎用レシピ追加メソッド
     * @param output レシピの出力先
     * @param material 素材となるアイテム
     * @param result 完成品のレギンスアイテム
     */
    protected void buildLeggingsRecipe(RecipeOutput output, ItemLike material, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .define('X', material)
                .unlockedBy(getHasName(material), has(material))
                .save(output);
    }

    /**
     * ブーツの汎用レシピ追加メソッド
     * @param output レシピの出力先
     * @param material 素材となるアイテム
     * @param result 完成品のブーツアイテム
     */
    protected void buildBootsRecipe(RecipeOutput output, ItemLike material, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("X X")
                .pattern("X X")
                .define('X', material)
                .unlockedBy(getHasName(material), has(material))
                .save(output);
    }
}
