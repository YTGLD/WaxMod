package com.ytgld.the_wax;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class WaxTagGenerator extends FabricTagsProvider.ItemTagsProvider{

    public static final TagKey<Item> WAX = bind("wax");
    public static final TagKey<Item> PIPE = bind("pipe");
    public static final TagKey<Item> NETHER_PIPE = bind("nether_pipe");
    public static final TagKey<Item> WaxMelonStemTag = bind("wax_melon_stem");

    public WaxTagGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture, null);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateRawBuilder(ItemTags.PLANKS)
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"nether_pipe_planks"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"melon_root"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax_melon_stem_plack"))

        ;
        getOrCreateRawBuilder(ConventionalItemTags.WHEAT_CROPS)
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"richin_flour_rhizomes"))
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
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"melon_root"))

        ;
        getOrCreateRawBuilder(WAX)
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"water_wax"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"nether_wax"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax_melon_wax"))

        ;
        getOrCreateRawBuilder(WaxMelonStemTag)
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax_melon_stem"))
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"stripped_wax_melon_stem"))

        ;
    }
    private static TagKey<Item> bind(String string) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,string));
    }
}