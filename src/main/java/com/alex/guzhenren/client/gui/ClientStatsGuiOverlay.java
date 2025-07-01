package com.alex.guzhenren.client.gui;

import com.alex.guzhenren.client.data.ClientAptitudesData;
import com.alex.guzhenren.client.data.ClientEssenceData;
import com.alex.guzhenren.utils.enums.ModRank;
import com.alex.guzhenren.utils.enums.ModStage;
import com.alex.guzhenren.utils.enums.ModTalent;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ClientStatsGuiOverlay extends Screen {

    public ClientStatsGuiOverlay() {
        super(Component.translatable("guzhenren.text.screen_title"));
    }

    private static final int TEXT_COLOR = 0xFFFFFF;
    private static final int BACKGROUND_COLOR = 0xFF000000;

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        guiGraphics.fillGradient(0, 0, this.width, this.height, BACKGROUND_COLOR, BACKGROUND_COLOR);

        Minecraft mc = Minecraft.getInstance();

        float playerEssence = ClientEssenceData.getEssence();
        int playerMaxEssence = ClientEssenceData.getMaxEssence();
        String text = String.format("%.0f/%d", playerEssence, playerMaxEssence);
        guiGraphics.drawString(mc.font, text, 20, 20, TEXT_COLOR, true);

        Component component;

        float playerLifespan = ClientAptitudesData.getLifespan();
        component = Component.translatable("guzhenren.text.lifespan").append(": " + playerLifespan);
        guiGraphics.drawString(mc.font, component, 20, 32, TEXT_COLOR, true);

        float playerThoughts = ClientAptitudesData.getThoughts();

        int playerLuck = ClientAptitudesData.getLuck();
        int playerSoul = ClientAptitudesData.getSoul();
        int playerMoral = ClientAptitudesData.getMoral();

        ModRank playerRank = ClientAptitudesData.getRank();
        ModStage playerStage = ClientAptitudesData.getStage();
        ModTalent playerTalent = ClientAptitudesData.getTalent();

    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
