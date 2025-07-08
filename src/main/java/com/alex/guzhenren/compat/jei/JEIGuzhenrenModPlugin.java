package com.alex.guzhenren.compat.jei;

import com.alex.guzhenren.Guzhenren;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class JEIGuzhenrenModPlugin implements IModPlugin {

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Guzhenren.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        // Register recipes here
        // Example: registration.addRecipes(recipeList, categoryUid);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        // Register recipe categories here
        // Example: registration.addRecipeCategories(new GuzhenrenRecipeCategory());
    }
}
