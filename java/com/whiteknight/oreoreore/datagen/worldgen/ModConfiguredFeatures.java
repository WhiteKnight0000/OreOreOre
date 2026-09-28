package com.whiteknight.oreoreore.datagen.worldgen;

import com.whiteknight.oreoreore.game.OreOreOre;
import com.whiteknight.oreoreore.game.register.ModOres;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORE_KEY = registerKey("silver_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_SMALL_ORE_KEY = registerKey("silver_ore_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AQUAMARINE_ORE_KEY = registerKey("aquamarine_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GARNET_ORE_KEY = registerKey("garnet_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ONYX_ORE_KEY = registerKey("onyx_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TOPAZ_ORE_KEY = registerKey("topaz_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OPAL_ORE_KEY = registerKey("opal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAPPHIRE_ORE_KEY = registerKey("sapphire_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPINEL_ORE_KEY = registerKey("spinel_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PERIDOT_ORE_KEY = registerKey("peridot_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOONSTONE_ORE_KEY = registerKey("moonstone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MITHRIL_ORE_KEY = registerKey("mithril_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SKYTHIUM_ORE_KEY = registerKey("skythium_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest basaltReplaceables = new BlockMatchTest(Blocks.BASALT);
        RuleTest blackstoneReplaceables = new BlockMatchTest(Blocks.BLACKSTONE);

        List<OreConfiguration.TargetBlockState> aquamarineOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModOres.AQUAMARINE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModOres.DEEPSLATE_AQUAMARINE_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> onyxOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModOres.ONYX_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModOres.DEEPSLATE_ONYX_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> topazOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModOres.TOPAZ_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModOres.DEEPSLATE_TOPAZ_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> opalOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModOres.OPAL_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModOres.DEEPSLATE_OPAL_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> sapphireOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModOres.SAPPHIRE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModOres.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> spinelOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModOres.SPINEL_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModOres.DEEPSLATE_SPINEL_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> moonstoneOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModOres.MOONSTONE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModOres.DEEPSLATE_MOONSTONE_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> garnetOres = List.of(
                OreConfiguration.target(netherrackReplaceables, ModOres.NETHER_GARNET_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> peridotOres = List.of(
                OreConfiguration.target(basaltReplaceables, ModOres.BASALT_PERIDOT_ORE.get().defaultBlockState()),
                OreConfiguration.target(blackstoneReplaceables, ModOres.BLACKSTONE_PERIDOT_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldSilverOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModOres.SILVER_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModOres.DEEPSLATE_SILVER_ORE.get().defaultBlockState()));
        register(context, SILVER_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSilverOres, 8));
        register(context, SILVER_SMALL_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSilverOres, 3));
        List<OreConfiguration.TargetBlockState> overworldMithrilOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModOres.MITHRIL_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModOres.DEEPSLATE_MITHRIL_ORE.get().defaultBlockState()));
        register(context, MITHRIL_ORE_KEY, Feature.ORE, new OreConfiguration(overworldMithrilOres, 4));

        register(context, AQUAMARINE_ORE_KEY, Feature.ORE, new OreConfiguration(aquamarineOres, 10));
        register(context, GARNET_ORE_KEY, Feature.ORE, new OreConfiguration(garnetOres, 2));
        register(context, ONYX_ORE_KEY, Feature.ORE, new OreConfiguration(onyxOres, 7));
        register(context, TOPAZ_ORE_KEY, Feature.ORE, new OreConfiguration(topazOres, 7));
        register(context, OPAL_ORE_KEY, Feature.ORE, new OreConfiguration(opalOres, 7));
        register(context, SAPPHIRE_ORE_KEY, Feature.ORE, new OreConfiguration(sapphireOres, 2));
        register(context, SPINEL_ORE_KEY, Feature.ORE, new OreConfiguration(spinelOres, 7));
        register(context, PERIDOT_ORE_KEY, Feature.ORE, new OreConfiguration(peridotOres, 7));
        register(context, MOONSTONE_ORE_KEY, Feature.ORE, new OreConfiguration(moonstoneOres, 7));

        List<OreConfiguration.TargetBlockState> skythiumOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModOres.SKYTHIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModOres.DEEPSLATE_SKYTHIUM_ORE.get().defaultBlockState())
        );
        register(context, SKYTHIUM_ORE_KEY, Feature.ORE, new OreConfiguration(skythiumOres, 4));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
