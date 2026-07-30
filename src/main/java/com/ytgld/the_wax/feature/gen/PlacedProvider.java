package com.ytgld.the_wax.feature.gen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class PlacedProvider extends FabricDynamicRegistryProvider {
    public PlacedProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        add(registries, entries, ModPlacedFeatures.ROOT);
    }
    private void add(HolderLookup.Provider registries, Entries entries, ResourceKey<PlacedFeature> resourceKey) {
        Optional<? extends HolderLookup.RegistryLookup<PlacedFeature>> registryLookup =
                registries.lookup(Registries.PLACED_FEATURE);
        registryLookup.ifPresent(placedFeatureRegistryLookup ->
                entries.add(resourceKey, placedFeatureRegistryLookup.getOrThrow(resourceKey).value()));
    }
    @Override
    public String getName() {
        return "worldgen/placed_feature";
    }
}
