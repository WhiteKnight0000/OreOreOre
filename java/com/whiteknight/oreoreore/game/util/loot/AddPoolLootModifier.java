package com.whiteknight.oreoreore.game.util.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class AddPoolLootModifier extends LootModifier {
    public static final MapCodec<AddPoolLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            codecStart(inst).and(
                    LootPool.CODEC.fieldOf("pool").forGetter(m -> m.pool)
            ).apply(inst, AddPoolLootModifier::new)
    );

    private final LootPool pool;

    public AddPoolLootModifier(LootItemCondition[] conditionsIn, LootPool pool) {
        super(conditionsIn);
        this.pool = pool;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        // バニラのLootPoolの抽選ロジックを実行し、生成されたアイテムを generatedLoot に追加する
        this.pool.addRandomItems(generatedLoot::add, context);
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
