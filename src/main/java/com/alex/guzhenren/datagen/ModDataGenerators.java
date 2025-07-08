package com.alex.guzhenren.datagen;

import com.alex.guzhenren.Guzhenren;
import com.alex.guzhenren.datagen.lang.ModLangEnUsProvider;
import com.alex.guzhenren.datagen.lang.ModLangZhCnProvider;
import com.alex.guzhenren.datagen.providers.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Guzhenren.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // LANG
        generator.addProvider(event.includeServer(), new ModLangEnUsProvider(packOutput));
        generator.addProvider(event.includeServer(), new ModLangZhCnProvider(packOutput));

        // LOOT TABLE
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));

        // RECIPES
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));

        // ITEM & BLOCK MODEL
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));

        // BLOCK TAG
        ModBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new ModBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(),
                new ModItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        // WORLD GEN
        generator.addProvider(event.includeServer(),new ModWorldGenProvider(packOutput, lookupProvider));
    }
}
