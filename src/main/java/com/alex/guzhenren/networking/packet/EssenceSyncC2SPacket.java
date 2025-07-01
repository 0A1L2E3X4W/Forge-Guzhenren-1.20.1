package com.alex.guzhenren.networking.packet;

import com.alex.guzhenren.capability.PlayerEssenceProvider;
import com.alex.guzhenren.networking.ModMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EssenceSyncC2SPacket {

    public EssenceSyncC2SPacket() {

    }

    public EssenceSyncC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

            ServerPlayer serverPlayer = context.getSender();
            if (serverPlayer == null) return;
            ServerLevel serverLevel = serverPlayer.serverLevel();

            serverPlayer.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                essence.subEssence(10);
                ModMessage.sendToPlayer(new EssenceSyncS2CPacket(essence.getEssence(), essence.getMaxEssence()), serverPlayer);
            });
        });
    }
}
