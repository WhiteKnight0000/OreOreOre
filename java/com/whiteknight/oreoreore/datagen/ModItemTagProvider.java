package com.whiteknight.oreoreore.datagen;

import com.whiteknight.oreoreore.game.OreOreOre;
import com.whiteknight.oreoreore.game.register.ModRitualItems;
import com.whiteknight.oreoreore.game.event.ModTags;
import com.whiteknight.oreoreore.game.register.ModOres;
import com.whiteknight.oreoreore.game.register.ModEquips;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, OreOreOre.MOD_ID, existingFileHelper);
    }

    protected void addOreTags(
            Supplier<? extends Item> ingot,
            Supplier<? extends Item> rawMaterial,
            Supplier<? extends Item> nugget,
            Supplier<? extends Block> ore,
            Supplier<? extends Block> deepslateOre) {

        this.tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ingot.get());
        this.tag(Tags.Items.INGOTS)
                .add(ingot.get());

        this.tag(Tags.Items.RAW_MATERIALS)
                .add(rawMaterial.get());

        this.tag(Tags.Items.NUGGETS)
                .add(nugget.get());

        this.tag(Tags.Items.ORES)
                .add(ore.get().asItem(), deepslateOre.get().asItem());
    }

    protected void addGemOreTags(
            Supplier<? extends Item> gem,
            Supplier<? extends Block> ore,
            Supplier<? extends Block> deepslateOre
    ){
        addGemOreTags(gem, List.of(ore, deepslateOre));
    }

    protected void addGemOreTags(
            Supplier<? extends Item> gem,
            List<Supplier<? extends Block>> ores) {

        this.tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(gem.get());

        this.tag(Tags.Items.GEMS)
                .add(gem.get());

        for(var ore : ores){
            this.tag(Tags.Items.ORES)
                    .add(ore.get().asItem());
        }
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.CHARGE_ITEM)
                .add(ModRitualItems.FRESH_GREEN_FAITH.get())
                .add(ModRitualItems.NATURE_FAITH.get())
                .add(ModRitualItems.JADE_FAITH.get());

        this.tag(ItemTags.SWORDS)
                .add(ModEquips.SILVER_SWORD.get())
                .add(ModEquips.ONYX_DAGGER.get());

        this.tag(ItemTags.AXES)
                .add(ModEquips.CRIMSON_AXE.get())
                .add(ModEquips.TOPAZ_AXE.get());

        this.tag(ItemTags.PICKAXES)
                .add(ModEquips.TOPAZ_PICKAXE.get());

        this.tag(ItemTags.SHOVELS)
                .add(ModEquips.TOPAZ_SHOVEL.get());

        tag(ItemTags.HEAD_ARMOR)
                .add(ModEquips.MITHRIL_HELMET.get());
        tag(ItemTags.CHEST_ARMOR)
                .add(ModEquips.MITHRIL_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR)
                .add(ModEquips.MITHRIL_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR)
                .add(ModEquips.MITHRIL_BOOTS.get());

        this.tag(ItemTags.FOOT_ARMOR)
                .add(ModEquips.BLAST_JUMPER.get())
                .add(ModEquips.SKY_WALKER.get());

        addOreTags(
                ModOres.SILVER_INGOT,
                ModOres.RAW_SILVER,
                ModOres.SILVER_NUGGET,
                ModOres.SILVER_ORE,
                ModOres.DEEPSLATE_SILVER_ORE
        );
        addOreTags(
                ModOres.MITHRIL_INGOT,
                ModOres.RAW_MITHRIL,
                ModOres.MITHRIL_NUGGET,
                ModOres.MITHRIL_ORE,
                ModOres.DEEPSLATE_MITHRIL_ORE
        );
        addGemOreTags(ModOres.AQUAMARINE, ModOres.AQUAMARINE_ORE, ModOres.DEEPSLATE_AQUAMARINE_ORE);
        addGemOreTags(ModOres.ONYX, ModOres.ONYX_ORE, ModOres.DEEPSLATE_ONYX_ORE);
        addGemOreTags(ModOres.TOPAZ, ModOres.TOPAZ_ORE, ModOres.DEEPSLATE_TOPAZ_ORE);
        addGemOreTags(ModOres.OPAL, ModOres.OPAL_ORE, ModOres.DEEPSLATE_OPAL_ORE);
        addGemOreTags(ModOres.SAPPHIRE, ModOres.SAPPHIRE_ORE, ModOres.DEEPSLATE_SAPPHIRE_ORE);
        addGemOreTags(ModOres.SPINEL, ModOres.SPINEL_ORE, ModOres.DEEPSLATE_SPINEL_ORE);
        addGemOreTags(ModOres.MOONSTONE, ModOres.MOONSTONE_ORE, ModOres.DEEPSLATE_MOONSTONE_ORE);
        addGemOreTags(ModOres.AGATE, ModOres.AGATE_ORE, ModOres.DEEPSLATE_AGATE_ORE);
        addGemOreTags(ModOres.GARNET, List.of(ModOres.NETHER_GARNET_ORE));
        addGemOreTags(ModOres.PERIDOT, List.of(ModOres.BASALT_PERIDOT_ORE, ModOres.BLACKSTONE_PERIDOT_ORE));
        addGemOreTags(ModOres.SKYTHIUM, ModOres.SKYTHIUM_ORE, ModOres.DEEPSLATE_SKYTHIUM_ORE);
    }
}
