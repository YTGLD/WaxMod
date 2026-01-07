package com.ytgld.the_wax;

import com.ytgld.the_wax.feature.gen.TheConfiguredFeatures;
import com.ytgld.the_wax.feature.gen.TheWaxConfiguredFeatureProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class WaxDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		fabricDataGenerator.createPack().addProvider(WaxTagGenerator::new);
		fabricDataGenerator.createPack().addProvider(WaxTagGeneratorBlock::new);
		fabricDataGenerator.createPack().addProvider(TheWaxConfiguredFeatureProvider::new);
	}
	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.CONFIGURED_FEATURE, TheConfiguredFeatures::bootstrap);
	}
}
