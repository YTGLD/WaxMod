package com.ytgld.the_wax;

import com.ytgld.the_wax.block.init.BlockInit;
import com.ytgld.the_wax.items.init.ItemInit;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class WaxTab {
    public static final CreativeModeTab TEST_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(BlockInit.Wax))
            .title(Component.translatable("itemGroup.the_wax.group"))
            .displayItems((context, entries) -> {
                entries.accept(BlockInit.Wax);
                entries.accept(BlockInit.WAX_PINE);
                entries.accept(BlockInit.WAX_BASE);
                entries.accept(BlockInit.WAX_SUGAR);
                entries.accept(BlockInit.WATER_WAX);
                entries.accept(BlockInit.HUGE_PIPE);



                entries.accept(ItemInit.ItemWax);
                entries.accept(ItemInit.GOLDEN_WAX);
                entries.accept(ItemInit.WAX_STRIPS);
                entries.accept(ItemInit.WAX_CANDLE);
                entries.accept(ItemInit.PIPE_HONEY_CANDLE);
                entries.accept(ItemInit.WAX_JUMP_SLIME_ITEM);
                entries.accept(ItemInit.SMALL_JUMPING_SLIME);



            })
            .build();
}
