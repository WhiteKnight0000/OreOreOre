package com.whiteknight.oreoreore.game.util;

import com.whiteknight.oreoreore.game.OreOreOre;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

public class ModDataMaps {
    public static final DataMapType<Item, Integer> ENERGY_VALUE = DataMapType.builder(
            ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, "energy_value"),
            Registries.ITEM,
            Codec.INT
    ).synced(Codec.INT, false).build();

    public static void onRegisterDataMapTypes(RegisterDataMapTypesEvent event) {
        event.register(ENERGY_VALUE);
    }
}