package com.ytgld.the_wax;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraft.tags.BlockTags;
import java.util.concurrent.CompletableFuture;

public class WaxTagGeneratorBlock extends BlockTagsProvider {
    public WaxTagGeneratorBlock(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture, ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, "the_wax", existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.CANDLES)
                .add(BuiltInRegistries.BLOCK.get(new ResourceLocation(WaxMod.MOD_ID,"wax_pipe_candle")))
                .add(BuiltInRegistries.BLOCK.get(new ResourceLocation(WaxMod.MOD_ID,"pipe_honey_candle")))

        ;
        tag(BlockTags.WALLS)
                .add(BuiltInRegistries.BLOCK.get(new ResourceLocation(WaxMod.MOD_ID,"pipe_wall")))


        ;
    }
}