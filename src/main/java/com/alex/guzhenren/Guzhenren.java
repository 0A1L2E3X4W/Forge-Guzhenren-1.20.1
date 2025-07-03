package com.alex.guzhenren;

import com.alex.guzhenren.block.ModBlocks;
import com.alex.guzhenren.item.ModCreativeModeTabs;
import com.alex.guzhenren.item.ModItems;
import com.alex.guzhenren.networking.ModMessage;
import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Guzhenren.MOD_ID)
public class Guzhenren {
    public static final String MOD_ID = "guzhenren";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Guzhenren(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // CUSTOM ITEMS & CREATIVE TAB REGISTER
        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);

        // CUSTOM BLOCKS REGISTER
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessage.register();

        event.enqueueWork(() -> {
            assert ModBlocks.MOON_ORCHID.getId() != null;
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.MOON_ORCHID.getId(), ModBlocks.POTTED_MOON_ORCHID);
        });
    }
}