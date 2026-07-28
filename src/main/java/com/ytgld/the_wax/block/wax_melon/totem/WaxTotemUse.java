package com.ytgld.the_wax.block.wax_melon.totem;

import com.ytgld.the_wax.block.init.BlockBase;
import com.ytgld.the_wax.block.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class WaxTotemUse extends BlockBase {
    public static final BooleanProperty huge =
            BooleanProperty.create("huge");

    public static final BooleanProperty polymer =
            BooleanProperty.create("polymer");

    public WaxTotemUse(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(polymer, false)
                        .setValue(huge,false)
        );
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(huge)) {
            return Shapes.or(
                    Block.column(16, 0, 16)
            );
        }
        return Shapes.or(
                Block.column(12, 0, 16)
        );
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (state.getValue(polymer)) {

            int give = player.getRandom().nextInt(3) + 1;
            if (state.getValue(huge)) {
                give *= 2;
            }
            level.addFreshEntity(new ItemEntity(level,pos.getX(),pos.getY(),pos.getZ(),new ItemStack(Items.SUGAR,
                    give )));
            level.setBlock(pos,state.setValue(polymer,false),3);
            return InteractionResult.PASS;
        }
        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }
    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.getValue(polymer)){
            if (level.getBlockState(pos.below()).is(BlockInit.WaxTotemBase_)) {
                level.setBlock(pos,state.setValue(polymer,true),3);
            }else if (level.getBlockState(pos.below()).hasProperty(polymer) && level.getBlockState(pos.below()).getValue(polymer)) {
                level.setBlock(pos,state.setValue(polymer,true),3);
            }
        }
    }

    @Override
    public void animateTick(final BlockState state, final Level level, final BlockPos pos, final RandomSource random) {
        if (!state.getValue(polymer)) {
            return;
        }
        Direction direction = Direction.getRandom(random);
        if (direction != Direction.UP) {
            BlockPos relativePos = pos.relative(direction);
            BlockState blockState = level.getBlockState(relativePos);
            if (!state.canOcclude() || !blockState.isFaceSturdy(level, relativePos, direction.getOpposite())) {
                double xx = pos.getX();
                double yy = pos.getY();
                double zz = pos.getZ();
                yy += random.nextDouble() * 0.8;
                if (direction.getAxis() == Direction.Axis.X) {
                    zz += random.nextDouble();
                    if (direction == Direction.EAST) {
                        ++xx;
                    } else {
                        xx += 0.05;
                    }
                } else {
                    xx += random.nextDouble();
                    if (direction == Direction.SOUTH) {
                        ++zz;
                    } else {
                        zz += 0.05;
                    }
                }

                level.addParticle(ParticleTypes.FALLING_HONEY, xx, yy, zz, (double)0.0F, (double)0.0F, (double)0.0F);
            }
        }
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(
                polymer,
                huge
        );
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        List<ItemStack> list = new ArrayList<>();
        list.add(this.asItem().getDefaultInstance());
        if (state.getValue(polymer)) {
            list.add(Items.SUGAR.getDefaultInstance());
            if (state.getValue(huge)) {
                list.add(Items.SUGAR.getDefaultInstance());
            }
        }
        return list;
    }
}
