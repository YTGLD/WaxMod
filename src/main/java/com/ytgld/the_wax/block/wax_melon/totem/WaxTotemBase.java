package com.ytgld.the_wax.block.wax_melon.totem;

import com.ytgld.the_wax.block.init.BlockBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WaxTotemBase extends BlockBase {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.column(12, 0, 16),
            Block.column(14, 0, 6),
            Block.column(16, 0, 2)
    );
    public WaxTotemBase(Properties properties) {
        super(properties);
    }
    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }
}
