package com.ytgld.the_wax.feature.gen;

import com.ytgld.the_wax.WaxMod;
import com.ytgld.the_wax.feature.WaterFeatureConfig;
import com.ytgld.the_wax.feature.WaxFeatureConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class TheConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_WAX = ResourceKey.create(Registries.CONFIGURED_FEATURE,
            Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_WATER_WAX = ResourceKey.create(Registries.CONFIGURED_FEATURE,
            Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"huge_water_wax"));

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> featureRegisterable) {
        FeatureUtils.register(featureRegisterable, BIG_WAX, TheFeatures.WAX_FEATURE_CONFIG_FEATURE,
                new WaxFeatureConfig(10,Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax")
                ));
        FeatureUtils.register(featureRegisterable, HUGE_WATER_WAX, TheFeatures.WATER_FEATURE_CONFIG_FEATURE,
                new WaterFeatureConfig(10,Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"huge_water_wax")
                ));
    }

    public static void init(){
    }
}
