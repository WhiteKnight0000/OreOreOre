package com.whiteknight.oreoreore.game.util;

import com.whiteknight.oreoreore.game.OreOreOre;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.Ignite;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;

public class ModEnchantments {
    //関連 : AttackEvent
    public static final ResourceKey<Enchantment> FIRE_AURA = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(OreOreOre.MOD_ID, "fire_aura")
    );

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> itemGetter = context.lookup(Registries.ITEM);

        context.register(
                FIRE_AURA,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        itemGetter.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                                        HolderSet.direct(),
                                        2,
                                        1,
                                        Enchantment.dynamicCost(10, 0),
                                        Enchantment.dynamicCost(50, 0),
                                        1,
                                        EquipmentSlotGroup.ARMOR
                                )
                        )
                        .withEffect(EnchantmentEffectComponents.TICK, new Ignite(LevelBasedValue.constant(2.0F)))
                        .withEffect(
                                EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.VICTIM,
                                EnchantmentTarget.ATTACKER,
                                new Ignite(LevelBasedValue.constant(2.0F)),
                                DamageSourceCondition.hasDamageSource(
                                        DamageSourcePredicate.Builder.damageType().isDirect(true)
                                )
                        )
                        .build(FIRE_AURA.location())
        );
    }
}
