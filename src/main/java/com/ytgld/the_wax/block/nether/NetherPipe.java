package com.ytgld.the_wax.block.nether;

import com.ytgld.the_wax.block.init.BlockBase;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class NetherPipe extends BlockBase {
    public NetherPipe(Properties properties) {
        super(properties);
    }
    @Override
    protected List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(this.asItem()));
    }

}
