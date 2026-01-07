package com.ytgld.the_wax;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class WaxTagGeneratorBlock extends FabricTagProvider.BlockTagProvider{
    public WaxTagGeneratorBlock(FabricDataOutput output,
                                CompletableFuture<HolderLookup.Provider>
                                   registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateRawBuilder(BlockTags.CANDLES)
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax_pipe_candle"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"pipe_honey_candle"))


        ;
    }

}