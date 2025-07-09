package net.alex.guzhenren.datagen.providers.block;

import net.alex.guzhenren.GuzhenrenMod;
import net.alex.guzhenren.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output,
                                CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, GuzhenrenMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.PRIMEVAL_STONE_BLOCK.get(),
                ModBlocks.PRIMEVAL_STONE_ORE.get(),
                ModBlocks.DEEPSLATE_PRIMEVAL_STONE_ORE.get(),
                ModBlocks.NETHER_PRIMEVAL_STONE_ORE.get(),
                ModBlocks.END_STONE_PRIMEVAL_STONE_ORE.get());

        tag(BlockTags.NEEDS_STONE_TOOL).add(
                ModBlocks.PRIMEVAL_STONE_BLOCK.get(),
                ModBlocks.PRIMEVAL_STONE_ORE.get(),
                ModBlocks.DEEPSLATE_PRIMEVAL_STONE_ORE.get(),
                ModBlocks.NETHER_PRIMEVAL_STONE_ORE.get(),
                ModBlocks.END_STONE_PRIMEVAL_STONE_ORE.get());
    }
}
