package com.ytgld.the_wax.feature.earth;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record UngroudRootFeatureConfig (int number, Identifier blockId) implements FeatureConfiguration {
    public static final Codec<UngroudRootFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Codec.INT.fieldOf("number").forGetter(UngroudRootFeatureConfig::number),
                            Identifier.CODEC.fieldOf("blockID").forGetter(UngroudRootFeatureConfig::blockId))
                    .apply(instance, UngroudRootFeatureConfig::new));
}
