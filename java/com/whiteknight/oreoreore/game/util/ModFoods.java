package com.whiteknight.oreoreore.game.util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties CRAZY_MUSHROOM_STEW = new FoodProperties.Builder()
            .nutrition(10)
            .saturationModifier(1F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200, 0), 1F)
            .build();
}
