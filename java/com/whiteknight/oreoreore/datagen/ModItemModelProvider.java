package com.whiteknight.oreoreore.datagen;

import com.whiteknight.oreoreore.game.OreOreOre;
import com.whiteknight.oreoreore.game.register.ModEquips;
import com.whiteknight.oreoreore.game.register.ModRitualItems;
import net.minecraft.data.PackOutput;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.HashSet;
import java.util.Set;

/**
 * アイテムの見た目を指定する
 */
public class ModItemModelProvider extends ItemModelProvider {
    private final Set<Item> customModels = new HashSet<>();
    private final ExistingFileHelper fileHelper;

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, OreOreOre.MOD_ID, existingFileHelper);
        this.fileHelper = existingFileHelper;
    }

    @Override
    protected void registerModels() {
        singleTexture("silver_sword",
                mcLoc("item/handheld"),
                "layer0",
                modLoc("item/silver_sword"));
        customModels.add(ModEquips.SILVER_SWORD.get());
        singleTexture("onyx_dagger",
                mcLoc("item/handheld"),
                "layer0",
                modLoc("item/onyx_dagger"));
        customModels.add(ModEquips.ONYX_DAGGER.get());
        singleTexture(
                ModRitualItems.EARTH_ALTAR.getId().getPath(),
                mcLoc("item/generated"),
                "layer0",
                modLoc("block/earth_altar")
        );

        ExistingFileHelper.ResourceType textureType = new ExistingFileHelper.ResourceType(PackType.CLIENT_RESOURCES, ".png", "textures");
        for (var entry : DataGenLibrary.getKnownItems()) {
            Item item = entry.get();
            if (item instanceof BlockItem || customModels.contains(item)) {
                continue;
            }
            String itemName = entry.getId().getPath();
            if (fileHelper.exists(modLoc("item/" + itemName), textureType)) {
                basicItem(item);
            } else {
                getBuilder(itemName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", modLoc("item/default_texture"));
            }
        }
    }
}
