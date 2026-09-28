package com.whiteknight.oreoreore.game.util.recipe.modifier;

//package com.whiteknight.oreoreore.game.util.recipe.modifier;

import com.whiteknight.oreoreore.game.register.ModUseItems;
import com.whiteknight.oreoreore.game.util.recipe.RecipeModifier;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

import java.util.Arrays;

/**
 * 任意のポーションを素材に追加できるようにする
 */
public record AnyPotionModifier() implements RecipeModifier {
    @Override
    public String getType() { return "any_potion"; }

    public static final MapCodec<AnyPotionModifier> CODEC = MapCodec.unit(new AnyPotionModifier());
    public static final StreamCodec<RegistryFriendlyByteBuf, AnyPotionModifier> STREAM_CODEC = StreamCodec.unit(new AnyPotionModifier());

    @Override
    public NonNullList<Ingredient> modifyIngredientOnRecipe(NonNullList<Ingredient> ingredients) {
        NonNullList<Ingredient> modifiedIngredients = NonNullList.create();
        for (var ing : ingredients){
            if(ing.isEmpty()){
                modifiedIngredients.add(ing);
                continue;
            }
            var modifiedItems = new ItemStack[ing.getItems().length];
            boolean allPotion = true;
            for (int i = 0; i < ing.getItems().length; i++) {
                modifiedItems[i] = ing.getItems()[i].copy();
                if(!modifiedItems[i].is(Tags.Items.POTIONS)){
                    allPotion = false;
                }
            }
            if(allPotion){
                modifiedIngredients.add(Ingredient.of(ModUseItems.INNER_ANY_POTION.toStack(1)));
                continue;
            }
            var modifiedIng = Ingredient.of(Arrays.stream(modifiedItems));
            modifiedIngredients.add(modifiedIng);
        }
        return modifiedIngredients;
    }

    @Override
    public MapCodec<? extends RecipeModifier> codec() { return CODEC; }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ? extends RecipeModifier> streamCodec() {
        return STREAM_CODEC;
    }
}

