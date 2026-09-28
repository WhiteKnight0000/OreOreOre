package com.whiteknight.oreoreore.game.elements.item;

import com.whiteknight.oreoreore.game.event.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.List;

public class PaxelItem extends DiggerItem {

    public PaxelItem(Tier tier, Properties properties) {
        // 先ほど作成したカスタムタグを渡す
        super(tier, ModTags.Blocks.MINABLE_WITH_PAXEL, properties);
    }

    // 各種ツールの基本アクション（エンチャントや別Modからの互換性判定）を許可する
    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility toolAction) {
        return ItemAbilities.DEFAULT_AXE_ACTIONS.contains(toolAction) ||
                ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(toolAction) ||
                ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(toolAction);
    }

    // 右クリックアクション（皮剥き、道作りなど）の実装
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState blockstate = level.getBlockState(blockpos);

        // 1. 斧のアクション（皮剥き、銅の錆落としなど）を試行
        BlockState modifiedState = blockstate.getToolModifiedState(context, ItemAbilities.AXE_STRIP, false);
        if (modifiedState == null) {
            modifiedState = blockstate.getToolModifiedState(context, ItemAbilities.AXE_SCRAPE, false);
        }
        if (modifiedState == null) {
            modifiedState = blockstate.getToolModifiedState(context, ItemAbilities.AXE_WAX_OFF, false);
        }

        // 2. 斧のアクションに該当しなければ、シャベルのアクション（道作り）を試行
        if (modifiedState == null) {
            modifiedState = blockstate.getToolModifiedState(context, ItemAbilities.SHOVEL_FLATTEN, false);
        }

        // ブロックの変化が適用可能な場合
        if (modifiedState != null) {
            // サウンドの再生（皮剥きか道作りかで簡易的に分岐）
            level.playSound(player, blockpos,
                    modifiedState.is(BlockTags.DIRT) ? SoundEvents.SHOVEL_FLATTEN : SoundEvents.AXE_STRIP,
                    SoundSource.BLOCKS, 1.0F, 1.0F);

            if (!level.isClientSide) {
                level.setBlock(blockpos, modifiedState, 11);
                if (player != null) {
                    context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                }
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, ToolTipUtil.shiftToolTip(this, tooltipComponents), tooltipFlag);
    }
}
