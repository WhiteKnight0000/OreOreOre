package com.whiteknight.oreoreore.datagen;

import com.whiteknight.oreoreore.game.register.ModOres;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
//import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.HashSet;
import java.util.Set;

public class ModBlockLootProvider extends BlockLootSubProvider {
    protected ModBlockLootProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    private final Set<Block> generatedBlocks = new HashSet<>();

    @Override
    protected void add(Block block, LootTable.Builder builder) {
        super.add(block, builder);
        this.generatedBlocks.add(block);
    }

    @Override
    protected void generate() {
        add(ModOres.SILVER_ORE.get(), block -> createOreDrop(ModOres.SILVER_ORE.get(), ModOres.RAW_SILVER.get()));
        add(ModOres.DEEPSLATE_SILVER_ORE.get(), block -> createOreDrop(ModOres.DEEPSLATE_SILVER_ORE.get(), ModOres.RAW_SILVER.get()));
        add(ModOres.MITHRIL_ORE.get(), block -> createOreDrop(ModOres.MITHRIL_ORE.get(), ModOres.RAW_MITHRIL.get()));
        add(ModOres.DEEPSLATE_MITHRIL_ORE.get(), block -> createOreDrop(ModOres.DEEPSLATE_MITHRIL_ORE.get(), ModOres.RAW_MITHRIL.get()));

        add(ModOres.AQUAMARINE_ORE.get(), block -> createOreDrop(block, ModOres.AQUAMARINE.get()));
        add(ModOres.DEEPSLATE_AQUAMARINE_ORE.get(), block -> createOreDrop(block, ModOres.AQUAMARINE.get()));
        add(ModOres.NETHER_GARNET_ORE.get(), block -> createOreDrop(block, ModOres.GARNET.get()));
        add(ModOres.ONYX_ORE.get(), block -> createOreDrop(block, ModOres.ONYX.get()));
        add(ModOres.DEEPSLATE_ONYX_ORE.get(), block -> createOreDrop(block, ModOres.ONYX.get()));
        add(ModOres.TOPAZ_ORE.get(), block -> createOreDrop(block, ModOres.TOPAZ.get()));
        add(ModOres.DEEPSLATE_TOPAZ_ORE.get(), block -> createOreDrop(block, ModOres.TOPAZ.get()));
        add(ModOres.OPAL_ORE.get(), block -> createOreDrop(block, ModOres.OPAL.get()));
        add(ModOres.DEEPSLATE_OPAL_ORE.get(), block -> createOreDrop(block, ModOres.OPAL.get()));
        add(ModOres.SAPPHIRE_ORE.get(), block -> createOreDrop(block, ModOres.SAPPHIRE.get()));
        add(ModOres.DEEPSLATE_SAPPHIRE_ORE.get(), block -> createOreDrop(block, ModOres.SAPPHIRE.get()));
        add(ModOres.SPINEL_ORE.get(), block -> createOreDrop(block, ModOres.SPINEL.get()));
        add(ModOres.DEEPSLATE_SPINEL_ORE.get(), block -> createOreDrop(block, ModOres.SPINEL.get()));
        add(ModOres.BASALT_PERIDOT_ORE.get(), block -> createOreDrop(block, ModOres.PERIDOT.get()));
        add(ModOres.BLACKSTONE_PERIDOT_ORE.get(), block -> createOreDrop(block, ModOres.PERIDOT.get()));
        add(ModOres.MOONSTONE_ORE.get(), block -> createOreDrop(block, ModOres.MOONSTONE.get()));
        add(ModOres.DEEPSLATE_MOONSTONE_ORE.get(), block -> createOreDrop(block, ModOres.MOONSTONE.get()));
        add(ModOres.AGATE_ORE.get(), block -> createOreDrop(block, ModOres.AGATE.get()));
        add(ModOres.DEEPSLATE_AGATE_ORE.get(), block -> createOreDrop(block, ModOres.AGATE.get()));

        add(ModOres.SKYTHIUM_ORE.get(), block -> createOreDrop(block, ModOres.SKYTHIUM.get()));
        add(ModOres.DEEPSLATE_SKYTHIUM_ORE.get(), block -> createOreDrop(block, ModOres.SKYTHIUM.get()));

        for (Block block : this.getKnownBlocks()) {
            if (!this.generatedBlocks.contains(block)) {
                this.dropSelf(block);
            }
        }
    }

    /**
     *
     * @return Modで追加したすべてのブロック
     */
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return DataGenLibrary.getKnownBlocks().stream().map(Holder::value)::iterator;
    }
}
