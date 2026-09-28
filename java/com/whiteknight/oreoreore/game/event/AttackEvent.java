package com.whiteknight.oreoreore.game.event;

import com.whiteknight.oreoreore.game.util.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.Optional;

public class AttackEvent {
    public static void onLivingDamage(LivingDamageEvent.Post event) {
        LivingEntity victim = event.getEntity();
        Entity source = event.getSource().getEntity();

        //Fire Auraの攻撃効果
        if (event.getSource().isDirect() && source instanceof LivingEntity attacker && attacker.level() instanceof ServerLevel serverLevel) {
            Registry<Enchantment> registry = serverLevel.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
            Optional<Holder.Reference<Enchantment>> auraEnchant = registry.getHolder(ModEnchantments.FIRE_AURA);

            if (auraEnchant.isPresent()) {
                Holder<Enchantment> blazingArmor = auraEnchant.get();
                boolean enchanted = false;

                for (ItemStack armorStack : attacker.getArmorSlots()) {
                    int stackLevel = EnchantmentHelper.getItemEnchantmentLevel(blazingArmor, armorStack);
                    enchanted |= stackLevel > 0;
                }

                if (enchanted) {
                    victim.igniteForSeconds(2f);
                }
            }
        }
    }
}
