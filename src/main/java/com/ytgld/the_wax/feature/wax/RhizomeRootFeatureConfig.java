package com.ytgld.the_wax.feature.wax;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record RhizomeRootFeatureConfig(int number, Identifier blockId) implements FeatureConfiguration {
    public static final Codec<RhizomeRootFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Codec.INT.fieldOf("number").forGetter(RhizomeRootFeatureConfig::number),
                            Identifier.CODEC.fieldOf("blockID").forGetter(RhizomeRootFeatureConfig::blockId))
                    .apply(instance, RhizomeRootFeatureConfig::new));
}