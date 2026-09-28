package com.whiteknight.oreoreore.datagen;

import com.whiteknight.oreoreore.game.OreOreOre;
import com.whiteknight.oreoreore.game.register.ModOres;
import com.whiteknight.oreoreore.game.register.ModRitualItems;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.HashSet;
import java.util.Set;

/**
 * Blockの見た目を指定する
 * blockstateに応じた指定も含む
 */
public class ModBlockStateProvider extends BlockStateProvider {
    private final ExistingFileHelper fileHelper;

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, OreOreOre.MOD_ID, exFileHelper);
        this.fileHelper = exFileHelper;
    }

    private final Set<Block> customBlockStates = new HashSet<>();
    private final Set<Block> customItemModels = new HashSet<>();

    @Override
    protected void registerStatesAndModels() {
        axisBlock(
                (RotatedPillarBlock) ModOres.BASALT_PERIDOT_ORE.get(),
                modLoc("block/basalt_peridot_ore_side"),
                modLoc("block/basalt_peridot_ore_top")
        );
        simpleBlockItem(ModOres.BASALT_PERIDOT_ORE.get(), models().getExistingFile(modLoc("block/basalt_peridot_ore")));
        customBlockStates.add(ModOres.BASALT_PERIDOT_ORE.get());
        customItemModels.add(ModOres.BASALT_PERIDOT_ORE.get());

        ResourceLocation altarTextureLoc = modLoc("block/earth_altar");

        BlockModelBuilder altarModel = models().getBuilder("earth_altar")
                .renderType("minecraft:cutout") // 透過テクスチャ用
                .texture("particle", altarTextureLoc)
                .texture("texture", altarTextureLoc)
                .element()
                // 座標の指定 (x, y, z)
                .from(0f, 0.25f, 0f)
                .to(16f, 0.25f, 16f)
                // 上面のテクスチャ指定
                .face(Direction.UP)
                .uvs(0f, 0f, 16f, 16f)
                .texture("#texture")
                .end()
                // 下面のテクスチャ指定 (浮いた際に見える場合を考慮)
                .face(Direction.DOWN)
                .uvs(0f, 0f, 16f, 16f)
                .texture("#texture")
                .end()
                .end();

        simpleBlock(ModRitualItems.EARTH_ALTAR.get(), altarModel);
        customBlockStates.add(ModRitualItems.EARTH_ALTAR.get());
        customItemModels.add(ModRitualItems.EARTH_ALTAR.get());

        String aquamarineBlockName = ModOres.AQUAMARINE_BLOCK.getRegisteredName().split(":",2)[1];
        ModelFile aquamarineBlockModel = models().cubeAll(aquamarineBlockName, modLoc("block/" + aquamarineBlockName)).renderType("minecraft:translucent");
        simpleBlockWithItem(ModOres.AQUAMARINE_BLOCK.get(),aquamarineBlockModel);
        customBlockStates.add(ModOres.AQUAMARINE_BLOCK.get());
        customItemModels.add(ModOres.AQUAMARINE_BLOCK.get());



        ExistingFileHelper.ResourceType textureType = new ExistingFileHelper.ResourceType(PackType.CLIENT_RESOURCES, ".png", "textures");
        for (DeferredHolder<Block, ? extends Block> holder : DataGenLibrary.getKnownBlocks()) {
            Block block = holder.get();
            String blockName = holder.getId().getPath();
            if (!customBlockStates.contains(block)) {
                if(fileHelper.exists(modLoc("block/" + blockName), textureType)){
                    simpleBlock(block);
                }else{
                    simpleBlock(block, models().cubeAll(blockName, modLoc("block/default_texture")));
                }
            }
            if (!customItemModels.contains(block)) {
                simpleBlockItem(block, new ModelFile.UncheckedModelFile(modLoc("block/" + holder.getId().getPath())));
            }
        }
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile(OreOreOre.MOD_ID + ":block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile(OreOreOre.MOD_ID + ":block/" + deferredBlock.getId().getPath() + appendix));
    }
}
