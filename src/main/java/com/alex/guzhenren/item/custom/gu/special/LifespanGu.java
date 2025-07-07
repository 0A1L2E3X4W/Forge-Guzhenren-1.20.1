package com.alex.guzhenren.item.custom.gu.special;

import com.alex.guzhenren.capability.PlayerFlags;
import com.alex.guzhenren.capability.providers.PlayerAptitudesProvider;
import com.alex.guzhenren.capability.providers.PlayerFlagsProvider;
import com.alex.guzhenren.item.custom.ModCustomItem;
import com.alex.guzhenren.utils.enums.ModRank;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LifespanGu extends ModCustomItem {

    public LifespanGu(Properties properties, int lifespan) {
        super(properties, ModRank.ONE);
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

        boolean isAwaken = player.getCapability(PlayerFlagsProvider.PLAYER_FLAGS)
                .map(PlayerFlags::isAwaken).orElse(false);

        if (!isAwaken) {
            return InteractionResultHolder.fail(itemStack);
        }

        player.getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE).ifPresent(aptitudes -> {
            aptitudes.addLifespan(lifespan);
        });

        itemStack.shrink(1);
        player.getCooldowns().addCooldown(this, 3);
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack itemStack,
            @Nullable Level level,
            @NotNull List<Component> tooltip,
            @NotNull TooltipFlag isAdvanced) {
        super.appendHoverText(itemStack, level, tooltip, isAdvanced);
    }
}
