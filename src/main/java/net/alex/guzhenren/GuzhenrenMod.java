package net.alex.guzhenren;

import net.alex.guzhenren.block.ModBlocks;
import net.alex.guzhenren.item.ModCreativeModeTabs;
import net.alex.guzhenren.item.ModItems;
import net.alex.guzhenren.networking.ModMessage;
import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(GuzhenrenMod.MOD_ID)
public class GuzhenrenMod {

    public static final String MOD_ID = "guzhenren";
    public static final Logger LOGGER = LogUtils.getLogger();

    public GuzhenrenMod(FMLJavaModLoadingContext context) {
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