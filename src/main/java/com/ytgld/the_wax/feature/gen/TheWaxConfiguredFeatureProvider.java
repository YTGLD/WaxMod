package com.ytgld.the_wax.feature.gen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class TheWaxConfiguredFeatureProvider  extends FabricDynamicRegistryProvider {

    public TheWaxConfiguredFeatureProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }


    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        add(registries, entries, TheConfiguredFeatures.BIG_WAX);
        add(registries, entries, TheConfiguredFeatures.HUGE_WATER_WAX);
    }
    private void add(HolderLookup.Provider registries, Entries entries, ResourceKey<ConfiguredFeature<?, ?>> resourceKey) {
        Optional<? extends HolderLookup.RegistryLookup<ConfiguredFeature<?, ?>>> configuredFeatureRegistryLookup =
                registries.lookup(Registries.CONFIGURED_FEATURE);

        entries.add(resourceKey, configuredFeatureRegistryLookup.get().getOrThrow(resourceKey).value());
    }
    @Override
    public @NotNull String getName() {
        return "worldgen/configured_feature";
    }

}

