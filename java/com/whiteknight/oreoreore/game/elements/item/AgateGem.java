package com.whiteknight.oreoreore.game.elements.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

/**
 * 自身を含めた周囲にデバフを割り振る
 */
public class AgateGem extends Item {
    private static final double EFFECT_RADIUS = 40.0D;
    private static final int DURATION_TICKS = 45 * 20;


    public AgateGem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand){
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            AABB boundingBox = player.getBoundingBox().inflate(EFFECT_RADIUS);
            List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, boundingBox);

            for (LivingEntity target : targets) {
                target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, DURATION_TICKS, 0));
                target.addEffect(new MobEffectInstance(MobEffects.WITHER, DURATION_TICKS, 0));
                target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, DURATION_TICKS, 0));
            }
        }

        itemstack.shrink(1);

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, ToolTipUtil.shiftToolTip(this,tooltipComponents), tooltipFlag);
    }
}
