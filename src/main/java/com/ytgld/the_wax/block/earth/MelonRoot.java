package com.ytgld.the_wax.block.earth;

import com.ytgld.the_wax.block.init.BlockBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class MelonRoot extends BlockBase {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.column(16, 0, 16),
            Block.column(8, 0, 19)
    );
    public MelonRoot(Properties properties) {
        super(properties);
    }

    @Override
    protected List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(this));
    }
    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

}
