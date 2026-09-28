package com.whiteknight.oreoreore.game.register;

import com.whiteknight.oreoreore.game.elements.effect.SmallHealEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.whiteknight.oreoreore.game.util.ModRegistries.*;

public class ModPotions {
    //持続時間はtick(20tick = 1sec), 即時なら1tick
    //ポーションの色とパーティクルの色は連動

    public static final DeferredHolder<MobEffect, MobEffect> SMALL_HEAL_EFFECT =
            MOB_EFFECTS.register("small_heal",
                    () -> new SmallHealEffect(MobEffectCategory.BENEFICIAL, 0xFF5555));
    public static final DeferredHolder<Potion, Potion> HOLY_POTION =
            POTIONS.register("holy_potion",
                    () -> new Potion(new MobEffectInstance(SMALL_HEAL_EFFECT, 1, 0)));

    public static final DeferredHolder<Potion, Potion> WATER_BREATHING_POTION_12MIN =
            POTIONS.register("water_breathing_12min_potion",
                    () -> new Potion(new MobEffectInstance(MobEffects.WATER_BREATHING, 14400, 0)));

    public static void load(){}
}
