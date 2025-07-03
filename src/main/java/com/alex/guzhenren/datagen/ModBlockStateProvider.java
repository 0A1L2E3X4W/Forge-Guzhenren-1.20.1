package com.alex.guzhenren.datagen;

import com.alex.guzhenren.Guzhenren;
import com.alex.guzhenren.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Guzhenren.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.PRIMEVAL_STONE_BLOCK);
        blockWithItem(ModBlocks.PRIMEVAL_STONE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_PRIMEVAL_STONE_ORE);
        blockWithItem(ModBlocks.NETHER_PRIMEVAL_STONE_ORE);
        blockWithItem(ModBlocks.END_STONE_PRIMEVAL_STONE_ORE);

        simpleBlock(ModBlocks.MOON_ORCHID.get(), models().cross(blockTexture(ModBlocks.MOON_ORCHID.get()).getPath(),
                blockTexture(ModBlocks.MOON_ORCHID.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlocks.POTTED_MOON_ORCHID.get(),
                models().withExistingParent("potted_moon_orchid", mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBlocks.MOON_ORCHID.get())).renderType("cutout"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
