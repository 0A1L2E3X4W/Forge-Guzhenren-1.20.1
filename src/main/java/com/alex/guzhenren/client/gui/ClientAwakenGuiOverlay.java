package com.alex.guzhenren.client.gui;

import com.alex.guzhenren.client.data.ClientAptitudesData;
import com.alex.guzhenren.client.data.ClientEssenceData;
import com.alex.guzhenren.networking.c2s_packet.ConfirmAwakenC2SPacket;
import com.alex.guzhenren.networking.c2s_packet.ResetAwakenC2SPacket;
import com.alex.guzhenren.utils.enums.ModExtremePhysique;
import com.alex.guzhenren.utils.enums.ModTalent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class ClientAwakenGuiOverlay extends Screen {

    public ClientAwakenGuiOverlay() {
        super(Component.empty());
    }
    // 颜色常量
    private static final int TEXT_COLOR = 0xFFE6D4A6;  // 金色文字
    private static final int VALUE_COLOR = 0xFFD4AF37;  // 更亮的金色值
    private static final int BACKGROUND_COLOR = 0xCC000000; // 70%透明度的黑色背景

    // 尺寸常量
    private static final int PANEL_WIDTH = 250;
    private static final int PANEL_HEIGHT = 150;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_SPACING = 10;

    private int panelX, panelY;

    @Override
    protected void init() {
        super.init();

        panelX = (this.width - PANEL_WIDTH) / 2;
        panelY = (this.height - PANEL_HEIGHT) / 2;

        int buttonY = panelY + PANEL_HEIGHT - BUTTON_HEIGHT - 15;
        int buttonStartX = panelX + (PANEL_WIDTH - (BUTTON_WIDTH * 2 + BUTTON_SPACING)) / 2;

        Button confirmBtn = Button.builder(Component.translatable("guzhenren.text.button.confirm"), button -> {
                    ConfirmAwakenC2SPacket.send();
                    onClose();
                }).pos(buttonStartX, buttonY).size(BUTTON_WIDTH, BUTTON_HEIGHT).build();

        Button resetBtn = Button.builder(Component.translatable("guzhenren.text.button.reset"), button -> {
                    ResetAwakenC2SPacket.send();
                    onClose();
                }).pos(buttonStartX + BUTTON_WIDTH + BUTTON_SPACING, buttonY).size(BUTTON_WIDTH, BUTTON_HEIGHT).build();

        addRenderableWidget(confirmBtn);
        addRenderableWidget(resetBtn);
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // 绘制半透明黑色背景（覆盖整个屏幕）
        guiGraphics.fill(0, 0, this.width, this.height, BACKGROUND_COLOR);

        // 绘制主面板（圆角矩形）
        drawPanel(guiGraphics);

        // 绘制属性信息
        drawAttributes(guiGraphics);

        // 渲染按钮和其他组件
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    private void drawPanel(GuiGraphics guiGraphics) {
        // 绘制面板背景（圆角矩形）
        guiGraphics.fill(panelX, panelY, panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT, 0xDD000000);

        // 绘制金色边框（圆角）
        int borderColor = 0xFF8B4513;
        guiGraphics.fill(panelX - 1, panelY, panelX + PANEL_WIDTH + 1, panelY + 1, borderColor); // 上边框
        guiGraphics.fill(panelX - 1, panelY + PANEL_HEIGHT - 1, panelX + PANEL_WIDTH + 1, panelY + PANEL_HEIGHT, borderColor); // 下边框
        guiGraphics.fill(panelX, panelY - 1, panelX + 1, panelY + PANEL_HEIGHT + 1, borderColor); // 左边框
        guiGraphics.fill(panelX + PANEL_WIDTH - 1, panelY - 1, panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT + 1, borderColor); // 右边框

        // 绘制四角装饰
        int cornerSize = 6;
        guiGraphics.fill(panelX, panelY, panelX + cornerSize, panelY + 1, borderColor);
        guiGraphics.fill(panelX, panelY, panelX + 1, panelY + cornerSize, borderColor);

        guiGraphics.fill(panelX + PANEL_WIDTH - cornerSize, panelY, panelX + PANEL_WIDTH, panelY + 1, borderColor);
        guiGraphics.fill(panelX + PANEL_WIDTH - 1, panelY, panelX + PANEL_WIDTH, panelY + cornerSize, borderColor);

        guiGraphics.fill(panelX, panelY + PANEL_HEIGHT - 1, panelX + cornerSize, panelY + PANEL_HEIGHT, borderColor);
        guiGraphics.fill(panelX, panelY + PANEL_HEIGHT - cornerSize, panelX + 1, panelY + PANEL_HEIGHT, borderColor);

        guiGraphics.fill(panelX + PANEL_WIDTH - cornerSize, panelY + PANEL_HEIGHT - 1, panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT, borderColor);
        guiGraphics.fill(panelX + PANEL_WIDTH - 1, panelY + PANEL_HEIGHT - cornerSize, panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT, borderColor);
    }

    private void drawAttributes(GuiGraphics guiGraphics) {
        Minecraft minecraft = Minecraft.getInstance();
        int centerX = panelX + PANEL_WIDTH / 2;
        int startY = panelY + PANEL_HEIGHT / 3;
        int lineHeight = 20;

        // 精华值
        int playerMaxEssence = ClientEssenceData.getMaxEssence();
        drawCenteredAttribute(guiGraphics,
                Component.translatable("guzhenren.text.essence"),
                Component.literal(String.valueOf(playerMaxEssence)),
                startY);

        // 天赋
        ModTalent playerTalent = ClientAptitudesData.getTalent();
        drawCenteredAttribute(guiGraphics,
                Component.translatable("guzhenren.text.talent"),
                Component.translatable(playerTalent.getNameKey()),
                startY + lineHeight);

        // 体质
        ModExtremePhysique playerExtremePhysique = ClientAptitudesData.getExtremePhysique();
        drawCenteredAttribute(guiGraphics,
                Component.translatable("guzhenren.text.physique"),
                Component.translatable(playerExtremePhysique.getNameKey()),
                startY + lineHeight * 2);
    }

    private void drawCenteredAttribute(GuiGraphics guiGraphics, Component label, Component value, int yPos) {
        Minecraft minecraft = Minecraft.getInstance();

        // 创建完整文本：标签 + 值
        Component fullText = label.copy().append(": ").append(value);

        // 计算居中位置
        int textWidth = minecraft.font.width(fullText);
        int xPos = panelX + (PANEL_WIDTH - textWidth) / 2;

        // 绘制标签部分
        int labelWidth = minecraft.font.width(label);
        guiGraphics.drawString(minecraft.font, label, xPos, yPos, TEXT_COLOR, false);

        // 绘制值部分（使用更亮的颜色）
        guiGraphics.drawString(minecraft.font, value, xPos + labelWidth + 2, yPos, VALUE_COLOR, false);

        // 绘制装饰性下划线
        int underlineY = yPos + minecraft.font.lineHeight + 2;
        guiGraphics.hLine(xPos, xPos + textWidth - 1, underlineY, 0x55E6D4A6);
    }

    // 按ESC关闭界面
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) { // ESC键
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    // 点击背景关闭界面
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!super.mouseClicked(mouseX, mouseY, button)) {
            // 如果点击在面板外，关闭界面
            if (mouseX < panelX || mouseX > panelX + PANEL_WIDTH ||
                    mouseY < panelY || mouseY > panelY + PANEL_HEIGHT) {
                onClose();
                return true;
            }
        }
        return true;
    }
}
