package com.alex.guzhenren.datagen;

import com.alex.guzhenren.Guzhenren;
import com.alex.guzhenren.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Guzhenren.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.basicItem(ModItems.APERTURE.get());
        this.basicItem(ModItems.HUMAN_HEART.get());
        this.basicItem(ModItems.PRIMEVAL_STONE.get());
    }
}
