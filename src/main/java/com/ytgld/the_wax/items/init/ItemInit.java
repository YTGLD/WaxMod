package com.ytgld.the_wax.items.init;

import com.ytgld.the_wax.WaxMod;
import com.ytgld.the_wax.items.wax.*;
import com.ytgld.the_wax.items.wax_melon.WaxMelonWax;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.SuspiciousStewEffects;

import java.util.List;
import java.util.function.Function;

public class ItemInit {
    public static final Item ITEM_WAX = register("item_wax", SmallWaxItem::new,
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

    public static final Item SMALL_WATER_WAX = register("small_water_wax", Item::new,
            new Item.Properties().rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder().alwaysEdible()
                            .nutrition(4).saturationModifier( 0.5f).build())
                    .component(DataComponents.SUSPICIOUS_STEW_EFFECTS,
                            new SuspiciousStewEffects(List.of(
                                    new SuspiciousStewEffects.Entry(MobEffects.WATER_BREATHING, 1200)))));
    public static final Item WaxMelonWax_ = register("wax_melon_wax", WaxMelonWax::new,
            new Item.Properties().rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder().alwaysEdible()
                            .nutrition(2).saturationModifier( 1).build()));
    public static final Item RichInFlourRhizomes_ = register("richin_flour_rhizomes", RichInFlourRhizomes::new,
            new Item.Properties().rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder().alwaysEdible()
                    .nutrition(2).saturationModifier( 1).build()));

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(WaxMod.MOD_ID, name));
        T item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void init() {}
    public static final Item WaxBowl_ = register("wax_bowl", WaxBowl::new,
            new Item.Properties().rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder().alwaysEdible()
                            .nutrition(2).saturationModifier(0.5f).build()));

    public static final Item WaxBowl_sus = register("wax_bowl_sus", Item::new,
            new Item.Properties().rarity(Rarity.UNCOMMON).food(new FoodProperties.Builder().alwaysEdible()
                    .nutrition(6).saturationModifier(0.6f).build()).
                    component(DataComponents.SUSPICIOUS_STEW_EFFECTS, SuspiciousStewEffects.EMPTY));

}
