package com.whiteknight.oreoreore.game.elements.item;

import com.whiteknight.oreoreore.game.util.ModTranslatable;
import com.whiteknight.oreoreore.game.util.component.ModDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class EarthFaith extends Item {
    protected int max_charge = 100;

    public EarthFaith(Properties properties, int max_charge) {
        super(properties.stacksTo(1));
        this.max_charge = max_charge;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(stack.get(ModDataComponents.ENERGY) != null) {
            if(stack.get(ModDataComponents.ENERGY) > max_charge){
                tooltipComponents.add(ModTranslatable.OVER_CAPACITY.getComponent());
            }
            tooltipComponents.add(ModTranslatable.CHARGED_ENERGY.getComponent()
                    .append(Component.literal(" : " + stack.get(ModDataComponents.ENERGY) + "/" + max_charge)));
        }

        super.appendHoverText(stack, context, ToolTipUtil.shiftToolTip(this, tooltipComponents), tooltipFlag);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        int currentCharge = stack.getOrDefault(ModDataComponents.ENERGY, 0);

        return Math.round(13.0F * currentCharge / max_charge);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0x59FF0C;
    }

    public int getMaxCharge(){
        return max_charge;
    }
}
