package com.ytgld.the_wax.feature.earth;

import com.mojang.serialization.Codec;
import com.ytgld.the_wax.block.earth.MelonRootBlockEntity;
import com.ytgld.the_wax.block.earth.MelonVinePlant;
import com.ytgld.the_wax.block.BlockInit;
import com.ytgld.the_wax.loot.WaxLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
public class UngroudRootFeature extends Feature<UngroudRootFeatureConfig> {


    public UngroudRootFeature(Codec<UngroudRootFeatureConfig> codec) {
        super(codec);
    }


    @Override
    public boolean place(FeaturePlaceContext<UngroudRootFeatureConfig> context) {

        addVine(context);

        return true;
    }


    private void addVine(
            FeaturePlaceContext<UngroudRootFeatureConfig> context
    ) {

        WorldGenLevel world = context.level();
        RandomSource random = context.random();


        // 10%
        if (random.nextInt(100) >= 10)
            return;


        BlockPos origin = new BlockPos(
                context.origin().getX(),
                Mth.nextInt(random, -30, 20),
                context.origin().getZ()
        );


        // 原8根，降低
        int vineCount =
                1 + random.nextInt(4);


        BlockState plant =
                BlockInit.MelonVinePlant_
                        .defaultBlockState();


        for (int i = 0; i < vineCount; i++) {


            BlockPos.MutableBlockPos roof =
                    origin.offset(
                            random.nextInt(3) - 1,
                            0,
                            random.nextInt(3) - 1
                    ).mutable();


            // 限制寻找顶部
            int searchUp = 80;


            while (
                    world.isEmptyBlock(roof)
                            && searchUp-- > 0
            ) {
                roof.move(Direction.UP);
            }


            if (searchUp <= 0)
                continue;


            BlockPos.MutableBlockPos vine =
                    roof.below().mutable();


            int length =
                    3 + random.nextInt(5);


            for (int j = 0; j < length; j++) {


                if (!world.isEmptyBlock(vine))
                    break;


                BlockState state = plant;


                if (random.nextInt(4) == 0) {
                    state =
                            state.setValue(
                                    MelonVinePlant.BERRIES,
                                    true
                            );
                }


                world.setBlock(
                        vine,
                        state,
                        0
                );


                vine.move(Direction.DOWN);
            }


            // 尾端
            if (world.isEmptyBlock(vine)) {


                BlockState end =
                        BlockInit.MelonVine_
                                .defaultBlockState();


                if (random.nextInt(4) == 0) {
                    end = end.setValue(
                            MelonVinePlant.BERRIES,
                            true
                    );
                }


                world.setBlock(
                        vine,
                        end,
                        0
                );

            }


            vine.move(Direction.DOWN);


            // 根
            if (world.isEmptyBlock(vine)) {


                world.setBlock(
                        vine,
                        BlockInit.MelonRoot_
                                .defaultBlockState(),
                        0
                );


                if (random.nextInt(100) < 50) {

                    generateRootFlower(
                            world,
                            vine,
                            random
                    );

                }

            }
        }
    }
    private void generateRootFlower(
            WorldGenLevel world,
            BlockPos.MutableBlockPos pos,
            RandomSource random
    ){


        BlockPos.MutableBlockPos search =
                pos.immutable().below()
                        .mutable();



        int depth=40;


        while(
                world.isEmptyBlock(search)
                        && depth-- >0
        ){
            search.move(Direction.DOWN);
        }


        if(depth<=0)
            return;



        search.move(Direction.UP);



        if(world.isEmptyBlock(search)){


            world.setBlock(
                    search,
                    BlockInit.MelonRoot_
                            .defaultBlockState(),
                    0
            );



            BlockEntity entity =
                    world.getBlockEntity(search);


            if(entity instanceof MelonRootBlockEntity root){

                root.setLootTable(
                        WaxLootTables.MELON_ROOT,
                        random.nextLong()
                );
            }



            BlockPos flower =
                    search.above();



            if(world.isEmptyBlock(flower)){

                world.setBlock(
                        flower,
                        BlockInit.MelonRootFlower_
                                .defaultBlockState(),
                        0
                );
            }
        }
    }
}