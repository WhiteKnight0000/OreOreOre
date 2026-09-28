package com.whiteknight.oreoreore.game.register;

import com.whiteknight.oreoreore.game.OreOreOre;
import com.whiteknight.oreoreore.game.elements.item.*;
import net.minecraft.Util;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.EnumMap;
import java.util.List;

import static com.whiteknight.oreoreore.game.util.ModRegistries.*;

public class ModEquips {
    public static final Tier TIER_SILVER;
    public static final DeferredItem<Item> SILVER_SWORD;

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> MITHRIL_MATERIAL;
    public static final DeferredItem<Item> MITHRIL_HELMET;
    public static final DeferredItem<Item> MITHRIL_CHESTPLATE;
    public static final DeferredItem<Item> MITHRIL_LEGGINGS;
    public static final DeferredItem<Item> MITHRIL_BOOTS;

    public static final Tier TIER_CRIMSON;
    //関連 : Loot
    public static final DeferredItem<Item> CRIMSON_AXE;
    public static final DeferredItem<Item> GREEN_SHEARS;

    public static final Tier TIER_ONYX;
    public static final DeferredItem<Item> ONYX_DAGGER;

    public static final DeferredItem<Item> DEFENCE_BIG_SHIELD;

    public static final Tier TIER_TOPAZ;
    public static final DeferredItem<Item> TOPAZ_AXE;
    public static final DeferredItem<Item> TOPAZ_PICKAXE;
    public static final DeferredItem<Item> TOPAZ_SHOVEL;
    public static final DeferredItem<Item> TOPAZ_PAXEL;

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SKY_WALKER_MATERIAL;
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> BLAST_JUMPER_MATERIAL;
    public static final DeferredItem<Item> SKY_WALKER;
    public static final DeferredItem<Item> BLAST_JUMPER;



    static {
        TIER_SILVER = new SimpleTier(
                BlockTags.INCORRECT_FOR_IRON_TOOL, 150, 6.0F, 1.0F, 14, () -> Ingredient.of(new ItemLike[]{ModOres.SILVER_INGOT})
        );
        SILVER_SWORD = ITEMS.register((String)"silver_sword", () -> new SwordItem(TIER_SILVER, (new Item.Properties()).attributes(SwordItem.createAttributes(Tiers.STONE, 3, -2.4F))));

        MITHRIL_MATERIAL = ARMOR_MATERIALS.register("mithril",
                () -> new ArmorMaterial(
                        Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                            map.put(ArmorItem.Type.BOOTS, 3);
                            map.put(ArmorItem.Type.LEGGINGS, 7);
                            map.put(ArmorItem.Type.CHESTPLATE, 9);
                            map.put(ArmorItem.Type.HELMET, 3);
                            map.put(ArmorItem.Type.BODY, 11);
                        }),
                        15,
                        SoundEvents.ARMOR_EQUIP_DIAMOND,
                        () -> Ingredient.of(ModOres.MITHRIL_INGOT),
                        List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, "mithril"))),
                        3.5F,
                        0F
                )
        );

        MITHRIL_HELMET = ITEMS.register("mithril_helmet", () -> new ArmorItem(MITHRIL_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(44)).stacksTo(1)));
        MITHRIL_CHESTPLATE = ITEMS.register("mithril_chestplate", () -> new ArmorItem(MITHRIL_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(44)).stacksTo(1)));
        MITHRIL_LEGGINGS = ITEMS.register("mithril_leggings", () -> new ArmorItem(MITHRIL_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(44)).stacksTo(1)));
        MITHRIL_BOOTS = ITEMS.register("mithril_boots", () -> new ArmorItem(MITHRIL_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(44)).stacksTo(1)));

        TIER_CRIMSON = new SimpleTier(
                BlockTags.INCORRECT_FOR_IRON_TOOL,
                250,
                6.0F,
                2.0F,
                14,
                () -> Ingredient.of(ModOres.GARNET.get())
        );
        CRIMSON_AXE = ITEMS.register("crimson_axe", () -> new AxeItemWithToolTip(TIER_CRIMSON, (new Item.Properties()).attributes(AxeItem.createAttributes(TIER_CRIMSON, 6.0F, -3.1F))));
        GREEN_SHEARS = ITEMS.register("green_shears", () -> new ShearsItem((new Item.Properties()).durability(714).component(DataComponents.TOOL, ShearsItem.createToolProperties())));

        TIER_ONYX = new SimpleTier(
                BlockTags.INCORRECT_FOR_IRON_TOOL, 9999, 7.0F, 1.0F, 5, () -> Ingredient.of(new ItemLike[]{ModOres.SILVER_INGOT})
        );
        ONYX_DAGGER = ITEMS.register((String)"onyx_dagger", () -> new SwordItem(TIER_ONYX, (new Item.Properties()).attributes(SwordItem.createAttributes(Tiers.STONE, 1, 0f))));
        DEFENCE_BIG_SHIELD = ITEMS.register((String)"defence_big_shield", () -> new ShieldItem((new Item.Properties()).durability(1000).component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)));

        TIER_TOPAZ = new SimpleTier(
                BlockTags.INCORRECT_FOR_IRON_TOOL,
                350, 7.0F, 0.0F, 17,
                () -> Ingredient.of(ModOres.TOPAZ.get())
        );
        TOPAZ_AXE = ITEMS.register("topaz_axe", () -> new AxeItem(TIER_TOPAZ, (new Item.Properties()).attributes(AxeItem.createAttributes(TIER_TOPAZ, 3.0F, -3.1F))));
        TOPAZ_PICKAXE = ITEMS.register("topaz_pickaxe", () -> new PickaxeItem(TIER_TOPAZ, (new Item.Properties()).attributes(PickaxeItem.createAttributes(TIER_TOPAZ, 0.0F, -2.8F))));
        TOPAZ_SHOVEL = ITEMS.register("topaz_shovel", () -> new ShovelItem(TIER_TOPAZ, (new Item.Properties()).attributes(ShovelItem.createAttributes(TIER_TOPAZ, 0.5F, -3.0F))));
        TOPAZ_PAXEL = ITEMS.register("topaz_paxel",() -> new PaxelItem(TIER_TOPAZ, (new Item.Properties()).attributes(PaxelItem.createAttributes(TIER_TOPAZ, 3.0F, -2.8F))));

        SKY_WALKER_MATERIAL = ARMOR_MATERIALS.register("sky_walker",
                () -> new ArmorMaterial(
                        Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                            map.put(ArmorItem.Type.BOOTS, 2);
                            map.put(ArmorItem.Type.LEGGINGS, 7);
                            map.put(ArmorItem.Type.CHESTPLATE, 9);
                            map.put(ArmorItem.Type.HELMET, 3);
                            map.put(ArmorItem.Type.BODY, 11);
                        }),
                        30,
                        SoundEvents.ARMOR_EQUIP_DIAMOND,
                        () -> Ingredient.of(ModOres.SKYTHIUM),
                        List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, "sky_walker"))),
                        0F,
                        0F
                )
        );

        BLAST_JUMPER_MATERIAL = ARMOR_MATERIALS.register("blast_jumper",
                () -> new ArmorMaterial(
                        Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                            map.put(ArmorItem.Type.BOOTS, 2);
                            map.put(ArmorItem.Type.LEGGINGS, 7);
                            map.put(ArmorItem.Type.CHESTPLATE, 9);
                            map.put(ArmorItem.Type.HELMET, 3);
                            map.put(ArmorItem.Type.BODY, 11);
                        }),
                        30,
                        SoundEvents.ARMOR_EQUIP_DIAMOND,
                        () -> Ingredient.of(ModOres.SKYTHIUM),
                        List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, "blast_jumper"))),
                        0F,
                        0F
                )
        );

        SKY_WALKER = ITEMS.register("sky_walker", () -> new DoubleJumpArmorItem(SKY_WALKER_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(17)).stacksTo(1),1f));
        BLAST_JUMPER = ITEMS.register("blast_jumper", () -> new DoubleJumpArmorItem(BLAST_JUMPER_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(17)).stacksTo(1),4f));
    }

    public static void load(){}
}
