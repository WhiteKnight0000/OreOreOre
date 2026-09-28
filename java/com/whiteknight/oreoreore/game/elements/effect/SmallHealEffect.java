package com.whiteknight.oreoreore.game.elements.effect;

import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

/**
 * 1回復刻みの回復効果
 */
public class SmallHealEffect extends InstantenousMobEffect {

    public SmallHealEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entity, int amplifier, double health) {
        // healメソッドの引数はfloat型。1.0Fでハート0.5個分の回復になります。
        entity.heal(1.0F+amplifier);
    }
}
