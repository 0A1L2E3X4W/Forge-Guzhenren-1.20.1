package com.alex.guzhenren.networking.s2c_packet;

import com.alex.guzhenren.capability.PlayerFlags;
import com.alex.guzhenren.client.data.ClientFlagsData;
import com.alex.guzhenren.networking.ModMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FlagsSyncS2CPacket {

    private final boolean isAwaken;

    public FlagsSyncS2CPacket(boolean isAwaken) {
        this.isAwaken = isAwaken;
    }

    public FlagsSyncS2CPacket(FriendlyByteBuf buf) {
        isAwaken = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(isAwaken);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientFlagsData.setIsAwaken(isAwaken);
        });
    }

    public static void send(ServerPlayer serverPlayer, PlayerFlags flags) {
        ModMessage.sendToPlayer(new FlagsSyncS2CPacket(
                flags.isAwaken()), serverPlayer);
    }
}
