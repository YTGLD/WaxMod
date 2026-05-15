package com.ytgld.the_wax.feature.earth;

import com.mojang.serialization.Codec;
import com.ytgld.the_wax.block.earth.MelonRootBlockEntity;
import com.ytgld.the_wax.block.earth.MelonVinePlant;
import com.ytgld.the_wax.block.init.BlockInit;
import com.ytgld.the_wax.loot.WaxLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

public class UngroudRootFeature extends Feature<UngroudRootFeatureConfig> {

    public UngroudRootFeature(Codec<UngroudRootFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<UngroudRootFeatureConfig> context) {
        adVine(context);
        return true;
    }
    private void adVine(FeaturePlaceContext<UngroudRootFeatureConfig> context){
        WorldGenLevel world = context.level();
        RandomSource random = context.random();
        if (random.nextInt(100) < 33) {
            BlockPos origin =new BlockPos(context.origin().getX(),Mth.nextInt(RandomSource.create(),-30,20),context.origin().getZ());
            int vineCount = 1 + random.nextInt(8);
            for (int i = 0; i < vineCount; i++) {
                int xOffset = random.nextInt(3) - 1; // -1 ~ 1
                int zOffset = random.nextInt(3) - 1; // -1 ~ 1

                BlockPos roofPos = origin.offset(xOffset, 0, zOffset);
                while (world.isEmptyBlock(roofPos) && roofPos.getY() < 320) {
                    roofPos = roofPos.above();
                }

                BlockPos vinePos = roofPos.below();
                int vineLength = 3 + random.nextInt(5);
                for (int j = 0; j < vineLength; j++) {
                    if (world.isEmptyBlock(vinePos)) {
                        BlockState state = BlockInit.MelonVinePlant_.defaultBlockState();
                        if (Mth.nextInt(random, 1, 4) <= 1) {
                            state = state.setValue(MelonVinePlant.BERRIES,true);
                        }
                        world.setBlock(vinePos, state, 2);


                        vinePos = vinePos.below();
                    } else {
                        break;
                    }
                }

                if (world.isEmptyBlock(vinePos)) {
                    BlockState state = BlockInit.MelonVine_.defaultBlockState();
                    if (Mth.nextInt(random, 1, 4) <= 1) {
                        state = state.setValue(MelonVinePlant.BERRIES,true);
                    }
                    world.setBlock(vinePos, state, 2);
                }
                BlockPos pos = vinePos.below();
                if (world.isEmptyBlock(pos)) {
                    world.setBlock(pos, BlockInit.MelonRoot_.defaultBlockState(), 2);
                    if (random.nextInt(100) < 50) {
                        BlockPos stonePos = pos.below();
                        while (world.isEmptyBlock(stonePos) && stonePos.getY() > -60) {
                            stonePos = stonePos.below();
                        }
                        stonePos = stonePos.above();

                        if (world.isEmptyBlock(stonePos)) {
                            world.setBlock(stonePos, BlockInit.MelonRoot_.defaultBlockState(), 2);

                            BlockEntity tileEntity = world.getBlockEntity(stonePos);
                            if (tileEntity instanceof MelonRootBlockEntity melonTile) {
                                melonTile.setLootTable(WaxLootTables.MELON_ROOT, random.nextLong());
                            }
                        }

                        BlockPos sandPos = stonePos.above();
                        if (world.isEmptyBlock(sandPos)) {

                            world.setBlock(sandPos, BlockInit.MelonRootFlower_.defaultBlockState(), 2);
                        }
                    }
                }
            }
        }
    }
}

