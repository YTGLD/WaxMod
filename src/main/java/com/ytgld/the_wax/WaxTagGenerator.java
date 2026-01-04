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
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class WaxTagGenerator extends FabricTagProvider.ItemTagProvider{
    public WaxTagGenerator(FabricDataOutput output,
                           CompletableFuture<HolderLookup.Provider>
                                   registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateRawBuilder(ItemTags.PLANKS)
                .addElement(Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax"));
    }

}