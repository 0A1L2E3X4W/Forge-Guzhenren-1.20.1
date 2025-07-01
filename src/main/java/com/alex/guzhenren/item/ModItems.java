package com.alex.guzhenren.item;

import com.alex.guzhenren.Guzhenren;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Guzhenren.MOD_ID);

    // ========== GU MATERIALS ==========
    public static final RegistryObject<Item> APERTURE = ITEMS.register("aperture", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> HUMAN_HEART = ITEMS.register("human_heart", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PRIMEVAL_STONE = ITEMS.register("primeval_stone", () -> new Item(new Item.Properties().stacksTo(64)));

    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }
}
