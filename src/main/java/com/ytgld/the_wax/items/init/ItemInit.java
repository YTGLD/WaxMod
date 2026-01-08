package com.ytgld.the_wax.items.init;

import com.ytgld.the_wax.WaxMod;
import com.ytgld.the_wax.items.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.component.UseEffects;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ItemInit {
    public static final Item ItemWax = register("item_wax", SmallWaxItem::new,
            new Item.Properties().rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder()
                            .nutrition(5).saturationModifier(0.8f).build())
                    .component(DataComponents.SUSPICIOUS_STEW_EFFECTS,
                            new SuspiciousStewEffects(List.of(
                                    new SuspiciousStewEffects.Entry(MobEffects.STRENGTH, 300),
                                    new SuspiciousStewEffects.Entry(MobEffects.REGENERATION,200)))));
    public static final Item GOLDEN_WAX = register("golden_wax", GoldWax::new,
            new Item.Properties().rarity(Rarity.EPIC).food(new FoodProperties.Builder()
                            .nutrition(10).saturationModifier(0.5f).build())
                    .component(DataComponents.SUSPICIOUS_STEW_EFFECTS,
                            new SuspiciousStewEffects(List.of(
                                    new SuspiciousStewEffects.Entry(MobEffects.ABSORPTION, 1200),
                                    new SuspiciousStewEffects.Entry(MobEffects.STRENGTH, 900),
                                    new SuspiciousStewEffects.Entry(MobEffects.RESISTANCE, 750),
                                    new SuspiciousStewEffects.Entry(MobEffects.REGENERATION,600)))));

    public static final Item WAX_STRIPS = register("wax_strips", WaxStrips::new,
            new Item.Properties().rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder()
                            .nutrition(6).saturationModifier(1.1f).build())
                    .component(DataComponents.SUSPICIOUS_STEW_EFFECTS,
                            new SuspiciousStewEffects(List.of(
                                    new SuspiciousStewEffects.Entry(MobEffects.SPEED, 600)))));
    public static final Item WAX_CANDLE = register("wax_pipe_candle_item", WaxPipeCandleItem::new,
            new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Item PIPE_HONEY_CANDLE = register("pipe_honey_candle_item", PipeHoneyCandleItem::new,
            new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Item WAX_JUMP_SLIME_ITEM = register("wax_jump_slime_item", WaxJumpSlimeItem::new,
            new Item.Properties().rarity(Rarity.UNCOMMON));

    public static final Item SMALL_JUMPING_SLIME = register("small_jump_slime", Item::new,
            new Item.Properties().rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder()
                            .nutrition(7).saturationModifier( 1.3f).build())
                    .component(DataComponents.SUSPICIOUS_STEW_EFFECTS,
                            new SuspiciousStewEffects(List.of(
                                    new SuspiciousStewEffects.Entry(MobEffects.SPEED, 1200),
                                    new SuspiciousStewEffects.Entry(MobEffects.JUMP_BOOST, 1200)))));


    private static Item register(String path, Function<Item.Properties, Item> factory, Item.Properties settings) {
        final Identifier identifier = Identifier.fromNamespaceAndPath(WaxMod.MOD_ID, path);
        final ResourceKey<Item> registryKey = ResourceKey.create(Registries.ITEM, identifier);
        return Items.registerItem(registryKey,factory,settings);
    }
    public static void init() {}

}
