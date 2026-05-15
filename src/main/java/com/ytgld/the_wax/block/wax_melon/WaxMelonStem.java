package com.ytgld.the_wax.block.wax_melon;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jspecify.annotations.NonNull;

public class WaxMelonStem extends RotatedPillarBlock {

    public static final BooleanProperty A = BooleanProperty.create("a");
    public static final BooleanProperty B = BooleanProperty.create("b");
    public static final BooleanProperty C = BooleanProperty.create("c");
    public static final BooleanProperty D = BooleanProperty.create("d");



    public WaxMelonStem(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(A, false)
                        .setValue(B, false)
                        .setValue(C, false)
                        .setValue(D, false)
        );
    }

    @Override
    public @NonNull BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter blockView = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState stateDown = blockView.getBlockState(blockPos.below());
        int offset = (blockPos.getX() + blockPos.getY() + blockPos.getZ()) / 3 + blockPos.getX() + blockPos.getY() + blockPos.getZ() ;
        if (!stateDown.is(this) && !stateDown.is(Blocks.AIR)) {
            return super.getStateForPlacement(context)
                    .trySetValue(A,offset * blockPos.hashCode() %5==0)
                    .trySetValue(B,offset * blockPos.hashCode() %6==0)
                    .trySetValue(C,offset * blockPos.hashCode() %7==0)
                    .trySetValue(D,offset * blockPos.hashCode() %8==0);
        }
        return super.getStateForPlacement(context);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(
                A,
                B,
                C,
                D

        );
    }
}
