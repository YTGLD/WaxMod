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

        getOrCreateRawBuilder(BlockTags.MINEABLE_WITH_AXE)
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"pipe_wall"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax_vine_pipe"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"water_wax"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"huge_pipe"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax_vine_pipe"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"huge_pipe"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax_jump_slime"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"small_wax"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"nether_wax"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"nether_pipe"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"nether_pipe_other"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"nether_pipe_planks"))
        ;
        getOrCreateRawBuilder(BlockTags.MINEABLE_WITH_HOE)
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax_pipe_candle"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"pipe_honey_candle"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax_sponge"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wet_wax_sponge"))

        ;
        getOrCreateRawBuilder(BlockTags.CANDLES)
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax_pipe_candle"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"pipe_honey_candle"))

        ;
        getOrCreateRawBuilder(BlockTags.WALLS)
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"pipe_wall"))
        ;
    }

}