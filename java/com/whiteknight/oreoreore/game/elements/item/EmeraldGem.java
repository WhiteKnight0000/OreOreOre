package com.whiteknight.oreoreore.game.elements.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.List;
import java.util.function.Consumer;

public class EmeraldGem extends Item {

    public EmeraldGem(Properties properties) {
        super(properties);
    }

    /**
     * 特定の座標のチャンクを非同期でロードし、完了後に地表のY座標を取得します。
     *
     * @param level    サーバーレベル (ワールド)
     * @param targetX  対象のX座標
     * @param targetZ  対象のZ座標
     * @param callback 取得したY座標を受け取る処理
     */
    public static void fetchSurfaceYAsync(ServerLevel level, int targetX, int targetZ, Consumer<Integer> callback) {
        ChunkPos chunkPos = new ChunkPos(new BlockPos(targetX, 0, targetZ));

        level.getChunkSource().getChunkFuture(chunkPos.x, chunkPos.z, ChunkStatus.FULL, true)
                .thenAcceptAsync(chunkResult -> {
                    int surfaceY = level.getHeight(Heightmap.Types.WORLD_SURFACE, targetX, targetZ);

                    // 結果をコールバックに返す
                    callback.accept(surfaceY);

                }, level.getServer());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            BlockPos playerPos = player.blockPosition();

            BlockPos villagePos = serverLevel.findNearestMapStructure(StructureTags.VILLAGE, playerPos, 100, false);

            player.getCooldowns().addCooldown(this, 100);
            if (villagePos != null) {
                fetchSurfaceYAsync(serverLevel, villagePos.getX(), villagePos.getZ(), (surfaceY) -> {
                    BlockPos targetPos = new BlockPos(villagePos.getX(), surfaceY+1, villagePos.getZ());

                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.teleportTo(serverLevel, targetPos.getX(), targetPos.getY(), targetPos.getZ(), serverPlayer.getYRot(), serverPlayer.getXRot());
                    }

                    WanderingTrader trader = EntityType.WANDERING_TRADER.create(serverLevel);
                    if (trader != null) {
                        trader.moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), 0.0F, 0.0F);
                        serverLevel.addFreshEntity(trader);
                    }

                    ItemStack emeralds = new ItemStack(Items.EMERALD, 10);
                    if (!player.getInventory().add(emeralds)) {
                        player.drop(emeralds, false);
                    }

                    if (!player.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }
                });

                return InteractionResultHolder.success(itemStack);
            } else {
                return InteractionResultHolder.fail(itemStack);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, ToolTipUtil.shiftToolTip(this, tooltipComponents), tooltipFlag);
    }
}
