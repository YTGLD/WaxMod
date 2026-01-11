package com.ytgld.the_wax.block;

import com.mojang.serialization.MapCodec;
import com.ytgld.the_wax.block.init.BlockInit;
import com.ytgld.the_wax.items.init.ItemInit;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class SmallWax extends Block {
    public static final BooleanProperty IN_AIR = BooleanProperty.create("in_air");
    public static final BooleanProperty IS_GOLDEN = BooleanProperty.create("is_golden");
    public static final MapCodec<SmallWax> CODEC = simpleCodec(SmallWax::new);
    private static final VoxelShape SHAPE = Shapes.or(
            Block.column(8, 8, 0.0, 8),
            Block.column(6, 6, 0.0, 10)
    );
    public SmallWax(Properties properties) {
        super(properties);
        BlockRenderLayerMap.putBlock(this, ChunkSectionLayer.TRANSLUCENT);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(IN_AIR, false)
                        .setValue(IS_GOLDEN, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(
                IN_AIR,
                IS_GOLDEN
        );
    }
    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockGetter blockView = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        BlockState stateUp = blockView.getBlockState(blockPos.above());
        boolean air = !stateUp.is(this)
                && !stateUp.is(Blocks.AIR)
                && blockView.getBlockState(blockPos.below()).isAir();
        return this.defaultBlockState()
                .setValue(IN_AIR, air);
    }
    @Override
    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        if (levelReader.getBlockState(blockPos.above()).is(BlockInit.WAX_PINE)){
            return true;
        }
        Direction direction = getConnectedDirection(blockState).getOpposite();
        return Block.canSupportCenter(levelReader, blockPos.relative(direction), direction.getOpposite());
    }

    protected static Direction getConnectedDirection(BlockState blockState) {
        return blockState.getValue(IN_AIR) ? Direction.DOWN : Direction.UP;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean bl) {
        super.neighborChanged(state, level, pos, block, orientation, bl);
        if (!level.getBlockState(pos.below()).isSolid()) {
            if (!level.getBlockState(pos.above()).isSolid()) {
                level.destroyBlock(pos, true);
            }
        } else {
            level.setBlock(pos, state.setValue(IN_AIR, false), 3);
        }
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }
    @Override
    protected List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        if (blockState.getValue(IS_GOLDEN)){
            return List.of(new ItemStack(ItemInit.GOLDEN_WAX));
        }
        return List.of(new ItemStack(ItemInit.ITEM_WAX));
    }
}
