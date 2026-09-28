package com.whiteknight.oreoreore.game.register;

import com.whiteknight.oreoreore.game.elements.entity.ThrownPhantomTrident;
import com.whiteknight.oreoreore.game.elements.entity.GarnetGemEntity;
import com.whiteknight.oreoreore.game.elements.item.*;
import com.whiteknight.oreoreore.game.util.ModFoods;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.whiteknight.oreoreore.game.util.ModRegistries.ENTITIES;
import static com.whiteknight.oreoreore.game.util.ModRegistries.ITEMS;

public class ModUseItems {
    public static final DeferredItem<Item> HOLY_GRAIL;
    public static final DeferredItem<Item> ROSARY;
    public static final DeferredItem<Item> MAGIC_GEM_OF_AQUAMARINE;
    public static final DeferredItem<Item> PHANTOM_TRIDENT;
    public static final DeferredHolder<EntityType<?>, EntityType<ThrownPhantomTrident>> THROWN_PHANTOM_TRIDENT;
    public static final DeferredItem<Item> MAGIC_GEM_OF_GARNET;
    public static final DeferredHolder<EntityType<?>, EntityType<GarnetGemEntity>> GARNET_GEM_ENTITY;
    public static final DeferredItem<Item> MAGIC_GEM_OF_ONYX;
    public static final DeferredItem<Item> MAGIC_GEM_OF_TOPAZ;
    public static final DeferredItem<Item> MAGIC_GEM_OF_OPAL;
    public static final DeferredItem<Item> MAGIC_GEM_OF_SAPPHIRE;
    public static final DeferredItem<Item> MAGIC_GEM_OF_SPINEL;
    public static final DeferredItem<Item> MAGIC_GEM_OF_PERIDOT;
    public static final DeferredItem<Item> MAGIC_GEM_OF_MOONSTONE;
    public static final DeferredItem<Item> MAGIC_GEM_OF_AGATE;
    public static final DeferredItem<Item> MAGIC_GEM_OF_EMERALD;
    public static final DeferredItem<Item> MAGIC_GEM_OF_DIAMOND;
    public static final DeferredItem<Item> CRAZY_MUSHROOM_STEW;

    public static final DeferredItem<Item> INNER_ANY_POTION;

    static{
        MAGIC_GEM_OF_AQUAMARINE = ITEMS.register("magic_gem_of_aquamarine", () -> new AquamarineGem(new Item.Properties().stacksTo(16)));
        PHANTOM_TRIDENT = ITEMS.register("phantom_trident", () -> new PhantomTrident((new Item.Properties()).rarity(Rarity.EPIC).durability(1).attributes(PhantomTrident.createAttributes()).component(DataComponents.TOOL, PhantomTrident.createToolProperties())));
        THROWN_PHANTOM_TRIDENT = ENTITIES.register("phantom_trident", () -> EntityType.Builder.<ThrownPhantomTrident>of(ThrownPhantomTrident::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("phantom_trident"));
        MAGIC_GEM_OF_GARNET = ITEMS.register("magic_gem_of_garnet", () -> new GarnetGem(new Item.Properties().stacksTo(16)));
        GARNET_GEM_ENTITY = ENTITIES.register("garnet_gem", () -> EntityType.Builder.<GarnetGemEntity>of(GarnetGemEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("garnet_gem"));
        MAGIC_GEM_OF_ONYX = ITEMS.register("magic_gem_of_onyx", () -> new OnyxGem(new Item.Properties().stacksTo(16)));
        MAGIC_GEM_OF_TOPAZ = ITEMS.register("magic_gem_of_topaz", () -> new TopazGem(new Item.Properties().stacksTo(16)));
        MAGIC_GEM_OF_OPAL = ITEMS.register("magic_gem_of_opal", () -> new OpalGem(new Item.Properties().stacksTo(16)));
        MAGIC_GEM_OF_SAPPHIRE = ITEMS.register("magic_gem_of_sapphire", () -> new SapphireGem(new Item.Properties().stacksTo(16)));
        MAGIC_GEM_OF_SPINEL = ITEMS.register("magic_gem_of_spinel", () -> new SpinelGem(new Item.Properties().stacksTo(16)));
        MAGIC_GEM_OF_PERIDOT = ITEMS.register("magic_gem_of_peridot", () -> new PeridotGem(new Item.Properties().stacksTo(16)));
        MAGIC_GEM_OF_MOONSTONE = ITEMS.register("magic_gem_of_moonstone", () -> new MoonStoneGem(new Item.Properties().stacksTo(16)));
        MAGIC_GEM_OF_AGATE = ITEMS.register("magic_gem_of_agate", () -> new AgateGem(new Item.Properties().stacksTo(16)));
        MAGIC_GEM_OF_EMERALD = ITEMS.register("magic_gem_of_emerald", () -> new EmeraldGem(new Item.Properties().stacksTo(16)));
        MAGIC_GEM_OF_DIAMOND = ITEMS.register("magic_gem_of_diamond", () -> new DiamondGem(new Item.Properties().stacksTo(16)));

        HOLY_GRAIL = ITEMS.register("holy_grail", () -> new ItemWithToolTip(new Item.Properties()));
        INNER_ANY_POTION = ITEMS.register("any_potion", () -> new Item(new Item.Properties()));
        ROSARY = ITEMS.register("rosary", () -> new Rosary(new Item.Properties().stacksTo(1),10D));

        CRAZY_MUSHROOM_STEW = ITEMS.register("crazy_mushroom_stew", () -> new Item(new Item.Properties().food(ModFoods.CRAZY_MUSHROOM_STEW)));
    }

    public static void load(){}
}
