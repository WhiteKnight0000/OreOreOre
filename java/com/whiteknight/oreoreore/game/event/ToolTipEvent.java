package com.whiteknight.oreoreore.game.event;

import com.whiteknight.oreoreore.game.util.ModDataMaps;
import com.whiteknight.oreoreore.game.util.ModTranslatable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public class ToolTipEvent {
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        // DataMapを持っているものにEnergyの表記
        if (stack.getItemHolder().getData(ModDataMaps.ENERGY_VALUE) instanceof Integer energy) {
            event.getToolTip().add(ModTranslatable.EARTH_ENERGY.getComponent()
                    .append(Component.literal(" : " + energy)));
        }
    }
}
