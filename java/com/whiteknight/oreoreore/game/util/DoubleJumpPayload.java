package com.whiteknight.oreoreore.game.util;

import com.whiteknight.oreoreore.game.OreOreOre;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record DoubleJumpPayload() implements CustomPacketPayload {
    public static final Type<DoubleJumpPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, "double_jump"));
    public static final StreamCodec<ByteBuf, DoubleJumpPayload> CODEC = StreamCodec.unit(new DoubleJumpPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
