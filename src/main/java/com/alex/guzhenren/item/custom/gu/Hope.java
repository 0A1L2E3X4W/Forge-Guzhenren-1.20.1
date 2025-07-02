package com.alex.guzhenren.item.custom.gu;

import com.alex.guzhenren.capability.PlayerEssenceProvider;
import com.alex.guzhenren.networking.packet.EssenceSyncS2CPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class Hope extends Item {

    public Hope(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(
            @NotNull Level level,
            @NotNull Player player,
            @NotNull InteractionHand usedHand) {

        ItemStack itemStack = player.getItemInHand(usedHand);

        if (level.isClientSide()) {
            return InteractionResultHolder.pass(itemStack);
        }

        boolean isAwaken = player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).map(essence ->  {
            essence.setMaxEssence(100000);
            essence.setEssence(40000);
            EssenceSyncS2CPacket.send((ServerPlayer) player, essence);
            return true;
        }).orElse(false);

        if (isAwaken) {
            if (!player.getAbilities().instabuild) { itemStack.shrink(1); }
            return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
        }

        return InteractionResultHolder.fail(itemStack);
    }
}
