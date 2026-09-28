package com.whiteknight.oreoreore.game.util.recipe.modifier;

import com.whiteknight.oreoreore.game.event.ModTags;
import com.whiteknight.oreoreore.game.util.component.ModDataComponents;
import com.whiteknight.oreoreore.game.util.recipe.RecipeModifier;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import java.util.Arrays;

/**
 * エネルギー要求をレシピに追加する
 * @param chargeCost
 */
public record ChargeModifier(int chargeCost) implements RecipeModifier {
    @Override
    public String getType() { return "charge"; }

    @Override
    public boolean checkRequirements(CraftingInput input, Level level) {
        // ChargeShapedRecipeの条件判定と同等の処理
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.is(ModTags.Items.CHARGE_ITEM)) {
                return stack.getOrDefault(ModDataComponents.ENERGY, 0) >= this.chargeCost;
            }
        }
        return false;
    }

    @Override
    public void applyRemainingItems(CraftingInput input, NonNullList<ItemStack> remaining) {
        // ChargeShapedRecipeの消費処理と同等の処理
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.is(ModTags.Items.CHARGE_ITEM)) {
                ItemStack leftover = stack.copy();
                leftover.setCount(1);
                int currentCharge = leftover.getOrDefault(ModDataComponents.ENERGY, 0);
                leftover.set(ModDataComponents.ENERGY, currentCharge - this.chargeCost);
                remaining.set(i, leftover);
            }
        }
    }

    @Override
    public NonNullList<Ingredient> modifyIngredientOnRecipe(NonNullList<Ingredient> ingredients) {
        NonNullList<Ingredient> modifiedIngredients = NonNullList.create();
        for (var ing : ingredients){
            if(ing.isEmpty()){
                modifiedIngredients.add(ing);
                continue;
            }
            var modifiedItems = new ItemStack[ing.getItems().length];
            for (int i = 0; i < ing.getItems().length; i++) {
                modifiedItems[i] = ing.getItems()[i].copy();
                if (modifiedItems[i].is(ModTags.Items.CHARGE_ITEM)) {
                    modifiedItems[i].set(ModDataComponents.ENERGY,this.chargeCost);
                }
            }
            var modifiedIng = Ingredient.of(Arrays.stream(modifiedItems));
            modifiedIngredients.add(modifiedIng);
        }
        return modifiedIngredients;
    }

    public static final MapCodec<ChargeModifier> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Codec.INT.fieldOf("charge_cost").forGetter(ChargeModifier::chargeCost)
    ).apply(inst, ChargeModifier::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, ChargeModifier> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, ChargeModifier::chargeCost,
            ChargeModifier::new
    );

    @Override
    public MapCodec<? extends RecipeModifier> codec() { return CODEC; }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ? extends RecipeModifier> streamCodec() {
        return STREAM_CODEC;
    }
}
