package net.alex.guzhenren.utils;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {

    public static final String KEY_CATEGORY_GUZHENREN = "key.category.guzhenren";
    public static final String KEY_TEST = "key.guzhenren.test";

    public static final KeyMapping TEST_KEY = new KeyMapping(
            KEY_TEST,
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            KEY_CATEGORY_GUZHENREN);
}
