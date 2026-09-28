package com.whiteknight.oreoreore.game.register;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.whiteknight.oreoreore.game.util.ModRegistries.registerBlock;

public class ModCraftedBlocks {

    public static final DeferredBlock<Block> MOON_GLOW_STONE;

    static{
        MOON_GLOW_STONE = registerBlock("moon_glow_stone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.PLING).strength(0.4F).sound(SoundType.GLASS).lightLevel((p_50872_) -> 8)));
    }

    public static void load(){}
}
