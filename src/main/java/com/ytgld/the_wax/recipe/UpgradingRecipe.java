package com.ytgld.the_wax.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ytgld.the_wax.items.init.ItemInit;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SuspiciousEffectHolder;

import java.util.List;

public class UpgradingRecipe extends CustomRecipe {

    public static final UpgradingRecipe INSTANCE = new UpgradingRecipe();
    public static final MapCodec<UpgradingRecipe> CODEC =MapCodec.unit(INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, UpgradingRecipe> STREAM_CODEC= StreamCodec.unit(INSTANCE);



    public UpgradingRecipe() {
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return WaxRecipes.UPGRADING_RECIPE_SERIALIZER;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        boolean flower = false;
        boolean honey = false;
        boolean wheat2 = false;
        boolean powder = false;
        for(int i = 0; i < input.size(); ++i) {
            ItemStack item = input.getItem(i);
            if (!item.isEmpty()) {
                if (item.is(Items.BROWN_MUSHROOM) && !wheat2) {
                    wheat2 = true;
                } else if (item.is(Items.RED_MUSHROOM) && !honey) {
                    honey = true;
                } else if (item.is(ItemTags.SMALL_FLOWERS) && !flower) {
                    flower = true;
                } else {
                    if (!item.is(ItemInit.WaxBowl_) || powder) {
                        return false;
                    }
                    powder = true;
                }
            }
        }
        return wheat2 && honey && flower && powder;

    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        ItemStack cookie = new ItemStack(ItemInit.WaxBowl_sus, 1);

        for(int i = 0; i < input.size(); ++i) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty()) {
                SuspiciousEffectHolder holder = SuspiciousEffectHolder.tryGet(stack.getItem());
                if (holder != null) {
                    for (SuspiciousStewEffects.Entry entry : holder.getSuspiciousEffects().effects()) {
                        Holder<MobEffect> effect = entry.effect();
                        int duration = entry.duration();
                        cookie.set(DataComponents.SUSPICIOUS_STEW_EFFECTS,new SuspiciousStewEffects(List.of(
                                new SuspiciousStewEffects.Entry(effect,duration)
                        )));
                        break;
                    }
                }
            }
        }

        return cookie;
    }
}