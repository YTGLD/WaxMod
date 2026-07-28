package com.ytgld.the_wax.block;

import com.ytgld.the_wax.WaxMod;
import com.ytgld.the_wax.block.earth.*;
import com.ytgld.the_wax.block.nether.*;
import com.ytgld.the_wax.block.water.*;
import com.ytgld.the_wax.block.wax.*;
import com.ytgld.the_wax.block.wax_melon.*;
import com.ytgld.the_wax.block.wax_melon.candle.WaxMelonCandle;
import com.ytgld.the_wax.block.wax_melon.candle.WaxMelonCandleHalf;
import com.ytgld.the_wax.block.wax_melon.candle.WaxMelonCandleOil;
import com.ytgld.the_wax.block.wax_melon.totem.WaxTotemBase;
import com.ytgld.the_wax.block.wax_melon.totem.WaxTotemUse;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
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
    public static final Block WATER_WAX_BASE = register("water_wax_base", WaterWaxBase::new,
            BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.WOOD));
    public static final Block WAX_SPONGE = register("wax_sponge", WaxSponge::new,
            BlockBehaviour.Properties.of().strength(0.3f).sound(SoundType.SPONGE));
    public static final Block WET_WAX_SPONGE = register("wet_wax_sponge", WetWaxSponge::new,
            BlockBehaviour.Properties.of().strength(0.45F).sound(SoundType.WET_SPONGE));
    public static final Block PIPE_WALL = register("pipe_wall", PipeWall::new,
            BlockBehaviour.Properties.of().strength(0.45F).lightLevel((state)->{
                return 8;
            }).sound(SoundType.WOOD));
    public static final Block WAX_FLOWER = register("wax_flower", WaxFlower::new,
            BlockBehaviour.Properties.of().strength(0.1F).lightLevel((state)->{
                return 15;
            }).sound(SoundType.WET_GRASS));



    public static final Block NETHER_WAX = register("nether_wax", NetherWaxBlock::new,
            BlockBehaviour.Properties.of().strength(0.8f).lightLevel((state)->{
                return 15;
            }).sound(SoundType.WOOD));
    public static final Block NETHER_PIPE = register("nether_pipe", NetherPipe::new,
            BlockBehaviour.Properties.of().strength(1f).sound(SoundType.WOOD));
    public static final Block NETHER_PIPE_OTHER = register("nether_pipe_other", NetherPipeOther::new,
            BlockBehaviour.Properties.of().strength(1f).sound(SoundType.WOOD));
    public static final Block NETHER_WAX_BASE = register("nether_wax_base", NetherWaxBase::new,
            BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.WOOD));
    public static final Block NETHER_FLOWER = register("nether_flower", NetherFlower::new,
            BlockBehaviour.Properties.of().strength(0.8f).lightLevel((state)->{
                return 11;
            }).sound(SoundType.GRASS));
    public static final Block NETHER_PIPE_PLANKS = register("nether_pipe_planks", NetherPipePlanks::new,
            BlockBehaviour.Properties.of().strength(1f).sound(SoundType.WOOD));

    public static final Block MelonRoot_ = register("melon_root", MelonRoot::new,
            BlockBehaviour.Properties.of().strength(2f).sound(SoundType.WOOD));
    public static final Block MelonRootFlower_ = register("melon_root_flower", MelonRootFlower::new,
            BlockBehaviour.Properties.of().strength(1f).lightLevel((state)->{
                return 10;
            }).sound(SoundType.WOOD));
    public static final Block MelonVine_ = register("melon_vine", MelonVine::new,
            BlockBehaviour.Properties.of().strength(0.2f).lightLevel((state)->{
                return 8;
            }).sound(SoundType.WOOD));
    public static final Block MelonVinePlant_ = register("melon_vine_plant", MelonVinePlant::new,
            BlockBehaviour.Properties.of().strength(0.2f).lightLevel((state)->{
                return 8;
            }).sound(SoundType.WOOD));
    public static final Block GiantRhizome_ = register("giant_rhizome", GiantRhizome::new,
            BlockBehaviour.Properties.of().strength(3f).sound(SoundType.WOOD));
    /**
     * 2026-5-15
     */
    public static final Block WaxMelonStem_ = register("wax_melon_stem", WaxMelonStem::new,
            BlockBehaviour.Properties.of().strength(3f).sound(SoundType.WOOD));
    public static final Block WaxMelonStemPlack_ = register("wax_melon_stem_plack", WaxMelonStemPlack::new,
            BlockBehaviour.Properties.of().strength(2f).sound(SoundType.WOOD));
    public static final Block BeeswaxMelon_ = register("beeswax_melon", BeeswaxMelon::new,
            BlockBehaviour.Properties.of().strength(1f).lightLevel((state)->15).sound(SoundType.WOOD));
    public static final Block WaxMelonStemSeed_ = register("wax_melon_ste_seed", WaxMelonStemSeed::new,
            BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.WOOD));
    /**
     * 2026-5-16
     */
    public static final Block WaxMelonCandle_ = register("wax_melon_candle", WaxMelonCandle::new,
            BlockBehaviour.Properties.of()
                    .lightLevel(WaxMelonCandle.LIGHT_EMISSION)
                    .strength(0.8f)
                    .randomTicks()
                    .sound(SoundType.CANDLE));
    public static final Block WaxMelonCandleHalf_ = register("wax_melon_wax_slab", WaxMelonCandleHalf::new,
            BlockBehaviour.Properties.of().strength(0.4f).sound(SoundType.CANDLE));
    public static final Block WaxMelonCandleOil_ = register("wax_melon_wax_oil", WaxMelonCandleOil::new,
            BlockBehaviour.Properties.of().friction(0.9f).strength(0.2f).sound(SoundType.CANDLE));

    /**
     * 2026-5-27
     */
    public static final Block StrippedWaxMelonStem_ = register("stripped_wax_melon_stem", StrippedWaxMelonStem::new,
            BlockBehaviour.Properties.of().strength(3f).sound(SoundType.WOOD));

    /**
     * 2026-6-26
     */
    public static final Block WaxTotemBase_ = register("wax_totem_base", WaxTotemBase::new,
            BlockBehaviour.Properties.of().lightLevel((state)->10).strength(3f).sound(SoundType.WOOD));
    public static final Block WaxTotemUse_ = register("wax_totem_use", WaxTotemUse::new,
            BlockBehaviour.Properties.of()
                    .randomTicks()
                    .lightLevel((state)->{
                        if (state.getValue(WaxTotemUse.polymer)){
                            if (state.getValue(WaxTotemUse.huge)) {
                                return 15;
                            }
                            return 10;
                        }
                        return 0;
                    })
                    .strength(3f).sound(SoundType.WOOD));

    /**
     * 2026-7-28
     */
    public static final Block RhizomeBlock_ = register("rhizome", RhizomeBlock::new,
            BlockBehaviour.Properties.of().strength(1.5f).sound(SoundType.WOOD));

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));
        ResourceKey<Item> itemKey = keyOfItem(name);
        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(WaxMod.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(WaxMod.MOD_ID, name));
    }
    public static void init() {}

}
