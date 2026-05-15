package com.ytgld.the_wax.feature.wax_melon;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record WaxMelonFeatureConfig(int number, Identifier blockId) implements FeatureConfiguration {
    public static final Codec<WaxMelonFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Codec.INT.fieldOf("number").forGetter(WaxMelonFeatureConfig::number),
                            Identifier.CODEC.fieldOf("blockID").forGetter(WaxMelonFeatureConfig::blockId))
                    .apply(instance, WaxMelonFeatureConfig::new));
}