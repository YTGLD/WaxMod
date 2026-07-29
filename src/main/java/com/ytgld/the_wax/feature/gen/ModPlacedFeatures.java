package com.ytgld.the_wax.feature.gen;

import com.ytgld.the_wax.WaxMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> ROOT =
            ResourceKey.create(Registries.PLACED_FEATURE,
                    Identifier.fromNamespaceAndPath(WaxMod.MOD_ID, "root"));

    public static final ResourceKey<PlacedFeature> Rhizome =
            ResourceKey.create(Registries.PLACED_FEATURE,
                    Identifier.fromNamespaceAndPath(WaxMod.MOD_ID, "rhizome"));

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        context.register(ROOT,
                new PlacedFeature(
                        context.lookup(Registries.CONFIGURED_FEATURE)
                                .getOrThrow(TheConfiguredFeatures.UngroudRootFeatureConfig_),
                        List.of()
                ));
        context.register(Rhizome,
                new PlacedFeature(
                        context.lookup(Registries.CONFIGURED_FEATURE)
                                .getOrThrow(TheConfiguredFeatures.RhizomeRootFeatureConfig_),
                        List.of()
                ));
    }
    public static void init() {
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.HAS_MINESHAFT),
                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.ROOT);
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.HAS_MINESHAFT),
                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                ModPlacedFeatures.Rhizome);
    }
}