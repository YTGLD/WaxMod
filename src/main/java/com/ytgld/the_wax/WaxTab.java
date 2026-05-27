package com.ytgld.the_wax;

import com.ytgld.the_wax.block.init.BlockInit;
import com.ytgld.the_wax.items.init.ItemInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class WaxTab {
    public static final CreativeModeTab TEST_GROUP = CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM,9)
            .icon(() -> new ItemStack(BlockInit.Wax))
            .title(Component.translatable("itemGroup.the_wax.group"))
            .displayItems((context, entries) -> {
                entries.accept(BlockInit.NETHER_WAX_BASE);
                entries.accept(BlockInit.WATER_WAX_BASE);
                entries.accept(BlockInit.WAX_BASE);


                entries.accept(BlockInit.Wax);
                entries.accept(BlockInit.WAX_PINE);
                entries.accept(BlockInit.WAX_SUGAR);
                entries.accept(BlockInit.WATER_WAX);
                entries.accept(BlockInit.HUGE_PIPE);
                entries.accept(BlockInit.WAX_SPONGE);
                entries.accept(BlockInit.WET_WAX_SPONGE);
                entries.accept(BlockInit.PIPE_WALL);
                entries.accept(BlockInit.WAX_FLOWER);
                entries.accept(BlockInit.NETHER_WAX);
                entries.accept(BlockInit.NETHER_PIPE);
                entries.accept(BlockInit.NETHER_PIPE_OTHER);
                entries.accept(BlockInit.NETHER_PIPE_PLANKS);
                entries.accept(BlockInit.NETHER_FLOWER);



                entries.accept(ItemInit.ITEM_WAX);
                entries.accept(ItemInit.GOLDEN_WAX);
                entries.accept(ItemInit.WAX_STRIPS);
                entries.accept(ItemInit.WAX_CANDLE);
                entries.accept(ItemInit.PIPE_HONEY_CANDLE);
                entries.accept(ItemInit.WAX_JUMP_SLIME_ITEM);
                entries.accept(ItemInit.SMALL_JUMPING_SLIME);
                entries.accept(ItemInit.SMALL_WATER_WAX);

                entries.accept(ItemInit.WaxBowl_);
                entries.accept(ItemInit.WaxBowl_sus);

                entries.accept(BlockInit.MelonRoot_);
                entries.accept(BlockInit.MelonRootFlower_);
                entries.accept(BlockInit.MelonVine_);
                entries.accept(BlockInit.GiantRhizome_);


                entries.accept(BlockInit.WaxMelonStem_);
                entries.accept(BlockInit.WaxMelonStemPlack_);
                entries.accept(BlockInit.BeeswaxMelon_);
                entries.accept(ItemInit.WaxMelonWax_);
                entries.accept(BlockInit.WaxMelonStemSeed_);
                entries.accept(BlockInit.WaxMelonCandle_);
                entries.accept(BlockInit.WaxMelonCandleHalf_);
                entries.accept(BlockInit.WaxMelonCandleOil_);
                entries.accept(BlockInit.StrippedWaxMelonStem_);

            })
            .build();
}
