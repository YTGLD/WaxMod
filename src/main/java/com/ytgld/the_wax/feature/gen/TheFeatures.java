package com.ytgld.the_wax.feature.gen;

import com.ytgld.the_wax.WaxMod;
import com.ytgld.the_wax.feature.BigWaxFeature;
import com.ytgld.the_wax.feature.WaterFeatureConfig;
import com.ytgld.the_wax.feature.WaterWaxFeature;
import com.ytgld.the_wax.feature.WaxFeatureConfig;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class TheFeatures {
    public static final Feature<WaxFeatureConfig> WAX_FEATURE_CONFIG_FEATURE = register("huge_wax", new BigWaxFeature(WaxFeatureConfig.CODEC));
    public static final Feature<WaterFeatureConfig> WATER_FEATURE_CONFIG_FEATURE = register("huge_water_wax", new WaterWaxFeature(WaterFeatureConfig.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(BuiltInRegistries.FEATURE, Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,name), feature);
    }

    public static void init() {
    }
}
