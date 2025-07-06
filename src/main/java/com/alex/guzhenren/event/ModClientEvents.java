package com.alex.guzhenren.event;

import com.alex.guzhenren.Guzhenren;
import com.alex.guzhenren.client.gui.ClientStatsGuiOverlay;
import com.alex.guzhenren.client.hud.ClientStatsHudOverlay;
import com.alex.guzhenren.utils.ModKeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModClientEvents {

    @Mod.EventBusSubscriber(modid = Guzhenren.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvent {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(ModKeyBindings.TEST_KEY);
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("guzhenren.hud.essence", ClientStatsHudOverlay.HUD_ESSENCE);
            event.registerAboveAll("guzhenren.hud.lifespan", ClientStatsHudOverlay.HUD_APTITUDE);
            event.registerAboveAll("guzhenren.hud.refinement", ClientStatsHudOverlay.HUD_REFINEMENT);
        }
    }

    @Mod.EventBusSubscriber(modid = Guzhenren.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            Minecraft minecraft = Minecraft.getInstance();
            if (ModKeyBindings.TEST_KEY.consumeClick() && minecraft.player != null) {
                minecraft.setScreen(new ClientStatsGuiOverlay());
            }
        }
    }
}
