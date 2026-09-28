package com.whiteknight.oreoreore.game.util.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.Optional;

public class SmeltBreakLootModifier extends LootModifier {
    public static final MapCodec<SmeltBreakLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            codecStart(inst).apply(inst, SmeltBreakLootModifier::new)
    );

    public SmeltBreakLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!context.hasParam(net.minecraft.world.level.storage.loot.parameters.LootContextParams.BLOCK_STATE)) {
            return generatedLoot;
        }

        var state = context.getParamOrNull(net.minecraft.world.level.storage.loot.parameters.LootContextParams.BLOCK_STATE);
        var tool = context.getParamOrNull(net.minecraft.world.level.storage.loot.parameters.LootContextParams.TOOL);
        boolean isCorrectTool = tool.isCorrectToolForDrops(state) || tool.getDestroySpeed(state) > 1.0F;

        // 適正ブロックではない（例: 斧で砂を掘った）場合はそのままドロップ
        if (!isCorrectTool) {
            return generatedLoot;
        }

        ObjectArrayList<ItemStack> newLoot = new ObjectArrayList<>();
        var level = context.getLevel();
        var recipeManager = level.getRecipeManager();

        for (ItemStack stack : generatedLoot) {
            // ドロップアイテムを入力として精錬(かまど)レシピを検索
            SingleRecipeInput input = new SingleRecipeInput(stack);
            Optional<RecipeHolder<SmeltingRecipe>> recipe = recipeManager.getRecipeFor(RecipeType.SMELTING, input, level);

            if (recipe.isPresent()) {
                ItemStack smelted = recipe.get().value().getResultItem(level.registryAccess()).copy();
                // 幸運エンチャントなどで複数ドロップしている場合に対応するため、個数を掛ける
                smelted.setCount(smelted.getCount() * stack.getCount());
                newLoot.add(smelted);
            } else {
                newLoot.add(stack);
            }
        }
        return newLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}