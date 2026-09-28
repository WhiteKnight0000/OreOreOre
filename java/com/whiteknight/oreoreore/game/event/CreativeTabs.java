package com.whiteknight.oreoreore.game.event;

import com.whiteknight.oreoreore.game.OreOreOre;
import com.whiteknight.oreoreore.game.register.*;
import com.whiteknight.oreoreore.game.util.ModTranslatable;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CreativeTabs {
    /**
     * バニラのクリエイティブタブについて変更する
     */
    public static void addCreativeTabs(BuildCreativeModeTabContentsEvent event){

    }

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OreOreOre.MOD_ID);

    public static final Supplier<CreativeModeTab> OREOREORE_TAB = CREATIVE_MODE_TAB.register("oreoreore_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModOres.MITHRIL_INGOT.get()))
                    .title(ModTranslatable.CREATIVETAB_NAME.getComponent())
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModRitualItems.FRESH_GREEN_FAITH.get());
                        output.accept(ModRitualItems.NATURE_FAITH.get());
                        output.accept(ModRitualItems.JADE_FAITH.get());
                        output.accept(ModRitualItems.EARTH_ALTAR.get());
                        output.accept(ModRitualItems.EARTH_WAND.get());

                        output.accept(ModOres.SILVER_ORE.get());
                        output.accept(ModOres.DEEPSLATE_SILVER_ORE.get());
                        output.accept(ModOres.SILVER_BLOCK.get());
                        output.accept(ModOres.RAW_SILVER_BLOCK.get());
                        output.accept(ModOres.RAW_SILVER.get());
                        output.accept(ModOres.SILVER_INGOT.get());
                        output.accept(ModOres.SILVER_NUGGET.get());

                        output.accept(ModOres.AQUAMARINE_ORE.get());
                        output.accept(ModOres.DEEPSLATE_AQUAMARINE_ORE.get());
                        output.accept(ModOres.AQUAMARINE_BLOCK.get());
                        output.accept(ModOres.AQUAMARINE.get());

                        output.accept(ModOres.NETHER_GARNET_ORE.get());
                        output.accept(ModOres.GARNET_BLOCK.get());
                        output.accept(ModOres.GARNET.get());

                        output.accept(ModOres.ONYX_ORE.get());
                        output.accept(ModOres.DEEPSLATE_ONYX_ORE.get());
                        output.accept(ModOres.ONYX_BLOCK.get());
                        output.accept(ModOres.ONYX.get());

                        output.accept(ModOres.TOPAZ_ORE.get());
                        output.accept(ModOres.DEEPSLATE_TOPAZ_ORE.get());
                        output.accept(ModOres.TOPAZ_BLOCK.get());
                        output.accept(ModOres.TOPAZ.get());

                        output.accept(ModOres.OPAL_ORE.get());
                        output.accept(ModOres.DEEPSLATE_OPAL_ORE.get());
                        output.accept(ModOres.OPAL_BLOCK.get());
                        output.accept(ModOres.OPAL.get());

                        output.accept(ModOres.SAPPHIRE_ORE.get());
                        output.accept(ModOres.DEEPSLATE_SAPPHIRE_ORE.get());
                        output.accept(ModOres.SAPPHIRE_BLOCK.get());
                        output.accept(ModOres.SAPPHIRE.get());

                        output.accept(ModOres.SPINEL_BLOCK.get());
                        output.accept(ModOres.SPINEL.get());

                        output.accept(ModOres.BASALT_PERIDOT_ORE.get());
                        output.accept(ModOres.BLACKSTONE_PERIDOT_ORE.get());
                        output.accept(ModOres.PERIDOT_BLOCK.get());
                        output.accept(ModOres.PERIDOT.get());

                        output.accept(ModOres.MOONSTONE_ORE.get());
                        output.accept(ModOres.DEEPSLATE_MOONSTONE_ORE.get());
                        output.accept(ModOres.MOONSTONE_BLOCK.get());
                        output.accept(ModOres.MOONSTONE.get());

                        output.accept(ModOres.AGATE_ORE.get());
                        output.accept(ModOres.DEEPSLATE_AGATE_ORE.get());
                        output.accept(ModOres.AGATE_BLOCK.get());
                        output.accept(ModOres.AGATE.get());

                        output.accept(ModOres.PEARL.get());

                        output.accept(ModOres.SKYTHIUM_ORE.get());
                        output.accept(ModOres.SKYTHIUM_BLOCK.get());
                        output.accept(ModOres.SKYTHIUM.get());

                        output.accept(ModOres.MITHRIL_ORE.get());
                        output.accept(ModOres.DEEPSLATE_MITHRIL_ORE.get());
                        output.accept(ModOres.MITHRIL_BLOCK.get());
                        output.accept(ModOres.RAW_MITHRIL_BLOCK.get());
                        output.accept(ModOres.RAW_MITHRIL.get());
                        output.accept(ModOres.MITHRIL_INGOT.get());
                        output.accept(ModOres.MITHRIL_NUGGET.get());

                        output.accept(ModEquips.MITHRIL_HELMET.get());
                        output.accept(ModEquips.MITHRIL_CHESTPLATE.get());
                        output.accept(ModEquips.MITHRIL_LEGGINGS.get());
                        output.accept(ModEquips.MITHRIL_BOOTS.get());
                        output.accept(ModEquips.SKY_WALKER.get());
                        output.accept(ModEquips.BLAST_JUMPER.get());

                        output.accept(ModEquips.GREEN_SHEARS.get());
                        output.accept(ModEquips.CRIMSON_AXE.get());
                        output.accept(ModEquips.TOPAZ_AXE.get());
                        output.accept(ModEquips.TOPAZ_PICKAXE.get());
                        output.accept(ModEquips.TOPAZ_SHOVEL.get());
                        output.accept(ModEquips.TOPAZ_PAXEL.get());
                        output.accept(ModEquips.ONYX_DAGGER.get());
                        output.accept(ModEquips.SILVER_SWORD.get());

                        output.accept(ModUseItems.MAGIC_GEM_OF_AQUAMARINE.get());
                        output.accept(ModUseItems.MAGIC_GEM_OF_GARNET.get());
                        output.accept(ModUseItems.MAGIC_GEM_OF_ONYX.get());
                        output.accept(ModUseItems.MAGIC_GEM_OF_TOPAZ.get());
                        output.accept(ModUseItems.MAGIC_GEM_OF_OPAL.get());
                        output.accept(ModUseItems.MAGIC_GEM_OF_SAPPHIRE.get());
                        output.accept(ModUseItems.MAGIC_GEM_OF_SPINEL.get());
                        output.accept(ModUseItems.MAGIC_GEM_OF_PERIDOT.get());
                        output.accept(ModUseItems.MAGIC_GEM_OF_MOONSTONE.get());
                        output.accept(ModUseItems.MAGIC_GEM_OF_AGATE.get());
                        output.accept(ModUseItems.MAGIC_GEM_OF_EMERALD.get());
                        output.accept(ModUseItems.MAGIC_GEM_OF_DIAMOND.get());

                        output.accept(ModUseItems.HOLY_GRAIL.get());
                        output.accept(ModUseItems.ROSARY.get());
                        output.accept(ModUseItems.CRAZY_MUSHROOM_STEW.get());
                        output.accept(ModCraftedBlocks.MOON_GLOW_STONE.get());
                        output.accept(ModUseItems.PHANTOM_TRIDENT.get());
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);

        eventBus.addListener(CreativeTabs::addCreativeTabs);
    }
}
