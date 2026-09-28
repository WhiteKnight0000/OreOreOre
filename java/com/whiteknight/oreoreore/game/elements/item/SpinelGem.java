package com.whiteknight.oreoreore.game.elements.item;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.Comparator;
import java.util.List;

public class SpinelGem extends Item {
    private static final double SWAP_RADIUS = 16.0D;
    private static final int USE_INTERVAL = 20;

    public SpinelGem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            AABB searchBox = player.getBoundingBox().inflate(SWAP_RADIUS);

            List<Mob> nearbyMobs = level.getEntitiesOfClass(Mob.class, searchBox, mob -> mob.isAlive());

            if (nearbyMobs.isEmpty()) {
                return InteractionResultHolder.fail(itemstack);
            }

            Mob targetMob = nearbyMobs.stream()
                    .min(Comparator.comparingDouble(player::distanceToSqr))
                    .orElse(null);

            double playerX = player.getX();
            double playerY = player.getY();
            double playerZ = player.getZ();

            double mobX = targetMob.getX();
            double mobY = targetMob.getY();
            double mobZ = targetMob.getZ();

            targetMob.teleportTo(playerX, playerY, playerZ);
            player.teleportTo(mobX, mobY, mobZ);

            level.playSound(null, playerX, playerY, playerZ, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
            level.playSound(null, mobX, mobY, mobZ, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);

            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            player.getCooldowns().addCooldown(this, USE_INTERVAL);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, ToolTipUtil.shiftToolTip(this, tooltipComponents), tooltipFlag);
    }
}
