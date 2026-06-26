package com.ytgld.the_wax.block.wax_melon;

import com.ytgld.the_wax.block.init.BlockBase;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class WaxMelonStemPlack extends BlockBase {
    public WaxMelonStemPlack(Properties properties) {
        super(properties);
    }
    @Override
    protected List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(this.asItem()));
    }

}
