package net.alex.guzhenren.item.custom.custom_material.gu_materials;

import net.alex.guzhenren.item.custom.custom_material.GuMaterial;
import net.alex.guzhenren.utils.capability.PlayerEssenceUtils;
import net.alex.guzhenren.utils.enums.ModPath;
import net.alex.guzhenren.utils.enums.ModRank;
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

public class PrimevalStone extends GuMaterial {

    public PrimevalStone(Properties properties, int essence) {
        super(properties, ModRank.MORTAL, ModPath.HEAVEN);
        this.essenceAmount = essence;
    }

    private final int essenceAmount;

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(
            @NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);

        if (level.isClientSide()) {
            return InteractionResultHolder.pass(itemStack);
        }

        float currentEssence = PlayerEssenceUtils.getCurrentEssence(player);
        int maxEssence = PlayerEssenceUtils.getMaxEssence(player);

        if ((maxEssence - currentEssence) < essenceAmount) {
            PlayerEssenceUtils.addCurrentEssence(player, essenceAmount);
        } else {
            PlayerEssenceUtils.setCurrentEssence(player, maxEssence);
        }

        itemStack.shrink(1);
        player.getCooldowns().addCooldown(this, 3);
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag isAdvanced) {
        super.appendHoverText(itemStack, level, tooltip, isAdvanced);

        tooltip.add(Component.translatable("tooltip.guzhenren.primeval_stone")
                .withStyle(style -> style.withColor(0x87CEFA)));
    }
}
