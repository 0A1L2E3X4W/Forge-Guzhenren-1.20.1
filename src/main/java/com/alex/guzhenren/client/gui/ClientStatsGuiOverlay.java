package com.alex.guzhenren.client.gui;

import com.alex.guzhenren.client.data.ClientAptitudesData;
import com.alex.guzhenren.client.data.ClientEssenceData;
import com.alex.guzhenren.client.data.ClientPathData;
import com.alex.guzhenren.utils.enums.*;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
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

    private int currentPage = 0;
    private Button mainPageBtn, attainmentsPageBtn, realmsPageBtn;
    private static final int MAIN_PAGE = 0;
    private static final int ATTAINMENTS_PAGE = 1;
    private static final int REALMS_PAGE = 2;

    @Override
    protected void init() {
        super.init();

        int buttonWidth = 80;
        int buttonHeight = 20;
        int marginRight = 10;
        int spacing = 5;

        int xPos = this.width - buttonWidth - marginRight;
        int yStart = 10;

        mainPageBtn = Button.builder(Component.translatable("guzhenren.text.button.main_page"),
                button -> setPage(MAIN_PAGE))
                .pos(xPos, yStart).size(buttonWidth, buttonHeight).build();

        attainmentsPageBtn = Button.builder(Component.translatable("guzhenren.text.button.attainments_page"),
                button -> setPage(ATTAINMENTS_PAGE))
                .pos(xPos, yStart + (buttonHeight + spacing)).size(buttonWidth, buttonHeight).build();

        realmsPageBtn = Button.builder(Component.translatable("guzhenren.text.button.realms_page"),
                button -> setPage(REALMS_PAGE))
                .pos(xPos, yStart + 2 * (buttonHeight + spacing)).size(buttonWidth, buttonHeight).build();

        addRenderableWidget(mainPageBtn);
        addRenderableWidget(attainmentsPageBtn);
        addRenderableWidget(realmsPageBtn);

        updateButtonStates();
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        guiGraphics.fillGradient(0, 0, this.width, this.height, BACKGROUND_COLOR, BACKGROUND_COLOR);

        switch (currentPage) {
            case MAIN_PAGE -> mainPage(guiGraphics);
            case ATTAINMENTS_PAGE -> attainmentsPage(guiGraphics);
            case REALMS_PAGE -> realmsPage(guiGraphics);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void mainPage(GuiGraphics guiGraphics) {
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

        ModTalent playerTalent = ClientAptitudesData.getTalent();
        component = Component.translatable("guzhenren.text.talent")
                .append(": ").append(Component.translatable(playerTalent.getNameKey()));
        guiGraphics.drawString(minecraft.font, component, 20, 50, TEXT_COLOR, true);

        float playerLifespan = ClientAptitudesData.getLifespan();
        text = String.format("%.1f", playerLifespan);
        component = Component.translatable("guzhenren.text.lifespan").append(": " + text);
        guiGraphics.drawString(minecraft.font, component, 20, 65, TEXT_COLOR, true);

        float playerThoughts = ClientAptitudesData.getThoughts();
        text = String.format("%.1f", playerThoughts);
        component = Component.translatable("guzhenren.text.thoughts").append(": " + text);
        guiGraphics.drawString(minecraft.font, component, 20, 80, TEXT_COLOR, true);
    }

    private void attainmentsPage(GuiGraphics guiGraphics) {
        int baseX = 20;
        int baseY = 20;
        int lineHeight = 16;
        int columnWidth = 80;
        Minecraft minecraft = Minecraft.getInstance();

        int i = 0;
        for (ModPath path : ModPath.values()) {
            int attainment = ClientPathData.getPathAttainment(path);
            int column = i / 11;
            int row = i % 11;

            int x = baseX + column * columnWidth;
            int y = baseY + row * lineHeight;

            Component component = Component.translatable(path.getNameKey()).append(": " + attainment);
            guiGraphics.drawString(minecraft.font, component, x, y, TEXT_COLOR, true);

            i++;
        }
    }

    private void realmsPage(GuiGraphics guiGraphics) {
        int baseX = 20;
        int baseY = 20;
        int lineHeight = 16;
        int columnWidth = 80;
        Minecraft minecraft = Minecraft.getInstance();

        int i = 0;
        for (ModPath path : ModPath.values()) {
            ModPathRealm realm = ClientPathData.getPathRealm(path);
            int column = i / 11;
            int row = i % 11;

            int x = baseX + column * columnWidth;
            int y = baseY + row * lineHeight;

            Component component = Component.translatable(path.getNameKey())
                    .append(": ").append(Component.translatable(realm.getNameKey()));
            guiGraphics.drawString(minecraft.font, component, x, y, TEXT_COLOR, true);

            i++;
        }
    }

    private void setPage(int page) {
        currentPage = page;
        updateButtonStates();
    }

    private void updateButtonStates() {
        mainPageBtn.active = (currentPage != MAIN_PAGE);
        attainmentsPageBtn.active = (currentPage != ATTAINMENTS_PAGE);
        realmsPageBtn.active = (currentPage != REALMS_PAGE);
    }
}
