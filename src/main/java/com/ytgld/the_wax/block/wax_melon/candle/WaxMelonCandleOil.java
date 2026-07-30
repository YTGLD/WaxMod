package com.ytgld.the_wax.block.wax_melon.candle;

import com.mojang.serialization.MapCodec;
import com.ytgld.the_wax.block.init.BlockBase;
import com.ytgld.the_wax.items.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class WaxMelonCandleOil extends BlockBase {
    public static final MapCodec<WaxMelonCandleOil> CODEC = simpleCodec(WaxMelonCandleOil::new);
    public static final IntegerProperty LAYERS;
    private static final VoxelShape[] SHAPES;

    public MapCodec<WaxMelonCandleOil> codec() {
        return CODEC;
    }

    public WaxMelonCandleOil(final BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LAYERS, 1));
    }

    protected boolean isPathfindable(final BlockState state, final PathComputationType type) {
        if (type == PathComputationType.LAND) {
            return (Integer)state.getValue(LAYERS) < 5;
        } else {
            return false;
        }
    }

    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPES[(Integer)state.getValue(LAYERS)];
    }

    protected VoxelShape getCollisionShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPES[(Integer)state.getValue(LAYERS) - 1];
    }

    protected VoxelShape getBlockSupportShape(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return SHAPES[(Integer)state.getValue(LAYERS)];
    }

    protected VoxelShape getVisualShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return SHAPES[(Integer)state.getValue(LAYERS)];
    }

    protected boolean useShapeForLightOcclusion(final BlockState state) {
        return true;
    }

    protected float getShadeBrightness(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return (Integer)state.getValue(LAYERS) == 8 ? 0.2F : 1.0F;
    }

    protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
        BlockState belowState = level.getBlockState(pos.below());
        if (belowState.is(BlockTags.CANNOT_SUPPORT_SNOW_LAYER)) {
            return false;
        } else if (belowState.is(BlockTags.SUPPORT_OVERRIDE_SNOW_LAYER)) {
            return true;
        } else {
            return Block.isFaceFull(belowState.getCollisionShape(level, pos.below()), Direction.UP) || belowState.is(this) && (Integer)belowState.getValue(LAYERS) == 8;
        }
    }

    protected BlockState updateShape(final BlockState state, final LevelReader level, final ScheduledTickAccess ticks, final BlockPos pos, final Direction directionToNeighbour, final BlockPos neighbourPos, final BlockState neighbourState, final RandomSource random) {
        return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }
    protected boolean canBeReplaced(final BlockState state, final BlockPlaceContext context) {
        int layers = (Integer)state.getValue(LAYERS);
        if (context.getItemInHand().is(this.asItem()) && layers < 8) {
            if (context.replacingClickedOnBlock()) {
                return context.getClickedFace() == Direction.UP;
            } else {
                return true;
            }
        } else {
            return layers == 1;
        }
    }

    public @Nullable BlockState getStateForPlacement(final BlockPlaceContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        if (state.is(this)) {
            int layers = (Integer)state.getValue(LAYERS);
            return (BlockState)state.setValue(LAYERS, Math.min(8, layers + 1));
        } else {
            return super.getStateForPlacement(context);
        }
    }

    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{LAYERS});
    }

    static {
        LAYERS = BlockStateProperties.LAYERS;
        SHAPES = Block.boxes(8, (height) -> Block.column((double)16.0F, (double)0.0F, (double)(height * 2)));
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        return List.of(new ItemStack(ItemInit.WaxMelonWax_,state.getValue(LAYERS)));
    }
}
