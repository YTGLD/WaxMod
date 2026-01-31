package com.ytgld.the_wax.feature.nether;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record NetherWaxFeatureConfig(int number, Identifier blockId) implements FeatureConfiguration {
    public static final Codec<NetherWaxFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Codec.INT.fieldOf("number").forGetter(NetherWaxFeatureConfig::number),
                            Identifier.CODEC.fieldOf("blockID").forGetter(NetherWaxFeatureConfig::blockId))
                    .apply(instance, NetherWaxFeatureConfig::new));
}