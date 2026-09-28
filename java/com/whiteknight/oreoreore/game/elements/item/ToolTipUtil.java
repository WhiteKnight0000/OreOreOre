package com.whiteknight.oreoreore.game.elements.item;

import com.whiteknight.oreoreore.game.OreOreOre;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class ToolTipUtil {
    public static List<Component> shiftToolTip(Item selfItem, List<Component> tooltipComponents) {
        ResourceLocation registryName = BuiltInRegistries.ITEM.getKey(selfItem);
        String path = registryName.getPath();
        return shiftToolTip(path, tooltipComponents);
    }

    public static List<Component> shiftToolTip(Block selfBlock, List<Component> tooltipComponents) {
        ResourceLocation registryName = BuiltInRegistries.BLOCK.getKey(selfBlock);
        String path = registryName.getPath();
        return shiftToolTip(path, tooltipComponents);
    }

    private static List<Component> shiftToolTip(String path, List<Component> tooltipComponents) {
        String tooltipPath = "tooltip." + OreOreOre.MOD_ID + "." + path;
        if(!Language.getInstance().has(tooltipPath)){
            return tooltipComponents;
        }
        // Shiftキーが押されているか判定
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable(tooltipPath).withStyle(ChatFormatting.GRAY));
        } else {
            // 通常時のツールチップ（Shiftキーを促すメッセージ）
            tooltipComponents.add(Component.translatable("tooltip." + OreOreOre.MOD_ID +".hold_shift").withStyle(ChatFormatting.DARK_GRAY));
        }
        return tooltipComponents;
    }
}
