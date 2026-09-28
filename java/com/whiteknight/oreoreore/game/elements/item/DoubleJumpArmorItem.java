package com.whiteknight.oreoreore.game.elements.item;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import java.util.List;

public class DoubleJumpArmorItem extends ArmorItem {
    public float jump_power = 1f;

    public DoubleJumpArmorItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Item.Properties properties, float jump_power) {
        super(material, type, properties);
        this.jump_power = jump_power;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, ToolTipUtil.shiftToolTip(this, tooltipComponents), tooltipFlag);
    }
}
