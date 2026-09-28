package com.whiteknight.oreoreore.datagen;

import com.whiteknight.oreoreore.game.OreOreOre;
import com.whiteknight.oreoreore.game.event.ModTags;
import com.whiteknight.oreoreore.game.register.ModCraftedBlocks;
import com.whiteknight.oreoreore.game.register.ModOres;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, OreOreOre.MOD_ID, existingFileHelper);
    }

    private void setupOresWithIngot(
            Supplier<? extends Block> ore,
            Supplier<? extends Block> deepslateOre,
            Supplier<? extends Block> ingotBlock,
            Supplier<? extends Block> rawBlock
    ){
        setupOresWithIngot(ore,deepslateOre,ingotBlock,rawBlock,BlockTags.NEEDS_IRON_TOOL);
    }

    private void setupOresWithIngot(
            Supplier<? extends Block> ore,
            Supplier<? extends Block> deepslateOre,
            Supplier<? extends Block> ingotBlock,
            Supplier<? extends Block> rawBlock,
            TagKey<Block> toolGrade
    ){
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(deepslateOre.get())
                .add(ore.get())
                .add(ingotBlock.get())
                .add(rawBlock.get());
        tag(toolGrade)
                .add(deepslateOre.get())
                .add(ore.get())
                .add(ingotBlock.get())
                .add(rawBlock.get());
        this.tag(Tags.Blocks.ORES)
                .add(ore.get(), deepslateOre.get());
    }

    private void setupGemOres(
            Supplier<? extends Block> ore,
            Supplier<? extends Block> deepslateOre,
            Supplier<? extends Block> storageBlock
    ){
        setupGemOres(ore,deepslateOre,storageBlock,BlockTags.NEEDS_IRON_TOOL);
    }

    private void setupGemOres(
            Supplier<? extends Block> ore,
            Supplier<? extends Block> deepslateOre,
            Supplier<? extends Block> storageBlock,
            TagKey<Block> toolGrade
    ){
        setupGemOres(List.of(ore,deepslateOre), storageBlock, toolGrade);
    }

    // インゴットを経由しない鉱石（宝石類や石炭など）用のタグ設定メソッド
    private void setupGemOres(
            List<Supplier<? extends Block>> ores,
            Supplier<? extends Block> storageBlock,
            TagKey<Block> toolGrade
    ){
        for(var ore : ores){
            tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(ore.get());
            tag(toolGrade)
                    .add(ore.get());
            tag(Tags.Blocks.ORES)
                    .add(ore.get());
        }

        // ツルハシで採掘可能なブロックとして登録
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(storageBlock.get());

        // 採掘に必要なツールのレベルを設定
        tag(toolGrade)
                .add(storageBlock.get());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        setupOresWithIngot(
                ModOres.SILVER_ORE,
                ModOres.DEEPSLATE_SILVER_ORE,
                ModOres.SILVER_BLOCK,
                ModOres.RAW_SILVER_BLOCK
        );
        setupOresWithIngot(
                ModOres.MITHRIL_ORE,
                ModOres.DEEPSLATE_MITHRIL_ORE,
                ModOres.MITHRIL_BLOCK,
                ModOres.RAW_MITHRIL_BLOCK
        );
        setupGemOres(ModOres.AQUAMARINE_ORE, ModOres.DEEPSLATE_AQUAMARINE_ORE, ModOres.AQUAMARINE_BLOCK, BlockTags.NEEDS_IRON_TOOL);
        setupGemOres(ModOres.ONYX_ORE, ModOres.DEEPSLATE_ONYX_ORE, ModOres.ONYX_BLOCK, BlockTags.NEEDS_DIAMOND_TOOL);
        setupGemOres(ModOres.TOPAZ_ORE, ModOres.DEEPSLATE_TOPAZ_ORE, ModOres.TOPAZ_BLOCK, BlockTags.NEEDS_DIAMOND_TOOL);
        setupGemOres(ModOres.OPAL_ORE, ModOres.DEEPSLATE_OPAL_ORE, ModOres.OPAL_BLOCK, BlockTags.NEEDS_IRON_TOOL);
        setupGemOres(ModOres.SAPPHIRE_ORE, ModOres.DEEPSLATE_SAPPHIRE_ORE, ModOres.SAPPHIRE_BLOCK, BlockTags.NEEDS_DIAMOND_TOOL);
        setupGemOres(ModOres.SPINEL_ORE, ModOres.DEEPSLATE_SPINEL_ORE, ModOres.SPINEL_BLOCK, BlockTags.NEEDS_IRON_TOOL);
        setupGemOres(ModOres.MOONSTONE_ORE, ModOres.DEEPSLATE_MOONSTONE_ORE, ModOres.MOONSTONE_BLOCK, BlockTags.NEEDS_IRON_TOOL);
        setupGemOres(ModOres.AGATE_ORE, ModOres.DEEPSLATE_AGATE_ORE, ModOres.AGATE_BLOCK, BlockTags.NEEDS_IRON_TOOL);
        setupGemOres(ModOres.SKYTHIUM_ORE, ModOres.DEEPSLATE_SKYTHIUM_ORE, ModOres.SKYTHIUM_BLOCK, BlockTags.NEEDS_IRON_TOOL);

        setupGemOres(List.of(ModOres.NETHER_GARNET_ORE),ModOres.GARNET_BLOCK, BlockTags.NEEDS_IRON_TOOL);
        setupGemOres(List.of(ModOres.BASALT_PERIDOT_ORE,ModOres.BLACKSTONE_PERIDOT_ORE),ModOres.PERIDOT_BLOCK, BlockTags.NEEDS_IRON_TOOL);

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModCraftedBlocks.MOON_GLOW_STONE.get());

        tag(ModTags.Blocks.MINABLE_WITH_PAXEL)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(BlockTags.MINEABLE_WITH_SHOVEL);
    }
}
