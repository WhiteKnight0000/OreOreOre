package com.whiteknight.oreoreore.game.elements.blockentity.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface ITickableEntity {
    public void serverTick(Level pLevel, BlockPos pPos, BlockState pState);
}
