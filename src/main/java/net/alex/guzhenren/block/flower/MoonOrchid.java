package net.alex.guzhenren.block.flower;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;

public class MoonOrchid extends FlowerBlock {

    public MoonOrchid() {
        super(MobEffects.LUCK, 8,
                Block.Properties.copy(Blocks.BLUE_ORCHID).noOcclusion().noCollission());
    }
}
