package com.whiteknight.oreoreore.game.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum ModTranslatable {
    CREATIVETAB_NAME("creativetab.oreoreore.oreoreore_tab"),
    EARTH_ENERGY("tooltip.oreoreore.earth_energy", "Earth Energy"),
    CHARGED_ENERGY("tooltip.oreoreore.charged_energy", "Charged"),
    OVER_CAPACITY("tooltip.oreoreore.over_capacity", "Capacity Over"),
    EARTH_ALTAR("tooltip.oreoreore.earth_altar_gui");

    private final String key;
    private final String defaultTranslation;

    ModTranslatable(String key, String defaultTranslation) {
        this.key = key;
        this.defaultTranslation = defaultTranslation;
    }

    ModTranslatable(String key) {
        this.key = key;
        this.defaultTranslation = formatName(key);
    }

    private String formatName(String id){
        String id_end = Arrays.stream(id.split("\\.")).toList().getLast();
        return Arrays.stream(id_end.split("_"))
                .map(word -> word.isEmpty() ? "" : Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }

    public String getKey() {
        return key;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public MutableComponent getComponent() {
        return Component.translatable(this.key);
    }

    public MutableComponent getComponent(Object... args) {
        return Component.translatable(this.key, args);
    }
}
