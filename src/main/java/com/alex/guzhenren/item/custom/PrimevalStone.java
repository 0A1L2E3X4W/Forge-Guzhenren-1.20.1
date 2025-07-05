package com.alex.guzhenren.item.custom;

import com.alex.guzhenren.capability.providers.PlayerEssenceProvider;
import com.alex.guzhenren.networking.packet.EssenceSyncS2CPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PrimevalStone extends Item {

    public PrimevalStone(Properties properties, int essence) {
        super(properties);
        this.essenceAmount = essence;
    }

    private final int essenceAmount;

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(
            @NotNull Level level,
            @NotNull Player player,
            @NotNull InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);

        if (level.isClientSide()) {
            return InteractionResultHolder.pass(itemStack);
        }

        boolean isEssenceAdded = player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).map(essence ->  {
            float currentEssence = essence.getEssence();
            int maxEssence = essence.getMaxEssence();

            if (currentEssence < maxEssence) {
                essence.addEssence(essenceAmount);
                EssenceSyncS2CPacket.send((ServerPlayer) player, essence);
                return true;
            }

            return false;
        }).orElse(false);

        if (isEssenceAdded) {
            if (!player.getAbilities().instabuild) { itemStack.shrink(1); }
            return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
        }

        return InteractionResultHolder.fail(itemStack);
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack itemStack,
            @Nullable Level level,
            @NotNull List<Component> tooltips,
            @NotNull TooltipFlag isAdvanced) {

        tooltips.add(Component.translatable("tooltip.guzhenren.primeval_stone")
                .withStyle(style -> style.withColor(0x87CEFA)));

        super.appendHoverText(itemStack, level, tooltips, isAdvanced);
    }
}
