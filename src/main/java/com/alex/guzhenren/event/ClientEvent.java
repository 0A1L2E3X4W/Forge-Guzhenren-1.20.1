package com.alex.guzhenren.event;

import com.alex.guzhenren.Guzhenren;
import com.alex.guzhenren.client.gui.ClientStatsGuiOverlay;
import com.alex.guzhenren.client.hud.ClientHudOverlay;
import com.alex.guzhenren.utils.CustomKeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEvent {

    @Mod.EventBusSubscriber(modid = Guzhenren.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvent {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(CustomKeyBindings.TEST_KEY);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("guzhenren.hud.essence", ClientHudOverlay.HUD_ESSENCE);
            event.registerAboveAll("guzhenren.hud.lifespan", ClientHudOverlay.HUD_APTITUDE);
        }
    }

    @Mod.EventBusSubscriber(modid = Guzhenren.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            Minecraft mc = Minecraft.getInstance();
            if (CustomKeyBindings.TEST_KEY.consumeClick() && mc.player != null) {
                mc.setScreen(new ClientStatsGuiOverlay());
            }
        }
    }
}
