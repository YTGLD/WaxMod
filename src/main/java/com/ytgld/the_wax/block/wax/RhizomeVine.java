package com.ytgld.the_wax.block.wax;

import com.mojang.serialization.MapCodec;
import com.ytgld.the_wax.block.BlockInit;
import com.ytgld.the_wax.items.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class RhizomeVine extends PipeBlock {
    public static final MapCodec<RhizomeVine> CODEC = simpleCodec(RhizomeVine::new);

    @Override
    public MapCodec<RhizomeVine> codec() {
        return CODEC;
    }

    public RhizomeVine(BlockBehaviour.Properties properties) {
        super(10.0F, properties);

        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(NORTH, false)
                        .setValue(EAST, false)
                        .setValue(SOUTH, false)
                        .setValue(WEST, false)
                        .setValue(UP, false)
                        .setValue(DOWN, false)
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return getStateWithConnections(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos(), this.defaultBlockState());
    }

    public BlockState getStateWithConnections(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        BlockState blockState2 = blockGetter.getBlockState(blockPos.below());
        BlockState blockState3 = blockGetter.getBlockState(blockPos.above());
        BlockState blockState4 = blockGetter.getBlockState(blockPos.north());
        BlockState blockState5 = blockGetter.getBlockState(blockPos.east());
        BlockState blockState6 = blockGetter.getBlockState(blockPos.south());
        BlockState blockState7 = blockGetter.getBlockState(blockPos.west());

        return blockState.trySetValue(DOWN, blockState2.isSolid() || blockState2.is(this))
                .trySetValue(UP, blockState3.isSolid() || blockState3.is(this) )
                .trySetValue(NORTH, blockState4.isSolid() || blockState4.is(this) )
                .trySetValue(EAST, blockState5.isSolid() || blockState5.is(this) )
                .trySetValue(SOUTH, blockState6.isSolid() || blockState6.is(this) )
                .trySetValue(WEST, blockState7.isSolid() || blockState7.is(this) )
                ;
    }
    @Override
    protected List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(ItemInit.RichInFlourRhizomes_,2));
    }

    @Override
    protected BlockState updateShape(
            BlockState blockState,
            LevelReader levelReader,
            ScheduledTickAccess scheduledTickAccess,
            BlockPos blockPos,
            Direction direction,
            BlockPos blockPos2,
            BlockState blockState_,
            RandomSource randomSource
    ) {
        BlockState blockState2 = levelReader.getBlockState(blockPos.below());
        BlockState blockState3 = levelReader.getBlockState(blockPos.above());
        BlockState blockState4 = levelReader.getBlockState(blockPos.north());
        BlockState blockState5 = levelReader.getBlockState(blockPos.east());
        BlockState blockState6 = levelReader.getBlockState(blockPos.south());
        BlockState blockState7 = levelReader.getBlockState(blockPos.west());

        return blockState.trySetValue(DOWN, blockState2.isSolid() || blockState2.is(this))
                .trySetValue(UP, blockState3.isSolid() || blockState3.is(this) )
                .trySetValue(NORTH, blockState4.isSolid() || blockState4.is(this) )
                .trySetValue(EAST, blockState5.isSolid() || blockState5.is(this) )
                .trySetValue(SOUTH, blockState6.isSolid() || blockState6.is(this) )
                .trySetValue(WEST, blockState7.isSolid() || blockState7.is(this) )
                ;
    }

    @Override
    protected void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (!blockState.canSurvive(serverLevel, blockPos)) {
            serverLevel.destroyBlock(blockPos, true);
        }
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }
}


