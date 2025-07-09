package com.alex.guzhenren.datagen.providers.item;

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
        basicItem(ModItems.APERTURE.get());
        basicItem(ModItems.HUMAN_HEART.get());
        basicItem(ModItems.PRIMEVAL_STONE.get());

        basicItem(ModItems.HOPE_GU.get());
        basicItem(ModItems.YEAR_LIFESPAN_GU.get());
        basicItem(ModItems.TEN_YEAR_LIFESPAN_GU.get());
        basicItem(ModItems.PRIMEVAL_ELDER_GU_3.get());
        basicItem(ModItems.PRIMEVAL_ELDER_GU_4.get());

        basicItem(ModItems.LIQUOR.get());
    }
}
