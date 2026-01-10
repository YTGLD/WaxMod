package com.ytgld.the_wax.block.init;

import com.ytgld.the_wax.WaxMod;
import com.ytgld.the_wax.block.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class BlockInit {
    public static final Block Wax = register("wax", WaxGourd::new,
            BlockBehaviour.Properties.of().strength(0.5F).lightLevel((state)->{
                return 12;
            }).sound(SoundType.WOOD));
    public static final Block SMALL_WAX = register("small_wax", SmallWax::new,
            BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.WOOD));
    public static final Block SMALL_WAX_GOLDEN = register("small_wax_golden", SmallWaxGolden::new,
            BlockBehaviour.Properties.of().strength(0.5F).lightLevel((state)->{
              return 10;
            }).sound(SoundType.WOOD));
    public static final Block WAX_PINE = register("wax_vine_pipe", WaxVinePipe::new,
            BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.WOOD));
    public static final Block WAX_BASE = register("wax_base", WaxBase::new,
            BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.WOOD));
    public static final Block WAX_SUGAR = register("wax_sugar", WaxSugar::new,
            BlockBehaviour.Properties.of().strength(0.2f)
                    .noOcclusion().friction(0.98f)
                    .isSuffocating(Blocks::never)
                    .isViewBlocking(Blocks::never)
                    .sound(SoundType.GLASS));

    public static final Block WAX_PIPE_CANDLE = register("wax_pipe_candle", WaxPipeCandle::new,
            BlockBehaviour.Properties.of()
                    .lightLevel(WaxPipeCandle.LIGHT_EMISSION)
                    .strength(0.5f)
                    .randomTicks()
                    .sound(SoundType.WOOD));
    public static final Block PIPE_HONEY_CANDLE = register("pipe_honey_candle", PipeHoneyCandle::new,
            BlockBehaviour.Properties.of()
                    .lightLevel(PipeHoneyCandle.LIGHT_EMISSION)
                    .strength(0.8F)
                    .randomTicks()
                    .sound(SoundType.WOOD));
    public static final Block WAX_JUMP_SLIME = register("wax_jump_slime", WaxJumpSlime::new,
            BlockBehaviour.Properties.of().strength(1)
                    .noOcclusion().friction(0.98f).lightLevel((state)->{
                        return 8;
                    })
                    .isSuffocating(Blocks::never)
                    .isViewBlocking(Blocks::never)
                    .sound(SoundType.SLIME_BLOCK));
    public static final Block WATER_WAX = register("water_wax", WaterWax::new,
            BlockBehaviour.Properties.of().strength(0.75f).lightLevel((state)->{
                return 12;
            }).sound(SoundType.WOOD));
    public static final Block HUGE_PIPE = register("huge_pipe", HugePipe::new,
            BlockBehaviour.Properties.of().strength(0.9f).lightLevel((state)->{
                return 8;
            }).sound(SoundType.WOOD));
    private static Block register(String path, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        final Identifier identifier = Identifier.fromNamespaceAndPath(WaxMod.MOD_ID, path);
        final ResourceKey<Block> registryKey = ResourceKey.create(Registries.BLOCK, identifier);
        final Block block = Blocks.register(registryKey, factory, settings);
        Items.registerBlock(block);
        return block;
    }
    public static void init() {}

}
