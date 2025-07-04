package com.alex.guzhenren.item.custom.gu.lifespan;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class TenYearsLifespanGu extends LifespanGu {

    public TenYearsLifespanGu(Properties properties, int lifespan) {
        super(properties, lifespan);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(
            @NotNull Level level,
            @NotNull Player player,
            @NotNull InteractionHand usedHand) {
        return super.use(level, player, usedHand);
    }
}
