package net.alex.guzhenren.datagen.providers.block;

import net.alex.guzhenren.GuzhenrenMod;
import net.alex.guzhenren.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output,
                                 ExistingFileHelper existingFileHelper) {
        super(output, GuzhenrenMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.PRIMEVAL_STONE_BLOCK);
        blockWithItem(ModBlocks.PRIMEVAL_STONE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_PRIMEVAL_STONE_ORE);
        blockWithItem(ModBlocks.NETHER_PRIMEVAL_STONE_ORE);
        blockWithItem(ModBlocks.END_STONE_PRIMEVAL_STONE_ORE);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
