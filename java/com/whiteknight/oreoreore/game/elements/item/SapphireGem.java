package com.whiteknight.oreoreore.game.elements.item;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SapphireGem extends Item {
    private static final double RANGE = 20.0;
    private static final double FORCE_MULTIPLIER = 0.7;

    public SapphireGem(Properties properties) {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 60;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if (!(livingEntity instanceof Player player)) {
            return;
        }
        if (!level.isClientSide()) {
            if (!level.isClientSide) {
                AABB boundingBox = player.getBoundingBox().inflate(RANGE);

                List<LivingEntity> entities = level.getEntitiesOfClass(
                        LivingEntity.class,
                        boundingBox,
                        entity -> entity != player
                );

                RandomSource random = level.getRandom();

                for (LivingEntity entity : entities) {
                    double angle1 = random.nextDouble() * Math.PI * 2.0;
                    double angle2 = random.nextDouble() * Math.PI * 2.0;

                    Vec3 randomForce = new Vec3(Math.cos(angle1) * Math.cos(angle2), Math.sin(angle1) * Math.cos(angle2),Math.sin(angle2)).scale(FORCE_MULTIPLIER);

                    entity.setDeltaMovement(entity.getDeltaMovement().add(randomForce));
                    entity.setOnGround(false);

                    entity.hasImpulse = true;
                }

                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.WIND_CHARGE_BURST, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeCharged) {
        if (!level.isClientSide() && entity instanceof Player player) {
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, ToolTipUtil.shiftToolTip(this, tooltipComponents), tooltipFlag);
    }
}
