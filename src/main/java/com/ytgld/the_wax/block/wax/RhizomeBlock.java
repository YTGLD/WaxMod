package com.ytgld.the_wax.block.wax;

import com.ytgld.the_wax.block.init.BlockBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class RhizomeBlock extends BlockBase {

    public static final IntegerProperty offset = IntegerProperty.create("offset",0,3);

    public RhizomeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(offset, 0)
        );
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(offset);
    }
    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        return List.of(this.asItem().getDefaultInstance());
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return getStateWithConnections(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos(), this.defaultBlockState());
    }
    public static BlockState getStateWithConnections(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        int off = blockPos.hashCode();
        int is = off % 10;

        if (is < 0) {
            is = -is;
        }
        if (is > 3) {
            is = 1;
        }
        return blockState
                .trySetValue(offset,is);
    }
}
