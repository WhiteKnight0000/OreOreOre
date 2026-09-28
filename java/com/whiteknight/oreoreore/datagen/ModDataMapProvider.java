package com.whiteknight.oreoreore.datagen;

import com.whiteknight.oreoreore.game.register.ModOres;
import com.whiteknight.oreoreore.game.util.ModDataMaps;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.DataMapProvider;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        builder(ModDataMaps.ENERGY_VALUE)
                .add(ModOres.SILVER_INGOT,5,false)
                .add(ModOres.MITHRIL_INGOT,20,false)
                .add(ModOres.AQUAMARINE, 5, false)
                .add(ModOres.GARNET, 5, false)
                .add(ModOres.ONYX, 5, false)
                .add(ModOres.TOPAZ, 5, false)
                .add(ModOres.OPAL, 5, false)
                .add(ModOres.SAPPHIRE, 5, false)
                .add(ModOres.SPINEL, 5, false)
                .add(ModOres.PERIDOT, 5, false)
                .add(ModOres.MOONSTONE, 5, false)
                .add(ModOres.AGATE, 5, false)
                .add(ModOres.SKYTHIUM, 6, false)
                .add(ResourceLocation.withDefaultNamespace("gold_ingot"),1,false)
                .add(ResourceLocation.withDefaultNamespace("lapis_lazuli"),2,false)
                .add(ResourceLocation.withDefaultNamespace("amethyst_shard"),5,false)
                .add(ResourceLocation.withDefaultNamespace("emerald"),5,false)
                .add(ResourceLocation.withDefaultNamespace("echo_shard"),6,false)
                .add(ResourceLocation.withDefaultNamespace("netherite_ingot"),10,false)
                .add(ResourceLocation.withDefaultNamespace("diamond"),10,false);
    }
}