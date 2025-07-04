package com.alex.guzhenren.item;

import com.alex.guzhenren.Guzhenren;
import com.alex.guzhenren.item.custom.PrimevalStone;
import com.alex.guzhenren.item.custom.gu.lifespan.*;
import com.alex.guzhenren.item.custom.gu.special.Hope;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Guzhenren.MOD_ID);

    // ========== GU MATERIALS ==========
    public static final RegistryObject<Item> APERTURE = ITEMS.register("aperture",
            () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PRIMEVAL_STONE = ITEMS.register("primeval_stone",
            () -> new PrimevalStone(new Item.Properties().stacksTo(64), 4000));

    // ========== MORTAL GU ==========
    public static final RegistryObject<Item> HOPE = ITEMS.register("hope",
            () -> new Hope(new Item.Properties().stacksTo(64)));

    public static final RegistryObject<Item> YEAR_LIFESPAN_GU = ITEMS.register("year_lifespan",
            () -> new YearLifespanGu(new Item.Properties().stacksTo(64), 1));
    public static final RegistryObject<Item> TEN_YEAR_LIFESPAN_GU = ITEMS.register("ten_years_lifespan",
            () -> new TenYearsLifespanGu(new Item.Properties().stacksTo(64), 10));

    // ========== FOODS ==========
    public static final RegistryObject<Item> LIQUOR = ITEMS.register("liquor",
            () -> new Item(new Item.Properties().stacksTo(64).food(ModFoods.LIQUOR)));
    public static final RegistryObject<Item> HUMAN_HEART = ITEMS.register("human_heart",
            () -> new Item(new Item.Properties().stacksTo(64).food(ModFoods.HUMAN_HEART)));

    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }
}
