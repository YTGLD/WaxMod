package com.ytgld.the_wax.recipe;

import com.ytgld.the_wax.WaxMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class WaxRecipes {
    public static void init(){}

    public static final RecipeSerializer<UpgradingRecipe> UPGRADING_RECIPE_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Identifier.fromNamespaceAndPath(WaxMod.MOD_ID, "sus"),
            new RecipeSerializer<>(UpgradingRecipe.CODEC, UpgradingRecipe.STREAM_CODEC)
    );
}
