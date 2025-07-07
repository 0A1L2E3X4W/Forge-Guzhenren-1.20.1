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
    private static final int BACKGROUND_COLOR = 0xDD000000;

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        guiGraphics.fillGradient(0, 0, this.width, this.height, BACKGROUND_COLOR, BACKGROUND_COLOR);

        Minecraft minecraft = Minecraft.getInstance();
        Component component;
        String text;

        float playerEssence = ClientEssenceData.getEssence();
        int playerMaxEssence = ClientEssenceData.getMaxEssence();
        text = String.format("%.0f/%d", playerEssence, playerMaxEssence);
        component = Component.translatable("guzhenren.text.essence").append(": " + text);
        guiGraphics.drawString(minecraft.font, component, 20, 20, TEXT_COLOR, true);

        ModRank playerRank = ClientAptitudesData.getRank();
        ModStage playerStage = ClientAptitudesData.getStage();

        component = Component.translatable("guzhenren.text.rank")
                .append(": ").append(Component.translatable(playerRank.getNameKey())
                        .append(" ").append(Component.translatable(playerStage.getNameKey())));
        guiGraphics.drawString(minecraft.font, component, 20, 35, TEXT_COLOR, true);

        float playerLifespan = ClientAptitudesData.getLifespan();
        text = String.format("%.1f", playerLifespan);
        component = Component.translatable("guzhenren.text.lifespan").append(": " + text);
        guiGraphics.drawString(minecraft.font, component, 20, 50, TEXT_COLOR, true);

        float playerThoughts = ClientAptitudesData.getThoughts();
        text = String.format("%.1f", playerThoughts);
        component = Component.translatable("guzhenren.text.thoughts").append(": " + text);
        guiGraphics.drawString(minecraft.font, component, 20, 65, TEXT_COLOR, true);

        ModTalent playerTalent = ClientAptitudesData.getTalent();
        component = Component.translatable("guzhenren.text.talent")
                .append(": ").append(Component.translatable(playerTalent.getNameKey()));
        guiGraphics.drawString(minecraft.font, component, 20, 80, TEXT_COLOR, true);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
