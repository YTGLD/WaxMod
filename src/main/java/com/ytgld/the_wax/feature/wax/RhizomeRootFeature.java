package com.ytgld.the_wax.feature.wax;


import com.mojang.serialization.Codec;
import com.ytgld.the_wax.block.BlockInit;
import com.ytgld.the_wax.block.wax.RhizomeVine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
//
//public class RhizomeRootFeature extends Feature<RhizomeRootFeatureConfig> {
//
//    public RhizomeRootFeature(Codec<RhizomeRootFeatureConfig> codec) {
//        super(codec);
//    }
//
//    @Override
//    public boolean place(FeaturePlaceContext<RhizomeRootFeatureConfig> context) {
//        if (context.random().nextInt(100) < 10) {
//            addRoots(context);
//            return true;
//        }
//        return false;
//    }
//    private List<BlockPos> findCeiling(WorldGenLevel world, BlockPos origin){
//
//        List<BlockPos> result = new ArrayList<>();
//
//        for(int x = 0; x < 16; x++){
//            for(int z = 0; z < 16; z++){
//
//                for(int y = 50; y > -30; y--){
//
//                    BlockPos pos = origin.offset(x,y,z);
//
//
//                    // 固体 + 下方空气
//                    if(!world.isEmptyBlock(pos)
//                            && world.isEmptyBlock(pos.below())){
//
//
//                        result.add(pos.below());
//
//                        break;
//                    }
//                }
//            }
//        }
//
//        return result;
//    }
//    private void addRoots(FeaturePlaceContext<RhizomeRootFeatureConfig> context){
//
//        WorldGenLevel world=context.level();
//        RandomSource random=context.random();
//
//
//        List<BlockPos> surfaces =
//                findCeiling(world,context.origin());
//
//
//        int count =
//                Math.max(1,
//                        surfaces.size()/10);
//
//
//        Collections.shuffle(surfaces,
//                new Random(random.nextLong()));
//
//
//        for(int i=0;i<count;i++){
//
//            growMainRoot(
//                    world,
//                    surfaces.get(i),
//                    random
//            );
//        }
//    }
//    private void growMainRoot(
//            WorldGenLevel world,
//            BlockPos start,
//            RandomSource random
//    ){
//
//        int length =
//                8 + random.nextInt(12);
//
//
//        BlockPos.MutableBlockPos pos =
//                start.mutable();
//
//
//        for(int i=0;i<length;i++){
//
//
//            if(!world.isEmptyBlock(pos))
//                break;
//
//
//            placeVine(world,pos);
//
//
//            // 随机生成分叉
//            if(i > 3 &&
//                    random.nextInt(100)<25){
//
//                createBranch(
//                        world,
//                        pos,
//                        random
//                );
//            }
//
//
//            pos.move(Direction.DOWN);
//        }
//    }
//    private void createBranch(
//            WorldGenLevel world,
//            BlockPos center,
//            RandomSource random
//    ){
//
//        Direction dir =
//                random.nextBoolean()
//                        ? Direction.EAST
//                        : Direction.WEST;
//
//
//        BlockPos branch =
//                center.relative(dir);
//
//
//        if(world.isEmptyBlock(branch)){
//
//
//            placeVine(
//                    world,
//                    branch
//            );
//        }
//    }
//    private void placeVine(
//            WorldGenLevel world,
//            BlockPos pos
//    ){
//
//        BlockState state =
//                RhizomeVine.getStateWithConnections(
//                        world,
//                        pos,
//                        BlockInit.RhizomeVine_.defaultBlockState()
//                );
//
//        world.setBlock(
//                pos,
//                state,
//                3
//        );
//    }
//}

