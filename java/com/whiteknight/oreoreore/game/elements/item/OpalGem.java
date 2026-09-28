package com.whiteknight.oreoreore.game.elements.item;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * 50%で以下のいずれかの効果が働く
 * 1. 体力を半減し、ランダムな場所に転移させる
 * 2. 金リンゴの強化版の効果を受ける
 */
public class OpalGem extends Item {
    public OpalGem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (level.isClientSide) {
            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        }

        if (level.random.nextFloat() < 0.5F) {
            float currentHealth = player.getHealth();
            player.hurt(player.damageSources().magic(), currentHealth / 2.0F);

            double targetX = player.getX() + (level.random.nextDouble() - 0.5D) * 200.0D;
            double targetY = player.getY() + (level.random.nextInt(100) - 50);
            double targetZ = player.getZ() + (level.random.nextDouble() - 0.5D) * 200.0D;

            player.teleportTo(targetX, targetY, targetZ);
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 2));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 1));
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0));
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3));
        }

        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, ToolTipUtil.shiftToolTip(this, tooltipComponents), tooltipFlag);
    }
}
