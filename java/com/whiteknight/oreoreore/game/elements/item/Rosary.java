package com.whiteknight.oreoreore.game.elements.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class Rosary extends Item {
    private final double radius;

    public Rosary(Properties properties, double radius) {
        super(properties);
        this.radius = radius;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            AABB boundingBox = player.getBoundingBox().inflate(this.radius);
            List<Zombie> zombies = level.getEntitiesOfClass(Zombie.class, boundingBox);

            double radiusSqr = this.radius * this.radius;

            for (Zombie zombie : zombies) {
                if (player.distanceToSqr(zombie) <= radiusSqr) {
                    zombie.hurt(level.damageSources().playerAttack(player), 40.0F);
                }
            }

            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, ToolTipUtil.shiftToolTip(this, tooltipComponents), tooltipFlag);
    }
}
