package com.whiteknight.oreoreore.game.elements.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import java.util.List;

public class AxeItemWithToolTip extends AxeItem {
    public AxeItemWithToolTip(Tier p_40521_, Properties p_40524_) {
        super(p_40521_, p_40524_);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, ToolTipUtil.shiftToolTip(this, tooltipComponents), tooltipFlag);
    }
}
