package com.ytgld.the_wax;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class WaxTagGenerator extends FabricTagProvider.ItemTagProvider{
    public WaxTagGenerator(FabricDataOutput output,
                           CompletableFuture<HolderLookup.Provider>
                                   registriesFuture) {
        super(output, registriesFuture);

    }
    public static final TagKey<Item> WAX = bind("wax");
    public static final TagKey<Item> PIPE = bind("pipe");
    public static final TagKey<Item> NETHER_PIPE = bind("nether_pipe");
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateRawBuilder(ItemTags.PLANKS)
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"nether_pipe_planks"))

        ;
        getOrCreateRawBuilder(NETHER_PIPE)
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"nether_pipe"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"nether_pipe_other"))

        ;
        getOrCreateRawBuilder(PIPE)
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax_vine_pipe"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"huge_pipe"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"pipe_wall"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"nether_pipe"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"nether_pipe_other"))

        ;
        getOrCreateRawBuilder(WAX)
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"water_wax"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"nether_wax"))

        ;
    }
    private static TagKey<Item> bind(String string) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,string));
    }
}