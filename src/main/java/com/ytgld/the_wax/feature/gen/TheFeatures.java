package com.ytgld.the_wax.feature.gen;

import com.ytgld.the_wax.WaxMod;
import com.ytgld.the_wax.feature.earth.UngroudRootFeature;
import com.ytgld.the_wax.feature.earth.UngroudRootFeatureConfig;
import com.ytgld.the_wax.feature.nether.NetherWaxFeature;
import com.ytgld.the_wax.feature.nether.NetherWaxFeatureConfig;
import com.ytgld.the_wax.feature.water.WaterFeatureConfig;
import com.ytgld.the_wax.feature.water.WaterWaxFeature;
import com.ytgld.the_wax.feature.wax.BigWaxFeature;
import com.ytgld.the_wax.feature.wax.WaxFeatureConfig;
import com.ytgld.the_wax.feature.wax_melon.WaxMelonFeature;
import com.ytgld.the_wax.feature.wax_melon.WaxMelonFeatureConfig;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class TheFeatures {
    public static final Feature<WaxFeatureConfig> WAX_FEATURE_CONFIG_FEATURE =
            register("huge_wax", new BigWaxFeature(WaxFeatureConfig.CODEC));
    public static final Feature<WaterFeatureConfig> WATER_FEATURE_CONFIG_FEATURE =
            register("huge_water_wax", new WaterWaxFeature(WaterFeatureConfig.CODEC));
    public static final Feature<NetherWaxFeatureConfig> HUGE_NETHER_WAX_FEATURE =
            register("huge_nether_wax", new NetherWaxFeature(NetherWaxFeatureConfig.CODEC));
    public static final Feature<UngroudRootFeatureConfig> UngroudRootFeature_ =
            register("root", new UngroudRootFeature(UngroudRootFeatureConfig.CODEC));
    public static final Feature<WaxMelonFeatureConfig> WaxMelonFeature_ =
            register("wax_melon", new WaxMelonFeature(WaxMelonFeatureConfig.CODEC));
    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(BuiltInRegistries.FEATURE, Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,name), feature);
    }
    public static void init() {
    }
}
