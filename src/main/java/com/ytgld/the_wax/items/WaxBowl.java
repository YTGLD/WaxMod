package com.ytgld.the_wax.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class WaxBowl extends Item {
    public WaxBowl(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        builder.accept(Component.translatable("item.the_wax.wax_bowl.tip").withStyle(ChatFormatting.GRAY));
        builder.accept(Component.translatable("item.the_wax.wax_bowl.tip.1").withStyle(ChatFormatting.GRAY));
        builder.accept(Component.translatable("item.the_wax.wax_bowl_sus").withStyle(ChatFormatting.GRAY));
    }
}
