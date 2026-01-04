package com.ytgld.the_wax.items;

import com.ytgld.the_wax.block.SmallWax;
import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class GoldWax extends BlockItem {
    public GoldWax( Properties properties) {
        super(BlockInit.SMALL_WAX_GOLDEN, properties);
    }
}

