package com.ytgld.the_wax.block.earth;

import com.ytgld.the_wax.block.init.BlockBase;
import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class MelonRootFlower extends BlockBase {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.column(4, 0, 4),
            Block.column(2, 0, 21)
    );
    public MelonRootFlower(Properties properties) {
        super(properties);
    }
    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }
    @Override
    public boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
        return !level.isEmptyBlock(pos.below());
    }

}
