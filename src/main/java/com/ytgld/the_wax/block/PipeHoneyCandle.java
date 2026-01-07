package com.ytgld.the_wax.block;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.ToIntFunction;

public class PipeHoneyCandle extends CandleBlock {
    public static final ToIntFunction<BlockState> LIGHT_EMISSION = blockState -> {
        if (blockState.getValue(LIT)) {
            if (blockState.getValue(CANDLES) == 1) {
                return 4;
            } else if (blockState.getValue(CANDLES) == 2) {
                return 8;
            } else if (blockState.getValue(CANDLES) == 3) {
                return 12;
            } else if (blockState.getValue(CANDLES) == 4) {
                return 15;
            }
        }
        return 0;

    };
    private static final Int2ObjectMap<List<Vec3>> maPart = Util.make(
            new Int2ObjectOpenHashMap<>(4),
            int2ObjectOpenHashMap -> {
                float f = 0.0625f;
                int2ObjectOpenHashMap.put(1, List.of(new Vec3(8 ,7, 8).scale(f)));
                int2ObjectOpenHashMap.put(2, List.of(new Vec3(8 ,9, 8).scale(f)));
                int2ObjectOpenHashMap.put(3, List.of(new Vec3(8 ,10 , 8).scale(f)));
                int2ObjectOpenHashMap.put(4, List.of(new Vec3(8 ,14, 8).scale(f)));

            }
    );
    public PipeHoneyCandle(Properties properties) {
        super(properties);
        BlockRenderLayerMap.putBlock(this, ChunkSectionLayer.TRANSLUCENT);
    }
    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }


    @Override
    protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(LIT)) {
            Vec3 playerPos = blockPos.getCenter();
            int range = 4 + blockState.getValue(CANDLES) * 2;
            int lvl = 0;
            if (blockState.getValue(CANDLES) == 4) {
                lvl=1;
            }  List<Player> list = serverLevel.getEntitiesOfClass(Player.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
            for (Player player : list) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600 * blockState.getValue(CANDLES), lvl, true, true));
                player.addEffect(new MobEffectInstance(MobEffects.LUCK, 600 * blockState.getValue(CANDLES), lvl, true, true));
            }
        }
    }

    @Override
    protected @NotNull Iterable<Vec3> getParticleOffsets(BlockState blockState) {
        return maPart.get(blockState.getValue(CANDLES).intValue());
    }

    @Override
    protected List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(this,blockState.getValue(CANDLES)));
    }

    @Override
    protected VoxelShape getShape(BlockState blockState,
                                  BlockGetter blockGetter,
                                  BlockPos blockPos,
                                  CollisionContext collisionContext) {
        if (blockState.getValue(CANDLES)==1){
            return  Block.column(2, 2, 0.0, 4);
        } else if (blockState.getValue(CANDLES) == 2) {
            return  Block.column(4, 4, 0.0, 6);
        } else if (blockState.getValue(CANDLES) == 3) {
            return  Block.column(6, 6, 0.0, 8);
        } else if (blockState.getValue(CANDLES) == 4) {
            return  Block.column(8, 8, 0.0, 12);
        }
        return Block.column(2, 2, 0.0, 4);
    }
}

