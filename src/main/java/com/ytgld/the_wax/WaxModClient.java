package com.ytgld.the_wax;

import com.ytgld.the_wax.block.init.BlockInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.impl.client.rendering.fluid.FluidRenderingRegistryImpl;

public class WaxModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FluidRenderingRegistryImpl.setBlockTransparency(BlockInit.NETHER_FLOWER, true);
        FluidRenderingRegistryImpl.setBlockTransparency(BlockInit.NETHER_WAX_BASE, true);
        FluidRenderingRegistryImpl.setBlockTransparency(BlockInit.WATER_WAX_BASE, true);

        FluidRenderingRegistryImpl.setBlockTransparency(BlockInit.PIPE_HONEY_CANDLE, true);
        FluidRenderingRegistryImpl.setBlockTransparency(BlockInit.SMALL_WAX, true);
        FluidRenderingRegistryImpl.setBlockTransparency(BlockInit.SMALL_WAX_GOLDEN, true);
        FluidRenderingRegistryImpl.setBlockTransparency(BlockInit.WAX_BASE, true);
        FluidRenderingRegistryImpl.setBlockTransparency(BlockInit.WAX_FLOWER, true);
        FluidRenderingRegistryImpl.setBlockTransparency(BlockInit.WAX_JUMP_SLIME, true);
        FluidRenderingRegistryImpl.setBlockTransparency(BlockInit.WAX_PIPE_CANDLE, true);
        FluidRenderingRegistryImpl.setBlockTransparency(BlockInit.WAX_SUGAR, true);
        FluidRenderingRegistryImpl.setBlockTransparency(BlockInit.WAX_PINE, true);
    }
}
