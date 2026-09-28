package com.whiteknight.oreoreore.game.register;

import com.whiteknight.oreoreore.game.elements.blockentity.base.EarthAltar;
import com.whiteknight.oreoreore.game.elements.blockentity.entity.EarthAltarEntity;
import com.whiteknight.oreoreore.game.elements.item.EarthFaith;
import com.whiteknight.oreoreore.game.elements.item.ItemWithToolTip;
import com.whiteknight.oreoreore.game.elements.screen.EarthAltarMenu;
import com.whiteknight.oreoreore.game.util.component.ModDataComponents;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.whiteknight.oreoreore.game.util.ModRegistries.*;

import java.util.function.Supplier;

/**
 * 儀式関連のアイテム
 */
public class ModRitualItems {
    public static final DeferredItem<Item> FRESH_GREEN_FAITH;
    public static final DeferredItem<Item> NATURE_FAITH;
    public static final DeferredItem<Item> JADE_FAITH;

    public static final DeferredBlock<Block> EARTH_ALTAR;
    public static final Supplier<BlockEntityType<EarthAltarEntity>> EARTH_ALTAR_BE;
    public static final DeferredHolder<MenuType<?>, MenuType<EarthAltarMenu>> EARTH_ALTAR_MENU;

    public static final DeferredItem<Item> EARTH_WAND;

    static{
        FRESH_GREEN_FAITH = ITEMS.register("fresh_green_faith", () -> new EarthFaith(new Item.Properties().component(ModDataComponents.ENERGY,0),100));
        NATURE_FAITH = ITEMS.register("nature_faith", () -> new EarthFaith(new Item.Properties().component(ModDataComponents.ENERGY,0),5000));
        JADE_FAITH = ITEMS.register("jade_faith", () -> new EarthFaith(new Item.Properties().component(ModDataComponents.ENERGY,0),10000));

        EARTH_ALTAR = registerBlock("earth_altar", () -> new EarthAltar(BlockBehaviour.Properties.of().noCollission().noOcclusion()));
        EARTH_ALTAR_BE = BLOCK_ENTITIES.register("earth_altar_be", () -> BlockEntityType.Builder.of(EarthAltarEntity::new, EARTH_ALTAR.get()).build(null));

        EARTH_ALTAR_MENU = registerMenuType("earth_altar_menu", EarthAltarMenu::new);

        EARTH_WAND = ITEMS.register("earth_wand", () -> new ItemWithToolTip(new Item.Properties().stacksTo(1)));
    }

    public static void load(){}
}
