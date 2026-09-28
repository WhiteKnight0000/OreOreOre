package com.whiteknight.oreoreore.datagen;

import com.whiteknight.oreoreore.game.OreOreOre;
import com.whiteknight.oreoreore.game.register.ModOres;
import com.whiteknight.oreoreore.game.register.ModEquips;
import com.whiteknight.oreoreore.game.util.loot.AddPoolLootModifier;
import com.whiteknight.oreoreore.game.util.loot.SmeltBreakLootModifier;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, OreOreOre.MOD_ID);
    }

    /**
     * 鉄インゴットなどと同様のレア度として配置されるルートテーブル(mod追加差分)を生成し返す
     * @param coef
     * @return 生成されたルートテーブル
     */
    private LootPool getCommonLootPool(float coef){
        return LootPool.lootPool()
                .setRolls(UniformGenerator.between(2.0F*coef, 4.0F*coef))
                .add(
                        LootItem.lootTableItem(ModOres.SILVER_INGOT.get())
                                .setWeight(10)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                )
                .add(
                        LootItem.lootTableItem(ModOres.SPINEL.get())
                                .setWeight(2)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(
                        EmptyLootItem.emptyItem()
                                .setWeight(86)
                )
                .build();
    }

    /**
     * 各地のチェストの中身に配置される鉱石群について基本的な実装を行う
     */
    private void setUpChestStandard(){
        // Monster Room
        this.add("simple_dungeon", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/simple_dungeon")).build()
                },
                getCommonLootPool(1f)
        ));

        // Mineshaft
        this.add("abandoned_mineshaft", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/abandoned_mineshaft")).build()
                },
                getCommonLootPool(1f)
        ));

        // Bastion Remnant (Bridge chest)
        this.add("bastion_bridge", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/bastion_bridge")).build()
                },
                getCommonLootPool(1f)
        ));

        // Bastion Remnant (Generic chest)
        this.add("bastion_other", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/bastion_other")).build()
                },
                getCommonLootPool(1f)
        ));

        // Bastion Remnant (Treasure chest)
        this.add("bastion_treasure", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/bastion_treasure")).build()
                },
                getCommonLootPool(1f)
        ));

        // Buried Treasure
        this.add("buried_treasure", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/buried_treasure")).build()
                },
                getCommonLootPool(1f)
        ));

        // Desert Pyramid
        this.add("desert_pyramid", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/desert_pyramid")).build()
                },
                getCommonLootPool(1f)
        ));

        // End City
        this.add("end_city_treasure", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/end_city_treasure")).build()
                },
                getCommonLootPool(1f)
        ));

        // Jungle Pyramid
        this.add("jungle_temple", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/jungle_temple")).build()
                },
                getCommonLootPool(1f)
        ));

        // Nether Fortress
        this.add("nether_bridge", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/nether_bridge")).build()
                },
                getCommonLootPool(1f)
        ));

        // Pillager Outpost
        this.add("pillager_outpost", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/pillager_outpost")).build()
                },
                getCommonLootPool(1f)
        ));

        // Shipwreck (Treasure chest)
        this.add("shipwreck_treasure", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/shipwreck_treasure")).build()
                },
                getCommonLootPool(1f)
        ));

        // Stronghold (Altar chest)
        this.add("stronghold_crossing", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/stronghold_crossing")).build()
                },
                getCommonLootPool(1f)
        ));

        // Stronghold (Storeroom chest)
        this.add("stronghold_corridor", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/stronghold_corridor")).build()
                },
                getCommonLootPool(1f)
        ));

        // Trial Chambers (Corridor pot)
        this.add("trial_chambers_corridor_pot", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("pots/trial_chambers/corridor")).build()
                },
                getCommonLootPool(1f)
        ));

        // Trial Chambers (Vault and reward chest)
        this.add("trial_chambers_reward", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/trial_chambers/reward")).build()
                },
                getCommonLootPool(1f)
        ));

        // Village (Toolsmith's chest)
        this.add("village_toolsmith", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village/village_toolsmith")).build()
                },
                getCommonLootPool(1f)
        ));

        // Village (Weaponsmith's chest)
        this.add("village_weaponsmith", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village/village_weaponsmith")).build()
                },
                getCommonLootPool(1f)
        ));

        // Village (Armorer's chest)
        this.add("village_armorer", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/village/village_armorer")).build()
                },
                getCommonLootPool(1f)
        ));

        // Woodland Mansion
        this.add("woodland_mansion", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/woodland_mansion")).build()
                },
                getCommonLootPool(1f)
        ));
    }

    @Override
    protected void start() {
        setUpChestStandard();

        LootItemCondition.Builder toolCondition = MatchTool.toolMatches(
                ItemPredicate.Builder.item().of(ModEquips.CRIMSON_AXE.get())
        );

        add("auto_smelt_axe", new SmeltBreakLootModifier(
                new LootItemCondition[]{ toolCondition.build() }
        ));

        this.add("fishing_treasure", new AddPoolLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("gameplay/fishing/treasure")).build()
                },
                LootPool.lootPool()
                        .setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .add(
                                LootItem.lootTableItem(ModOres.PEARL.get())
                                        .setWeight(1)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                        )
                        .add(
                                EmptyLootItem.emptyItem()
                                        .setWeight(2)
                        )
                        .build()
        ));
    }
}
