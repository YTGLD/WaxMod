package com.ytgld.the_wax.feature.gen;

import com.ytgld.the_wax.WaxMod;
import com.ytgld.the_wax.feature.earth.UngroudRootFeatureConfig;
import com.ytgld.the_wax.feature.nether.NetherWaxFeatureConfig;
import com.ytgld.the_wax.feature.water.WaterFeatureConfig;
import com.ytgld.the_wax.feature.wax.RhizomeRootFeatureConfig;
import com.ytgld.the_wax.feature.wax.WaxFeatureConfig;
import com.ytgld.the_wax.feature.wax_melon.WaxMelonFeatureConfig;
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
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_NETHER_WAX = ResourceKey.create(Registries.CONFIGURED_FEATURE,
            Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"huge_nether_wax"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> UngroudRootFeatureConfig_ = ResourceKey.create(Registries.CONFIGURED_FEATURE,
            Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"root"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> WaxMelonFeatureConfig_ = ResourceKey.create(Registries.CONFIGURED_FEATURE,
            Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax_melon"));

    public static final ResourceKey<ConfiguredFeature<?, ?>> RhizomeRootFeatureConfig_ = ResourceKey.create(Registries.CONFIGURED_FEATURE,
            Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"rhizome"));

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> featureRegisterable) {
        FeatureUtils.register(featureRegisterable, BIG_WAX, TheFeatures.WAX_FEATURE_CONFIG_FEATURE,
                new WaxFeatureConfig(10,Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax")
                ));
        FeatureUtils.register(featureRegisterable, HUGE_WATER_WAX, TheFeatures.WATER_FEATURE_CONFIG_FEATURE,
                new WaterFeatureConfig(10,Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"huge_water_wax")
                ));
        FeatureUtils.register(featureRegisterable, HUGE_NETHER_WAX, TheFeatures.HUGE_NETHER_WAX_FEATURE,
                new NetherWaxFeatureConfig(10,Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"huge_nether_wax")
                ));
        FeatureUtils.register(featureRegisterable, UngroudRootFeatureConfig_, TheFeatures.UngroudRootFeature_,
                new UngroudRootFeatureConfig(10,Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"root")
                ));
        FeatureUtils.register(featureRegisterable, WaxMelonFeatureConfig_, TheFeatures.WaxMelonFeature_,
                new WaxMelonFeatureConfig(10,Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"wax_melon")
                ));

        FeatureUtils.register(featureRegisterable, RhizomeRootFeatureConfig_, TheFeatures.RhizomeRootFeature_,
                new RhizomeRootFeatureConfig(10,Identifier.fromNamespaceAndPath(WaxMod.MOD_ID,"rhizome")
                ));
    }

    public static void init(){
    }
}
