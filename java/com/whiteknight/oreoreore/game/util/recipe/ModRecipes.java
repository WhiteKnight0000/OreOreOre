package com.whiteknight.oreoreore.game.util.recipe;

import com.whiteknight.oreoreore.game.OreOreOre;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, OreOreOre.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, OreOreOre.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ModifiedShapedRecipe>> MODIFIED_SHAPED_SERIALIZER =
            SERIALIZERS.register("modified_shaped", ModifiedShapedRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<ModifiedShapedRecipe>> MODIFIED_SHAPED_RECIPE =
            TYPES.register("modified_shaped", () -> new RecipeType<ModifiedShapedRecipe>() {
                @Override
                public String toString() {
                    return "modified_shaped";
                }
            });
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ModifiedShapelessRecipe>> MODIFIED_SHAPELESS_SERIALIZER =
            SERIALIZERS.register("modified_shapeless", ModifiedShapelessRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<ModifiedShapelessRecipe>> MODIFIED_SHAPELESS_RECIPE =
            TYPES.register("modified_shapeless", () -> new RecipeType<ModifiedShapelessRecipe>() {
                @Override
                public String toString() {
                    return "modified_shapeless";
                }
            });


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
