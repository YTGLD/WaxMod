package com.ytgld.the_wax;

import com.ytgld.the_wax.block.init.BlockInit;
import com.ytgld.the_wax.feature.gen.TheConfiguredFeatures;
import com.ytgld.the_wax.feature.gen.TheFeatures;
import com.ytgld.the_wax.items.init.ItemInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaxMod implements ModInitializer {
	public static final String MOD_ID = "the_wax";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		BlockInit.init();
		ItemInit.init();
		TheFeatures.init();
		TheConfiguredFeatures.init();

		LootTableEvents.MODIFY.register((ResourceKey<LootTable> var1, LootTable.Builder var2, LootTableSource var3, HolderLookup.Provider var4)->{
			if (var1.equals(BuiltInLootTables.SNIFFER_DIGGING)){
				var2.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(BlockInit.NETHER_WAX_BASE)
								.when(LootItemRandomChanceCondition.randomChance(0.25f))));
			}
		});
		FuelRegistryEvents.BUILD.register((FuelValues.Builder var1, FuelRegistryEvents.Context var2) -> {
			var1.add(BlockInit.Wax, 500);
			var1.add(BlockInit.WAX_PINE, 600);
			var1.add(BlockInit.NETHER_WAX_BASE, 300);
			var1.add(BlockInit.HUGE_PIPE, 600);
			var1.add(BlockInit.PIPE_WALL, 100);
			var1.add(BlockInit.NETHER_WAX, 6000);
			var1.add(BlockInit.NETHER_PIPE, 2400);
			var1.add(BlockInit.NETHER_PIPE_OTHER, 2400);
		});
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(MOD_ID, "the_wax"), WaxTab.TEST_GROUP);
	}


}