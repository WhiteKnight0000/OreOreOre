package com.whiteknight.oreoreore.game.util.recipe;

import com.whiteknight.oreoreore.game.util.recipe.modifier.AnyPotionModifier;
import com.whiteknight.oreoreore.game.util.recipe.modifier.ChargeModifier;
import com.whiteknight.oreoreore.game.util.recipe.modifier.EnchantmentModifier;
import com.whiteknight.oreoreore.game.util.recipe.modifier.RemainItemModifier;
import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class ModifierCodecs {
    public static final Codec<RecipeModifier> DISPATCH_CODEC = Codec.STRING.dispatch(
            RecipeModifier::getType,
            type -> switch (type) {
                case "charge" -> ChargeModifier.CODEC;
                case "enchant" -> EnchantmentModifier.CODEC;
                case "any_potion" -> AnyPotionModifier.CODEC;
                case "remain_item" -> RemainItemModifier.CODEC;
                default -> throw new IllegalArgumentException("Unknown modifier type: " + type);
            }
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, RecipeModifier> STREAM_CODEC = ByteBufCodecs.STRING_UTF8.<RegistryFriendlyByteBuf>cast().dispatch(
            RecipeModifier::getType,
            type -> switch (type) {
                case "charge" -> ChargeModifier.STREAM_CODEC;
                case "enchant" -> EnchantmentModifier.STREAM_CODEC;
                case "any_potion" -> AnyPotionModifier.STREAM_CODEC;
                case "remain_item" -> RemainItemModifier.STREAM_CODEC;
                default -> throw new IllegalArgumentException("Unknown modifier type: " + type);
            }
    );
}
