package com.ytgld.the_wax.feature.gen;

import com.ytgld.the_wax.WaxMod;
import com.ytgld.the_wax.feature.earth.UngroudRootFeature;
import com.ytgld.the_wax.feature.earth.UngroudRootFeatureConfig;
import com.ytgld.the_wax.feature.wax.BigWaxFeature;
import com.ytgld.the_wax.feature.wax.WaxFeatureConfig;
import com.ytgld.the_wax.feature.nether.NetherWaxFeature;
import com.ytgld.the_wax.feature.nether.NetherWaxFeatureConfig;
import com.ytgld.the_wax.feature.water.WaterFeatureConfig;
import com.ytgld.the_wax.feature.water.WaterWaxFeature;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import vectorwing.farmersdelight.common.registry.ModBiomeModifiers;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.List;

public class TheFeatures {
    public static final Feature<WaxFeatureConfig> WAX_FEATURE_CONFIG_FEATURE =
            register("huge_wax", new BigWaxFeature(WaxFeatureConfig.CODEC));
    public static final Feature<WaterFeatureConfig> WATER_FEATURE_CONFIG_FEATURE =
            register("huge_water_wax", new WaterWaxFeature(WaterFeatureConfig.CODEC));
    public static final Feature<NetherWaxFeatureConfig> HUGE_NETHER_WAX_FEATURE =
            register("huge_nether_wax", new NetherWaxFeature(NetherWaxFeatureConfig.CODEC));
    public static final Feature<UngroudRootFeatureConfig> UngroudRootFeature_ =
            register("root", new UngroudRootFeature(UngroudRootFeatureConfig.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(BuiltInRegistries.FEATURE, Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,name), feature);
    }
    public static void init() {
    }
}
