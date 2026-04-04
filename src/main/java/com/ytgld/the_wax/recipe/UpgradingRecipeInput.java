package com.ytgld.the_wax.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public class UpgradingRecipeInput {
   /*

    @Override
    public boolean matches(RecipeInput input, Level level) {
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
    public ItemStack assemble(RecipeInput input) {
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
    */
}
