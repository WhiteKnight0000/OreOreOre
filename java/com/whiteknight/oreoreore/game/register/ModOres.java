package com.whiteknight.oreoreore.game.register;

import com.whiteknight.oreoreore.game.elements.item.PillarExperienceBlock;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.whiteknight.oreoreore.game.util.ModRegistries.*;

public class ModOres {
    // === Silver ===
    public static final DeferredBlock<Block> SILVER_ORE;
    public static final DeferredBlock<Block> DEEPSLATE_SILVER_ORE;
    public static final DeferredBlock<Block> SILVER_BLOCK;
    public static final DeferredBlock<Block> RAW_SILVER_BLOCK;
    public static final DeferredItem<Item> RAW_SILVER;
    public static final DeferredItem<Item> SILVER_INGOT;
    public static final DeferredItem<Item> SILVER_NUGGET;

    // === Aquamarine ===
    public static final DeferredBlock<Block> AQUAMARINE_ORE;
    public static final DeferredBlock<Block> DEEPSLATE_AQUAMARINE_ORE;
    public static final DeferredBlock<Block> AQUAMARINE_BLOCK;
    public static final DeferredItem<Item> AQUAMARINE;

    // === Garnet ===
    public static final DeferredBlock<Block> NETHER_GARNET_ORE;
    public static final DeferredBlock<Block> GARNET_BLOCK;
    public static final DeferredItem<Item> GARNET;

    // === Onyx ===
    public static final DeferredBlock<Block> ONYX_ORE;
    public static final DeferredBlock<Block> DEEPSLATE_ONYX_ORE;
    public static final DeferredBlock<Block> ONYX_BLOCK;
    public static final DeferredItem<Item> ONYX;

    // === Topaz ===
    public static final DeferredBlock<Block> TOPAZ_ORE;
    public static final DeferredBlock<Block> DEEPSLATE_TOPAZ_ORE;
    public static final DeferredBlock<Block> TOPAZ_BLOCK;
    public static final DeferredItem<Item> TOPAZ;

    // === Opal ===
    public static final DeferredBlock<Block> OPAL_ORE;
    public static final DeferredBlock<Block> DEEPSLATE_OPAL_ORE;
    public static final DeferredBlock<Block> OPAL_BLOCK;
    public static final DeferredItem<Item> OPAL;

    // === Sapphire ===
    public static final DeferredBlock<Block> SAPPHIRE_ORE;
    public static final DeferredBlock<Block> DEEPSLATE_SAPPHIRE_ORE;
    public static final DeferredBlock<Block> SAPPHIRE_BLOCK;
    public static final DeferredItem<Item> SAPPHIRE;

    // === Spinel ===
    public static final DeferredBlock<Block> SPINEL_ORE;
    public static final DeferredBlock<Block> DEEPSLATE_SPINEL_ORE;
    public static final DeferredBlock<Block> SPINEL_BLOCK;
    public static final DeferredItem<Item> SPINEL;

    // === Peridot ===
    public static final DeferredBlock<Block> BASALT_PERIDOT_ORE;
    public static final DeferredBlock<Block> BLACKSTONE_PERIDOT_ORE;
    public static final DeferredBlock<Block> PERIDOT_BLOCK;
    public static final DeferredItem<Item> PERIDOT;

    // === Moonstone ===
    public static final DeferredBlock<Block> MOONSTONE_ORE;
    public static final DeferredBlock<Block> DEEPSLATE_MOONSTONE_ORE;
    public static final DeferredBlock<Block> MOONSTONE_BLOCK;
    public static final DeferredItem<Item> MOONSTONE;

    // === Agate ===
    public static final DeferredBlock<Block> AGATE_ORE;
    public static final DeferredBlock<Block> DEEPSLATE_AGATE_ORE;
    public static final DeferredBlock<Block> AGATE_BLOCK;
    public static final DeferredItem<Item> AGATE;

    // === Mithril ===
    public static final DeferredBlock<Block> MITHRIL_ORE;
    public static final DeferredBlock<Block> DEEPSLATE_MITHRIL_ORE;
    public static final DeferredBlock<Block> MITHRIL_BLOCK;
    public static final DeferredBlock<Block> RAW_MITHRIL_BLOCK;
    public static final DeferredItem<Item> RAW_MITHRIL;
    public static final DeferredItem<Item> MITHRIL_INGOT;
    public static final DeferredItem<Item> MITHRIL_NUGGET;

    // === Pearl ===
    public static final DeferredItem<Item> PEARL;

    // === Skythium ===
    public static final DeferredBlock<Block> SKYTHIUM_ORE;
    public static final DeferredBlock<Block> DEEPSLATE_SKYTHIUM_ORE;
    public static final DeferredBlock<Block> SKYTHIUM_BLOCK;
    public static final DeferredItem<Item> SKYTHIUM;

    static {
        //silver
        SILVER_ORE = registerBlock("silver_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
        DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
        SILVER_BLOCK = registerBlock("silver_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
        RAW_SILVER_BLOCK = registerBlock("raw_silver_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)));
        RAW_SILVER = ITEMS.register("raw_silver", () -> new Item(new Item.Properties()));
        SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties()));
        SILVER_NUGGET = ITEMS.register("silver_nugget", () -> new Item(new Item.Properties()));

        // --- Aquamarine ---
        AQUAMARINE_ORE = registerBlock("aquamarine_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
        DEEPSLATE_AQUAMARINE_ORE = registerBlock("deepslate_aquamarine_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
        AQUAMARINE_BLOCK = registerBlock("aquamarine_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
        AQUAMARINE = ITEMS.register("aquamarine", () -> new Item(new Item.Properties()));

        // --- Garnet ---
        NETHER_GARNET_ORE = registerBlock("nether_garnet_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
        GARNET_BLOCK = registerBlock("garnet_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
        GARNET = ITEMS.register("garnet", () -> new Item(new Item.Properties()));

        // --- Onyx ---
        ONYX_ORE = registerBlock("onyx_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
        DEEPSLATE_ONYX_ORE = registerBlock("deepslate_onyx_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
        ONYX_BLOCK = registerBlock("onyx_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
        ONYX = ITEMS.register("onyx", () -> new Item(new Item.Properties()));

        // --- Topaz ---
        TOPAZ_ORE = registerBlock("topaz_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)));
        DEEPSLATE_TOPAZ_ORE = registerBlock("deepslate_topaz_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)));
        TOPAZ_BLOCK = registerBlock("topaz_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
        TOPAZ = ITEMS.register("topaz", () -> new Item(new Item.Properties()));

        // --- Opal ---
        OPAL_ORE = registerBlock("opal_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
        DEEPSLATE_OPAL_ORE = registerBlock("deepslate_opal_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
        OPAL_BLOCK = registerBlock("opal_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
        OPAL = ITEMS.register("opal", () -> new Item(new Item.Properties()));

        // --- Sapphire ---
        SAPPHIRE_ORE = registerBlock("sapphire_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
        DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
        SAPPHIRE_BLOCK = registerBlock("sapphire_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
        SAPPHIRE = ITEMS.register("sapphire", () -> new Item(new Item.Properties()));

        // --- Spinel ---
        SPINEL_ORE = registerBlock("spinel_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
        DEEPSLATE_SPINEL_ORE = registerBlock("deepslate_spinel_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
        SPINEL_BLOCK = registerBlock("spinel_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
        SPINEL = ITEMS.register("spinel", () -> new Item(new Item.Properties()));

        // --- Peridot ---
        BASALT_PERIDOT_ORE = registerBlock("basalt_peridot_ore", () -> new PillarExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
        BLACKSTONE_PERIDOT_ORE = registerBlock("blackstone_peridot_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
        PERIDOT_BLOCK = registerBlock("peridot_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
        PERIDOT = ITEMS.register("peridot", () -> new Item(new Item.Properties()));

        // --- Moonstone ---
        MOONSTONE_ORE = registerBlock("moonstone_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
        DEEPSLATE_MOONSTONE_ORE = registerBlock("deepslate_moonstone_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
        MOONSTONE_BLOCK = registerBlock("moonstone_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
        MOONSTONE = ITEMS.register("moonstone", () -> new Item(new Item.Properties()));

        // --- Agate ---
        AGATE_ORE = registerBlock("agate_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
        DEEPSLATE_AGATE_ORE = registerBlock("deepslate_agate_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
        AGATE_BLOCK = registerBlock("agate_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
        AGATE = ITEMS.register("agate", () -> new Item(new Item.Properties()));

        // mithril
        MITHRIL_ORE = registerBlock("mithril_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)));
        DEEPSLATE_MITHRIL_ORE = registerBlock("deepslate_mithril_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)));
        MITHRIL_BLOCK = registerBlock("mithril_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
        RAW_MITHRIL_BLOCK = registerBlock("raw_mithril_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)));
        RAW_MITHRIL = ITEMS.register("raw_mithril", () -> new Item(new Item.Properties()));
        MITHRIL_INGOT = ITEMS.register("mithril_ingot", () -> new Item(new Item.Properties()));
        MITHRIL_NUGGET = ITEMS.register("mithril_nugget", () -> new Item(new Item.Properties()));

        // pearl
        PEARL = ITEMS.register("pearl", () -> new Item(new Item.Properties()));

        // skythium
        SKYTHIUM_ORE = registerBlock("skythium_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
        DEEPSLATE_SKYTHIUM_ORE = registerBlock("deepslate_skythium_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
        SKYTHIUM_BLOCK = registerBlock("skythium_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
        SKYTHIUM = ITEMS.register("skythium", () -> new Item(new Item.Properties()));
    }

    public static void load(){}
}
