package com.ytgld.the_wax.feature.wax_melon;

import com.mojang.serialization.Codec;
import com.ytgld.the_wax.block.BlockInit;
import com.ytgld.the_wax.block.wax_melon.WaxMelonStem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class WaxMelonFeature extends Feature<WaxMelonFeatureConfig> {
    public WaxMelonFeature(Codec<WaxMelonFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<WaxMelonFeatureConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        BlockPos testPos = new BlockPos(origin);

        int waterHeight = 0;
        for (int i = 0; i < 5+ Mth.nextInt(RandomSource.create(),1,5) && world.getBlockState(testPos.above()).is(Blocks.AIR); i++) {
            waterHeight = i;
        }
        BlockState state = BlockInit.WaxMelonStem_.defaultBlockState();
        for (int i = 0; i < waterHeight; i++) {
            BlockPos nowPos = testPos.above(i);
            if (i == 0) {
                BlockState stateRoot = BlockInit.WaxMelonStem_.defaultBlockState();
                int offset = (testPos.getX() + testPos.getY() + testPos.getZ()) / 3 + testPos.getX() + testPos.getY() + testPos.getZ() ;
                stateRoot = stateRoot.trySetValue(WaxMelonStem.A,offset * testPos.hashCode() %5==0)
                        .trySetValue(WaxMelonStem.B,offset * testPos.hashCode() %6==0)
                        .trySetValue(WaxMelonStem.C,offset * testPos.hashCode() %7==0)
                        .trySetValue(WaxMelonStem.D,offset * testPos.hashCode() %8==0)
                        .setValue(WaxMelonStem.AXIS, Direction.Axis.Y);

                world.setBlock(nowPos.east(), stateRoot,3);
                world.setBlock(nowPos.north(),stateRoot,3);
                world.setBlock(nowPos.west(), stateRoot,3);
                world.setBlock(nowPos.south(),stateRoot,3);
                world.setBlock(nowPos,stateRoot,3);
            }else if (i == waterHeight - 1) {
                addStem(nowPos, world);
            }else {
                state = state.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);
                world.setBlock(nowPos, state, 3);
            }
        }


        return true;
    }
    public void addAir(BlockPos testPos,WorldGenLevel world){
        world.setBlock(testPos.offset(0,0,0), BlockInit.BeeswaxMelon_.defaultBlockState(), 3);
        world.setBlock(testPos.offset(0,1,0), BlockInit.MelonVine_.defaultBlockState(), 3);
    }
    public void addStem(BlockPos testPos, WorldGenLevel world) {
        addBlock(testPos,world, Direction.Axis.Y);
        int xz = 2;
        int y = 5;
        for (int i = 0; i < xz; i++) {
            addBlock(testPos.offset(0,0,i),world, Direction.Axis.Z);
        }
        for (int i = 0; i < y; i++) {
            addBlock(testPos.offset(0,i,xz),world, Direction.Axis.Y);
        }
        for (int i = 0; i < xz; i++) {
            addBlock(testPos.offset(0,y-1,i),world, Direction.Axis.Z);
        }
        addAir(testPos.offset(0,2,0), world);
    }
    public void addBlock(BlockPos testPos, WorldGenLevel world, Direction.Axis axis){
        BlockState state = BlockInit.WaxMelonStem_.defaultBlockState();
        state = state.setValue(WaxMelonStem.AXIS, axis);
        if (world.getBlockState(testPos).is(Blocks.AIR)) {
            world.setBlock(testPos,state , 3);
        }
    }
}
