package com.alex.guzhenren.networking.packet;

import com.alex.guzhenren.capability.PlayerEssenceProvider;
import com.alex.guzhenren.networking.ModMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncEssenceC2S {

    public SyncEssenceC2S() {

    }

    public SyncEssenceC2S(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

            ServerPlayer serverPlayer = context.getSender();
            if (serverPlayer == null) return;
            ServerLevel serverLevel = serverPlayer.serverLevel();

            serverPlayer.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                essence.subEssence(10);
                ModMessage.sendToPlayer(new SyncEssenceS2C(essence.getEssence(), essence.getMaxEssence()), serverPlayer);
            });
        });
        return true;
    }
}
