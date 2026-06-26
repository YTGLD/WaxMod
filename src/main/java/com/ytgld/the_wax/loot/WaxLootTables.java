package com.ytgld.the_wax.loot;

import com.ytgld.the_wax.WaxMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class WaxLootTables {
    public static ResourceKey<LootTable> MELON_ROOT = ResourceKey.create(Registries.LOOT_TABLE,
            Identifier.fromNamespaceAndPath(WaxMod.MOD_ID, "chests/melon_root"));

}
