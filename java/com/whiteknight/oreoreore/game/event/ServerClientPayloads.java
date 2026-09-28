package com.whiteknight.oreoreore.game.event;

import com.whiteknight.oreoreore.game.OreOreOre;
import com.whiteknight.oreoreore.game.util.DoubleJumpPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

/**
 * サーバーとクライアント間での情報のやり取りが必要な場合の経路を登録する
 */
public class ServerClientPayloads {
    public static void registerPayloads(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(OreOreOre.MOD_ID);

        registrar.playToServer(
                DoubleJumpPayload.TYPE,
                DoubleJumpPayload.CODEC,
                ServerClientPayloads::handleDoubleJump
        );
    }

    private static void handleDoubleJump(final DoubleJumpPayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            player.fallDistance = 0.0F;
        });
    }
}
