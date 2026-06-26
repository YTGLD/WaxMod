package com.ytgld.the_wax.block.earth;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class MelonRoot extends BaseEntityBlock {
    private static final VoxelShape SHAPE = Shapes.or(
            Block.column(16, 0, 16),
            Block.column(8, 0, 19)
    );
    public static final EnumProperty<Direction> FACING;
    public static final BooleanProperty OPEN;
    public MelonRoot(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(MelonRoot::new);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    protected InteractionResult useWithoutItem(final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult) {
        BlockEntity var8 = level.getBlockEntity(pos);
        if (var8 instanceof MelonRootBlockEntity melonRootBlockEntity) {
            player.openMenu(melonRootBlockEntity);
        }
        return InteractionResult.SUCCESS;
    }

    protected void affectNeighborsAfterRemoval(final BlockState state, final ServerLevel level, final BlockPos pos, final boolean movedByPiston) {
        Containers.updateNeighboursAfterDestroy(state, level, pos);
    }

    protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof MelonRootBlockEntity) {
            ((MelonRootBlockEntity)blockEntity).recheckOpen();
        }

    }

    public @Nullable BlockEntity newBlockEntity(final BlockPos worldPosition, final BlockState blockState) {
        return new MelonRootBlockEntity(worldPosition, blockState);
    }

    protected boolean hasAnalogOutputSignal(final BlockState state) {
        return true;
    }

    protected int getAnalogOutputSignal(final BlockState state, final Level level, final BlockPos pos, final Direction direction) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, OPEN});
    }

    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
    }

    static {
        FACING = BlockStateProperties.FACING;
        OPEN = BlockStateProperties.OPEN;
    }
}
