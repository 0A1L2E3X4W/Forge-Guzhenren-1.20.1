package com.alex.guzhenren.item.custom.gu.lifespan;

import com.alex.guzhenren.capability.PlayerFlags;
import com.alex.guzhenren.capability.providers.PlayerAptitudesProvider;
import com.alex.guzhenren.capability.providers.PlayerFlagsProvider;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LifespanGu extends Item {

    public LifespanGu(Properties properties, int lifespan) {
        super(properties);
        this.lifespan = lifespan;
    }

    protected int lifespan;

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(
            @NotNull Level level,
            @NotNull Player player,
            @NotNull InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);

        if (level.isClientSide()) {
            return InteractionResultHolder.pass(itemStack);
        }

        boolean isAwaken = player.getCapability(PlayerFlagsProvider.PLAYER_FLAGS).map(PlayerFlags::isAwaken).orElse(false);

        if (!isAwaken) {
            return InteractionResultHolder.fail(itemStack);
        }

        player.getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE).ifPresent(aptitudes -> {
            aptitudes.addLifespan(lifespan);
        });

        itemStack.shrink(1);
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}
