package com.ytgld.the_wax.block;

import com.ytgld.the_wax.block.init.BlockInit;
import com.ytgld.the_wax.feature.gen.TheConfiguredFeatures;
import com.ytgld.the_wax.items.init.ItemInit;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class WaxBase extends MushroomBlock implements BonemealableBlock {
    public WaxBase(Properties properties) {
        super(TheConfiguredFeatures.BIG_WAX, properties);
        BlockRenderLayerMap.putBlock(this, ChunkSectionLayer.TRANSLUCENT);
    }

    @Override
    protected List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        return List.of(new ItemStack(BlockInit.WAX_BASE));
    }
    @Override
    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return levelReader.getBlockState(blockPos.above()).is(BlockTags.BASE_STONE_OVERWORLD)||levelReader.getBlockState(blockPos.above()).is(BlockTags.DIRT);
    }
    @Override
    protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {

    }
}
