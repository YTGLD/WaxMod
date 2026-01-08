package com.ytgld.the_wax.items;

import com.ytgld.the_wax.block.init.BlockInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class WaxJumpSlimeItem extends BlockItem {
    public WaxJumpSlimeItem( Properties properties) {
        super(BlockInit.WAX_JUMP_SLIME, properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag);
        consumer.accept(Component.translatable("item.the_wax.wax_jump_slime_item.tip").withStyle(ChatFormatting.GRAY));
        consumer.accept(Component.translatable("item.the_wax.wax_jump_slime_item.tip.2").withStyle(ChatFormatting.GRAY));
    }
}

