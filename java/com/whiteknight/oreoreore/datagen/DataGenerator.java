package com.whiteknight.oreoreore.datagen;

import com.whiteknight.oreoreore.datagen.recipes.AllRecipeProvider;
import com.whiteknight.oreoreore.datagen.worldgen.ModBiomeModifiers;
import com.whiteknight.oreoreore.datagen.worldgen.ModConfiguredFeatures;
import com.whiteknight.oreoreore.datagen.worldgen.ModPlacedFeatures;
import com.whiteknight.oreoreore.game.OreOreOre;
import com.whiteknight.oreoreore.game.util.ModEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DataGenerator {
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        var registryBuilder = new RegistrySetBuilder()
                .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
                .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
                .add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);

        DatapackBuiltinEntriesProvider datapackProvider = new DatapackBuiltinEntriesProvider(
                packOutput,
                lookupProvider,
                registryBuilder,
                Set.of(OreOreOre.MOD_ID)
        );

        generator.addProvider(event.includeServer(),datapackProvider);

        CompletableFuture<HolderLookup.Provider> moddedEnhancedLookup = datapackProvider.getRegistryProvider();

        generator.addProvider(event.includeClient(), new ModEnLanguageProvider(packOutput, OreOreOre.MOD_ID));
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));

        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ModItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new AllRecipeProvider(packOutput, moddedEnhancedLookup));
        generator.addProvider(event.includeServer(), new ModDataMapProvider(packOutput, lookupProvider));

        generator.addProvider(event.includeServer(), new ModGlobalLootModifierProvider(packOutput, lookupProvider));
    }
}
