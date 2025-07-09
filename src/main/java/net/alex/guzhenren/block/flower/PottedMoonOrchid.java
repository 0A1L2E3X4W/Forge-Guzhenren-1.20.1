package net.alex.guzhenren.block.flower;

import net.alex.guzhenren.block.ModBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class PottedMoonOrchid extends FlowerPotBlock {

    public PottedMoonOrchid() {
        super(ModBlocks.MOON_ORCHID.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_BLUE_ORCHID).noOcclusion());
    }
}
