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
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class RhizomeVine extends PipeBlock {
    public static final MapCodec<RhizomeVine> CODEC = simpleCodec(RhizomeVine::new);


    public static final BooleanProperty GRASS = BooleanProperty.create("grass");
    public static final BooleanProperty MUSHROOM = BooleanProperty.create("mushroom");

    @Override
    public MapCodec<RhizomeVine> codec() {
        return CODEC;
    }

    public RhizomeVine(BlockBehaviour.Properties properties) {
        super(8, properties);

        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(NORTH, false)
                        .setValue(EAST, false)
                        .setValue(SOUTH, false)
                        .setValue(WEST, false)
                        .setValue(UP, false)
                        .setValue(DOWN, false)
                        .setValue(GRASS, false)
                        .setValue(MUSHROOM, false)
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return getStateWithConnections(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos(), this.defaultBlockState());
    }

    public static BlockState getStateWithConnections(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        BlockState blockState2 = blockGetter.getBlockState(blockPos.below());
        BlockState blockState3 = blockGetter.getBlockState(blockPos.above());
        BlockState blockState4 = blockGetter.getBlockState(blockPos.north());
        BlockState blockState5 = blockGetter.getBlockState(blockPos.east());
        BlockState blockState6 = blockGetter.getBlockState(blockPos.south());
        BlockState blockState7 = blockGetter.getBlockState(blockPos.west());

        int offset = blockPos.hashCode() +
                blockState2.hashCode() +
                blockState3.hashCode() +
                blockState4.hashCode() +
                blockState5.hashCode() +
                blockState6.hashCode() +
                blockState7.hashCode();
        offset /= 14;
        offset  = offset % 10;
        if (offset < 0) {
            offset = -offset;
        }

        boolean mushroom = offset < 2;
        boolean grass = offset > 6;

        return blockState.trySetValue(DOWN, blockState2.isSolid() || blockState2.is(BlockInit.RhizomeBlock_))
                .trySetValue(UP, blockState3.isSolid() || blockState3.is(BlockInit.RhizomeBlock_) )
                .trySetValue(NORTH, blockState4.isSolid() || blockState4.is(BlockInit.RhizomeBlock_) )
                .trySetValue(EAST, blockState5.isSolid() || blockState5.is(BlockInit.RhizomeBlock_) )
                .trySetValue(SOUTH, blockState6.isSolid() || blockState6.is(BlockInit.RhizomeBlock_) )
                .trySetValue(WEST, blockState7.isSolid() || blockState7.is(BlockInit.RhizomeBlock_) )
                .trySetValue(MUSHROOM, mushroom)
                .trySetValue(GRASS, grass)

                ;
    }
    @Override
    protected List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(ItemInit.RichInFlourRhizomes_,1));
    }

    @Override
    protected BlockState updateShape(
            BlockState blockState,
            LevelReader levelReader,
            ScheduledTickAccess scheduledTickAccess,
            BlockPos blockPos,
            Direction direction,
            BlockPos blockPos2,
            BlockState blockState2,
            RandomSource randomSource
    ) {
        if (!blockState.canSurvive(levelReader, blockPos)) {
            scheduledTickAccess.scheduleTick(blockPos, this, 1);
            return super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
        } else {
            boolean bl = blockState2.is(this)  || direction == Direction.DOWN && blockState2.isSolid();
            return blockState.setValue(PROPERTY_BY_DIRECTION.get(direction), bl);
        }
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN,GRASS,MUSHROOM);
    }
}


