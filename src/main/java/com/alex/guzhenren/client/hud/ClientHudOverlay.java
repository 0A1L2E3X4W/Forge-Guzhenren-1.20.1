package com.alex.guzhenren.client.hud;

import com.alex.guzhenren.client.data.ClientAptitudesData;
import com.alex.guzhenren.client.data.ClientEssenceData;
import com.alex.guzhenren.utils.enums.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ClientHudOverlay {

    private static final int BAR_WIDTH = 122;
    private static final int BAR_HEIGHT = 12;
    private static final int BAR_PADDING = 1;

    private static final int BACKGROUND_COLOR = 0x80000000;
    private static final int FILL_COLOR = 0xFF13DBEF;
    private static final int TEXT_COLOR = 0xFFFFFF;

    public static final IGuiOverlay HUD_ESSENCE =
            (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {

                int x = 12; int y = 12;

                float playerEssence = ClientEssenceData.getEssence();
                int playerMaxEssence = ClientEssenceData.getMaxEssence();

                float fillRatio = Math.min(playerEssence / (float) playerMaxEssence, 1.0f);
                int fillWidth = (int) (BAR_WIDTH * fillRatio);

                drawRoundedRect(guiGraphics, x, y, BAR_WIDTH, BAR_HEIGHT, BACKGROUND_COLOR);

                if (fillWidth > 0) {
                    drawRoundedRect(guiGraphics,
                            x + BAR_PADDING, y + BAR_PADDING,
                            fillWidth - 2 * BAR_PADDING, BAR_HEIGHT - 2 * BAR_PADDING,
                            FILL_COLOR);
                }

                Minecraft mc = Minecraft.getInstance();
                String text = String.format("%.0f/%d", playerEssence, playerMaxEssence);
                int textX = x + (BAR_WIDTH - mc.font.width(text)) / 2;
                int textY = y + (BAR_HEIGHT - 8) / 2;

                guiGraphics.drawString(mc.font, text, textX, textY, TEXT_COLOR, true);
    };

    public static final IGuiOverlay HUD_APTITUDE =
            (gui, guiGraphics, partialTick, screenWidth, screenHeight) ->  {

                int x = 12 + 4; int y = 12 + 16;

                float playerLifespan = ClientAptitudesData.getLifespan();
                ModRank playerRank = ClientAptitudesData.getRank();
                ModStage playerStage = ClientAptitudesData.getStage();

                Minecraft mc = Minecraft.getInstance();
                String text = String.format("%.1f", playerLifespan);

                Component lifespanText = Component.translatable("guzhenren.text.lifespan").append(": " + text);
                Component rankText = Component.translatable("guzhenren.text.rank")
                                .append(": ")
                                .append(Component.translatable(playerRank.getNameKey())
                                .append(" ")
                                .append(Component.translatable(playerStage.getNameKey())));

                guiGraphics.drawString(mc.font, lifespanText, x, y, TEXT_COLOR, true);
                guiGraphics.drawString(mc.font, rankText, x, y + 12, TEXT_COLOR, true);
    };

    // 绘制圆角矩形（简化版）
    private static void drawRoundedRect(GuiGraphics guiGraphics, int x, int y, int width, int height, int color) {
        // 绘制主体矩形
        guiGraphics.fill(x + 1, y, x + width - 1, y + height, color);

        // 绘制四个角（小圆角效果）
        guiGraphics.fill(x, y + 1, x + 1, y + height - 1, color); // 左边缘
        guiGraphics.fill(x + width - 1, y + 1, x + width, y + height - 1, color); // 右边缘
        guiGraphics.fill(x + 1, y, x + 2, y + 1, color); // 左上角
        guiGraphics.fill(x + width - 2, y, x + width - 1, y + 1, color); // 右上角
        guiGraphics.fill(x + 1, y + height - 1, x + 2, y + height, color); // 左下角
        guiGraphics.fill(x + width - 2, y + height - 1, x + width - 1, y + height, color); // 右下角
    }
}
