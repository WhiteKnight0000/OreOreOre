package com.whiteknight.oreoreore.game;

import com.whiteknight.oreoreore.datagen.DataGenerator;
import com.whiteknight.oreoreore.game.event.*;
import com.whiteknight.oreoreore.game.util.ModDataMaps;
import com.whiteknight.oreoreore.game.util.ModRegistries;
import com.whiteknight.oreoreore.game.util.component.ModDataComponents;
import com.whiteknight.oreoreore.game.util.loot.LootModifiers;
import com.whiteknight.oreoreore.game.util.recipe.ModRecipes;
import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import static net.neoforged.fml.loading.FMLEnvironment.dist;

@Mod(OreOreOre.MOD_ID)
public class OreOreOre {
    public static final String MOD_ID = "oreoreore";
    public static final Logger LOGGER = LogUtils.getLogger();

    public OreOreOre(IEventBus modEventBus, ModContainer modContainer) {
        LootModifiers.register(modEventBus);

        modEventBus.addListener(CreativeTabs::addCreativeTabs);
        CreativeTabs.register(modEventBus);
        modEventBus.addListener(DataGenerator::gatherData);
        modEventBus.addListener(EntityRenderer::onRegisterRenderers);
        if (dist.isClient()) {
            modEventBus.addListener(ScreenRegister::registerScreens);
            NeoForge.EVENT_BUS.addListener(ToolTipEvent::onItemTooltip);
            NeoForge.EVENT_BUS.addListener(ActionTick::onClientTick);
        }
        modEventBus.addListener(ModDataMaps::onRegisterDataMapTypes);

        modEventBus.addListener(ServerClientPayloads::registerPayloads);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        ModDataComponents.register(modEventBus);
        ModRecipes.register(modEventBus);
        NeoForge.EVENT_BUS.addListener(ModBrewingRecipes::onRegisterBrewingRecipes);
        NeoForge.EVENT_BUS.addListener(AttackEvent::onLivingDamage);

        ModRegistries.register(modEventBus);
    }
}
