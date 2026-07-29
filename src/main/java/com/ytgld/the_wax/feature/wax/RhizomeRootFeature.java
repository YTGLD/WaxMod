package com.ytgld.the_wax.feature.wax;


import com.mojang.serialization.Codec;
import com.ytgld.the_wax.block.BlockInit;
import com.ytgld.the_wax.block.earth.MelonRootBlockEntity;
import com.ytgld.the_wax.block.earth.MelonVinePlant;
import com.ytgld.the_wax.loot.WaxLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class RhizomeRootFeature extends Feature<RhizomeRootFeatureConfig> {

    public RhizomeRootFeature(Codec<RhizomeRootFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<RhizomeRootFeatureConfig> context) {
        adVine(context);

        return true;
    }

    private void adVine(FeaturePlaceContext<RhizomeRootFeatureConfig> context) {
        WorldGenLevel world = context.level();
        RandomSource random = context.random();
        if (random.nextInt(100) < 33) {

            BlockPos origin = new BlockPos(
                    context.origin().getX(),
                    Mth.nextInt(
                            RandomSource.create(),
                            -50,
                            20
                    ),
                    context.origin().getZ()
            );


            int vineCount = 2 + random.nextInt(2);
            for (int i = 0; i < vineCount; i++) {

                int xOffset = random.nextInt(3) - 1;
                int zOffset = random.nextInt(3) - 1;


                BlockPos roofPos = origin.offset(
                        xOffset,
                        0,
                        zOffset
                );


                while (
                        world.isEmptyBlock(roofPos)
                                && roofPos.getY() < 320
                ) {
                    roofPos = roofPos.above();
                }


                // 顶部连接点
                BlockPos start = roofPos.below();


                generateRhizome(
                        world,
                        start,
                        random,
                        4 + random.nextInt(6),4
                );
            }
        }
    }
    private void generateRhizome(
            LevelAccessor world,
            BlockPos start,
            RandomSource random,
            int length,
            int maxBranch
    ) {
        BlockPos pos = start;

        for (int i = 0; i < length; i++) {

            if (!world.isEmptyBlock(pos)) {
                break;
            }

            world.setBlock(
                    pos,
                    BlockInit.RhizomeVine_
                            .defaultBlockState(),
                    3
            );


            // 分支生成
            if (maxBranch > 0 && i > 3 && random.nextFloat() < 0.25F) {

                Direction dir = Direction.Plane.HORIZONTAL
                        .getRandomDirection(random);

                BlockPos branchStart = pos.relative(dir);

                generateRhizome(
                        world,
                        branchStart,
                        random,
                        3 + random.nextInt(6),
                        maxBranch - 1
                );
            }


            pos = pos.below();
        }
    }
}

