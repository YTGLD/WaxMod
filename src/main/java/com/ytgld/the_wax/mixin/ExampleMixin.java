package com.ytgld.the_wax.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Sniffer.class)
public class ExampleMixin {
	@Inject(at = @At("RETURN"), method = "dropSeed")
	private void dropSeed(CallbackInfo ci) {

	}
}