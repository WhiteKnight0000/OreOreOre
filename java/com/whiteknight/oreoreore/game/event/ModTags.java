package com.whiteknight.oreoreore.game.event;

import com.whiteknight.oreoreore.game.OreOreOre;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> MINABLE_WITH_PAXEL = createTag("minable_with_paxel");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> CHARGE_ITEM = createTag("charge_item");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, name));
        }
    }
}