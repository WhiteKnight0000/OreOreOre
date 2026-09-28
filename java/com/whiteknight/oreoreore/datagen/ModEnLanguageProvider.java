package com.whiteknight.oreoreore.datagen;

import com.whiteknight.oreoreore.game.util.ModRegistries;
import com.whiteknight.oreoreore.game.util.ModTranslatable;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ModEnLanguageProvider extends LanguageProvider {
    private static final String LOCALE = "en_us";

    public ModEnLanguageProvider(PackOutput output, String modId) {
        super(output, modId, LOCALE);
        loadManualTranslations(modId, LOCALE);
    }

    private final Map<String, String> manualTranslations = new HashMap<>();

    /**
     * クラスパスから手動で用意したJSONファイルを読み込む
     */
    private void loadManualTranslations(String modid, String locale) {
        String resourcePath = "/assets/" + modid + "/lang/manual_" + locale + ".json";

        try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
            if (is != null) {
                try (InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                    JsonObject json = new Gson().fromJson(reader, JsonObject.class);
                    json.entrySet().forEach(entry ->
                            manualTranslations.put(entry.getKey(), entry.getValue().getAsString())
                    );
                }
            } else {
                System.out.println("手動翻訳ファイルが見つからないためスキップします: " + resourcePath);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load manual translations for " + locale, e);
        }
    }

    /**
     * 通常の翻訳追加処理に加えて、手動翻訳をチェックし、存在すればそれを優先する処理を行う
     */
    @Override
    public void add(String key, String value) {
        if (manualTranslations.containsKey(key)) {
            super.add(key, manualTranslations.remove(key));
        } else {
            super.add(key, value);
        }
    }

    @Override
    protected void addTranslations() {
        for (var info : DataGenLibrary.getKnownBlocks()) {
            Block block = info.get();
            add(block, formatName(info.getId().getPath()));
        }
        for (var info : DataGenLibrary.getKnownItems()) {
            Item item = info.get();
            if(item instanceof BlockItem){
                continue;
            }
            add(item, formatName(info.getId().getPath()));
        }

        for (ModTranslatable message : ModTranslatable.values()) {
            add(message.getKey(), message.getDefaultTranslation());
        }

        for (var entry : ModRegistries.MOB_EFFECTS.getEntries()) {
            String englishName = formatName(entry.getId().getPath());
            add(entry.get(), englishName);
        }

        for (var entry : ModRegistries.POTIONS.getEntries()) {
            String path = entry.getId().getPath();

            String baseName = formatName(path.replace("_potion", ""));

            add("item.minecraft.potion.effect." + path, "Potion of " + baseName);
            add("item.minecraft.splash_potion.effect." + path, "Splash Potion of " + baseName);
            add("item.minecraft.lingering_potion.effect." + path, "Lingering Potion of " + baseName);
            add("item.minecraft.tipped_arrow.effect." + path, "Arrow of " + baseName);
        }

        for (Map.Entry<String, String> entry : manualTranslations.entrySet()) {
            super.add(entry.getKey(), entry.getValue());
        }
    }

    private String formatName(String id) {
        return Arrays.stream(id.split("_"))
                .map(word -> word.isEmpty() ? "" : Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }

}
