package com.whiteknight.oreoreore.datagen.worldgen;

import com.whiteknight.oreoreore.game.OreOreOre;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> SILVER_UPPER_ORE_PLACED_KEY = registerKey("silver_upper");
    public static final ResourceKey<PlacedFeature> SILVER_MIDDLE_ORE_PLACED_KEY = registerKey("silver_middle");
    public static final ResourceKey<PlacedFeature> SILVER_SMALL_ORE_PLACED_KEY = registerKey("silver_small");
    public static final ResourceKey<PlacedFeature> AQUAMARINE_ORE_PLACED_KEY = registerKey("aquamarine_ore_placed");
    public static final ResourceKey<PlacedFeature> GARNET_ORE_PLACED_KEY = registerKey("garnet_ore_placed");
    public static final ResourceKey<PlacedFeature> ONYX_ORE_PLACED_KEY = registerKey("onyx_ore_placed");
    public static final ResourceKey<PlacedFeature> TOPAZ_ORE_PLACED_KEY = registerKey("topaz_ore_placed");
    public static final ResourceKey<PlacedFeature> OPAL_ORE_PLACED_KEY = registerKey("opal_ore_placed");
    public static final ResourceKey<PlacedFeature> SAPPHIRE_ORE_PLACED_KEY = registerKey("sapphire_ore_placed");
    public static final ResourceKey<PlacedFeature> SPINEL_ORE_PLACED_KEY = registerKey("spinel_ore_placed");
    public static final ResourceKey<PlacedFeature> PERIDOT_ORE_PLACED_KEY = registerKey("peridot_ore_placed");
    public static final ResourceKey<PlacedFeature> MOONSTONE_ORE_PLACED_KEY = registerKey("moonstone_ore_placed");
    public static final ResourceKey<PlacedFeature> MITHRIL_ORE_PLACED_KEY = registerKey("mithril_ore_placed");
    public static final ResourceKey<PlacedFeature> SKYTHIUM_ORE_PLACED_KEY = registerKey("skythium_ore_placed");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, SILVER_UPPER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER_ORE_KEY),
                List.of(CountPlacement.of(80), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384)), BiomeFilter.biome()));
        register(context, SILVER_MIDDLE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER_ORE_KEY),
                List.of(CountPlacement.of(9), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)), BiomeFilter.biome()));
        register(context, SILVER_SMALL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER_SMALL_ORE_KEY),
                List.of(CountPlacement.of(9), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72)), BiomeFilter.biome()));
        register(context, MITHRIL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MITHRIL_ORE_KEY),
                List.of(CountPlacement.of(2), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(-61)), BiomeFilter.biome()));

        // アクアマリン (海):
        register(context, AQUAMARINE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.AQUAMARINE_ORE_KEY),
                commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(32))));
        // ガーネット (ネザー/真紅の森):
        register(context, GARNET_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GARNET_ORE_KEY),
                commonOrePlacement(80, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
        // オニキス (荒野):
        register(context, ONYX_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ONYX_ORE_KEY),
                commonOrePlacement(18, HeightRangePlacement.uniform(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(256))));
        // トパーズ (雪原):
        register(context, TOPAZ_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TOPAZ_ORE_KEY),
                commonOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(32))));
        // オパール (きのこ島):
        register(context, OPAL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OPAL_ORE_KEY),
                commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(112))));
        // サファイア (ジャングル):
        register(context, SAPPHIRE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SAPPHIRE_ORE_KEY),
                commonOrePlacement(48, HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(80))));
        // スピネル (高山)
        register(context, SPINEL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SPINEL_ORE_KEY),
                commonOrePlacement(18, HeightRangePlacement.uniform(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(256))));
        // ペリドット (ネザー/玄武岩の三角州):
        register(context, PERIDOT_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PERIDOT_ORE_KEY),
                commonOrePlacement(20, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top())));
        // ムーンストーン (サクラの林):
        register(context, MOONSTONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MOONSTONE_ORE_KEY),
                commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))));
        register(context, SKYTHIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SKYTHIUM_ORE_KEY),
                commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.absolute(110), VerticalAnchor.absolute(130))));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier height) {
        return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier height) {
        return orePlacement(CountPlacement.of(count), height);
    }

    private static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier height) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), height);
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
