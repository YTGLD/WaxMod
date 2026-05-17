package com.ytgld.the_wax.block.wax_melon.candle;

import com.ytgld.the_wax.block.init.BlockBase;
import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.function.ToIntFunction;

public class WaxMelonCandle extends BlockBase {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final BooleanProperty isLONG = BooleanProperty.create("is_long");
    public static final BooleanProperty oil = BooleanProperty.create("oil");

    public static final ToIntFunction<BlockState> LIGHT_EMISSION = blockState -> {
        if (blockState.getValue(LIT)) {
            return 15;
        }
        return 0;
    };

    @Override
    protected @NonNull InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockState(pos.above()).is(Blocks.AIR)) {
            if (itemStack.is(Items.FLINT_AND_STEEL)) {
                setLit(state, level, pos, true);
                return InteractionResult.SUCCESS;
            }else if (itemStack.isEmpty()){
                setLit(state, level, pos, false);
                return InteractionResult.SUCCESS;
            }
        }
        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        var c =super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
        if (level.getBlockState(pos.above()).is(Blocks.AIR)) {
            if (level.getBlockState(pos).getValue(LIT)) {
                return c.setValue(oil,true);
            }
        }else {
            return c.setValue(oil,false);
        }
        return c;
    }

    public void setLit(BlockState state, Level level, BlockPos pos, boolean lit){
        level.setBlock(pos, state.setValue(LIT, lit), 11);
        while (level.getBlockState(pos.below()).is(this)) {
            pos = pos.below();
            if (level.getBlockState(pos).getValue(isLONG)){
                level.setBlock(pos, state.setValue(LIT, lit), 11);
            }else {
                level.setBlock(pos, state.setValue(LIT, lit).setValue(isLONG,false), 11);
            }
        }
    }
    public void animateTick(final BlockState state, final Level level, final BlockPos pos, final RandomSource random) {
        float chance = random.nextFloat();
        if (!state.getValue(LIT)) {
            return;
        }
        if (level.getBlockState(pos.above()).is(Blocks.AIR)) {
            return;
        }
        if (chance < 0.3F) {
            level.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5f, pos.getY() + 1.5f, pos.getZ() + 0.5f, 0.0F, 0.0F, 0.0F);
        }

        level.addParticle(ParticleTypes.SMALL_FLAME, pos.getX() + 0.5f, pos.getY() + 1.5f, pos.getZ() + 0.5f, 0.0F, 0.0F, 0.0F);
    }

    public WaxMelonCandle(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(isLONG, false)
                        .setValue(oil, false)
                        .setValue(LIT, false)
        );
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter blockView = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState stateDown = blockView.getBlockState(blockPos.below());
        if (stateDown.is(this)) {
            return super.getStateForPlacement(context).setValue(isLONG,true);
        }
        return super.getStateForPlacement(context);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(
                isLONG,LIT,oil
        );
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }
    @Override
    protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(LIT)&&blockState.getValue(oil)) {
            Vec3 playerPos = blockPos.getCenter();
            int range = 16;
            List<Player> list = serverLevel.getEntitiesOfClass(Player.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
            for (Player player : list) {
                player.getFoodData().eat(1,0.5f);
            }
            while (serverLevel.getBlockState(blockPos.below()).is(this)){
                blockPos = blockPos.below();
            }

            BlockPos pos = blockPos.below().offset(Mth.nextInt(randomSource,-1,1),0,Mth.nextInt(randomSource,-1,1));
            BlockPos blockPoss = blockPos.offset(Mth.nextInt(randomSource,-1,1),0,Mth.nextInt(randomSource,-1,1));
            if (serverLevel.getBlockState(pos).isSolidRender() && !serverLevel.getBlockState(blockPoss).is(this)) {
                if (serverLevel.getBlockState(blockPoss).is(BlockInit.WaxMelonCandleOil_)) {
                    if (serverLevel.getBlockState(blockPoss).getValue(WaxMelonCandleOil.LAYERS) < 8) {
                        serverLevel.setBlock(blockPoss,
                                serverLevel.getBlockState(blockPoss).setValue(WaxMelonCandleOil.LAYERS,
                                        serverLevel.getBlockState(blockPoss).getValue(WaxMelonCandleOil.LAYERS) + 1), 3);
                    }
                }else {
                    serverLevel.setBlock(blockPoss,BlockInit.WaxMelonCandleOil_.defaultBlockState(),3);
                }
            }
        }
    }

    @Override
    protected VoxelShape getShape(BlockState blockState,
                                  BlockGetter blockGetter,
                                  BlockPos blockPos,
                                  CollisionContext collisionContext) {
        return  Block.column(16, 16, 0.0, 16);
    }

    @Override
    protected List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(this));
    }

}
