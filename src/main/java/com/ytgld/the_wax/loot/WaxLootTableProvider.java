package com.ytgld.the_wax.loot;

import com.ytgld.the_wax.block.init.BlockInit;
import com.ytgld.the_wax.items.init.ItemInit;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class WaxLootTableProvider extends SimpleFabricLootTableSubProvider {
    public WaxLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.CHEST);
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
        lootTableBiConsumer.accept(WaxLootTables.MELON_ROOT, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(2))
                        .add(LootItem.lootTableItem(Items.STICK)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(8))))
                        .add(LootItem.lootTableItem(Items.RAW_IRON)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4))))
                        .add(LootItem.lootTableItem(Items.RAW_COPPER)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(6))))
                        .add(LootItem.lootTableItem(Items.GOLD_NUGGET)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                        .add(LootItem.lootTableItem(Items.COAL)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))))
                        .add(LootItem.lootTableItem(Items.IRON_NUGGET)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))))
                        .add(LootItem.lootTableItem(BlockInit.WaxMelonStemSeed_)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))))
                ));
    }
}