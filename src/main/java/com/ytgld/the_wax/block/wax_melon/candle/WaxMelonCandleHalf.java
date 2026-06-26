package com.ytgld.the_wax.block.wax_melon.candle;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class WaxMelonCandleHalf extends SlabBlock {
    public WaxMelonCandleHalf(Properties properties) {
        super(properties);
    }
    @Override
    protected List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        if (blockState.getValue(TYPE).equals(SlabType.DOUBLE)) {
            return List.of(new ItemStack(this,2));
        }
        return List.of(new ItemStack(this));
    }

}
