package com.alex.guzhenren.item.custom.custom_gu.heaven;

import com.alex.guzhenren.item.custom.CustomItem;
import com.alex.guzhenren.utils.capability.PlayerAptitudesUtils;
import com.alex.guzhenren.utils.capability.PlayerFlagsUtils;
import com.alex.guzhenren.utils.enums.ModPath;
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

public class LifespanGu extends CustomItem {

    public LifespanGu(Properties properties, int lifespan) {
        super(properties);
        this.lifespan = lifespan;
    }

    protected int lifespan;

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(
            @NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {

        ItemStack itemStack = player.getItemInHand(usedHand);

        if (level.isClientSide()) {
            return InteractionResultHolder.pass(itemStack);
        }

        if (!PlayerFlagsUtils.isApertureAwaken(player)) {
            return InteractionResultHolder.fail(itemStack);
        }

        PlayerAptitudesUtils.addLifespan(player, lifespan);

        itemStack.shrink(1);
        player.getCooldowns().addCooldown(this, 3);
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag isAdvanced) {

        Component commonStats = Component.translatable(ModRank.ONE.getNameKey()).append(" ")
                .append(Component.translatable(ModPath.HEAVEN.getNameKey()))
                .withStyle(style -> style.withColor(0xAA00AA));

        tooltip.add(commonStats);

        super.appendHoverText(itemStack, level, tooltip, isAdvanced);
    }
}
