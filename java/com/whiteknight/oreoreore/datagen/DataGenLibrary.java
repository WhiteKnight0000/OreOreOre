package com.whiteknight.oreoreore.datagen;

import com.whiteknight.oreoreore.game.util.ModRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.List;

public class DataGenLibrary {
    public static List<DeferredHolder<Block, ? extends Block>> getKnownBlocks(){
        var res = new ArrayList<DeferredHolder<Block, ? extends Block>>();
        res.addAll(ModRegistries.BLOCKS.getEntries());
        return res;
    }

    //BlockItemを含む全アイテム
    public static List<DeferredHolder<Item, ? extends Item>> getKnownItems(){
        var res = new ArrayList<DeferredHolder<Item, ? extends Item>>();
        res.addAll(ModRegistries.ITEMS.getEntries());
        return res;
    }
}
