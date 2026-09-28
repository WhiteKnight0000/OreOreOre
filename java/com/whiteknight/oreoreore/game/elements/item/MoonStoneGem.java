package com.whiteknight.oreoreore.game.elements.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * 昼夜を逆転させる
 */
public class MoonStoneGem extends Item {

    // 進める時間（20 ticks = 1秒 / 24,000 ticks = ゲーム内1日）
    private static final long TICKS_TO_ADVANCE = 12000L;
    private static final int USE_INTERVAL = 40;

    public MoonStoneGem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if(!(level instanceof ServerLevel serverLevel)){
            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        }

        long currentTime = serverLevel.getDayTime();
        serverLevel.setDayTime(currentTime + TICKS_TO_ADVANCE);

        serverLevel.playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.PORTAL_TRAVEL,
                SoundSource.PLAYERS,
                0.8F,
                1.5F
        );

        serverLevel.sendParticles(
                ParticleTypes.ENCHANT,
                player.getX(), player.getY() + 1.0, player.getZ(),
                30,
                0.5, 0.5, 0.5,
                0.2
        );

        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        player.getCooldowns().addCooldown(this, USE_INTERVAL);

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, ToolTipUtil.shiftToolTip(this, tooltipComponents), tooltipFlag);
    }
}
