package com.ytgld.the_wax.block.earth;

import com.mojang.serialization.MapCodec;
import com.ytgld.the_wax.block.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GiantRhizome extends BaseEntityBlock {
    public static final EnumProperty<GiantRhizomePart> PART = EnumProperty.create("part", GiantRhizomePart.class);
    public static final BooleanProperty IS_ROOT = BooleanProperty.create("is_root");
    public GiantRhizome(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(IS_ROOT, false)
        );
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        return List.of(new ItemStack(BlockInit.MelonRoot_));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PART,IS_ROOT);
    }
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(GiantRhizome::new);
    }

    protected InteractionResult useWithoutItem(final BlockState state, final Level level, final BlockPos pos, final Player player, final BlockHitResult hitResult) {
        BlockEntity var8 = level.getBlockEntity(pos);
        if (var8 instanceof GiantRhizomeBlockEntity melonRootBlockEntity) {
            player.openMenu(melonRootBlockEntity);
        }
        return InteractionResult.SUCCESS;
    }

    protected void affectNeighborsAfterRemoval(final BlockState state, final ServerLevel level, final BlockPos pos, final boolean movedByPiston) {
        Containers.updateNeighboursAfterDestroy(state, level, pos);
    }

    protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof GiantRhizomeBlockEntity) {
            ((GiantRhizomeBlockEntity)blockEntity).recheckOpen();
        }

    }

    public @Nullable BlockEntity newBlockEntity(final BlockPos worldPosition, final BlockState blockState) {
        return new GiantRhizomeBlockEntity(worldPosition, blockState);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        BlockPos rootPos = state.getValue(PART) == GiantRhizomePart.ROOT ? pos : findRoot(pos, level);
        BlockEntity blockEntity = level.getBlockEntity(rootPos);
        if (blockEntity instanceof GiantRhizomeBlockEntity entity) {
            player.openMenu(entity);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        BlockPos rootPos = state.getValue(PART) == GiantRhizomePart.ROOT ? pos : findRoot(pos, level);
        for (int dx = 0; dx <= 1; dx++) {
            for (int dy = 0; dy <= 1; dy++) {
                for (int dz = 0; dz <= 1; dz++) {
                    BlockPos offset = rootPos.offset(dx, dy, dz);
                    if (level.getBlockState(offset).getBlock() instanceof GiantRhizome) {
                        level.destroyBlock(offset,true,player);
                    }
                }
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }
    private BlockPos findRoot(BlockPos pos, Level level) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    BlockPos checkPos = pos.offset(dx, dy, dz);
                    BlockState checkState = level.getBlockState(checkPos);
                    if (checkState.getBlock() instanceof GiantRhizome &&
                            checkState.getValue(PART) == GiantRhizomePart.ROOT) {
                        return checkPos;
                    }
                }
            }
        }
        return pos;
    }
    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (!canPlaceCube(level, pos)) {
            level.removeBlock(pos, false);
            return;
        }
        state.setValue(IS_ROOT,Boolean.TRUE);

        for (int dx = 0; dx <= 1; dx++) {
            for (int dy = 0; dy <= 1; dy++) {
                for (int dz = 0; dz <= 1; dz++) {
                    BlockPos offset = pos.offset(dx, dy, dz);
                    if (offset.equals(pos)) continue; // 根块已放置
                    level.setBlock(offset, this.defaultBlockState().setValue(PART, GiantRhizomePart.EXTENSION), 3);
                }
            }
        }
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();

        if (!canPlaceCube(level, pos)) {
            return null; // null 表示不能放置，客户端也不会消耗方块
        }

        return this.defaultBlockState();
    }
    private boolean canPlaceCube(Level level, BlockPos root) {
        List<Integer> integers = new ArrayList<>();
        for (int dx = 0; dx <= 1; dx++) {
            for (int dy = 0; dy <= 1; dy++) {
                for (int dz = 0; dz <= 1; dz++) {
                    BlockPos pos = root.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(pos);
                    if (state.isAir()) {
                        integers.add(1);
                    }
                }
            }
            if (integers.size() >= 7) {
                return true;
            }
        }

        return false;
    }
    protected boolean hasAnalogOutputSignal(final BlockState state) {
        return true;
    }

    protected int getAnalogOutputSignal(final BlockState state, final Level level, final BlockPos pos, final Direction direction) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }
}
