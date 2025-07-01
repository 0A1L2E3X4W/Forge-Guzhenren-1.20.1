package com.alex.guzhenren.networking.packet;

import com.alex.guzhenren.capability.PlayerEssence;
import com.alex.guzhenren.client.data.ClientEssenceData;
import com.alex.guzhenren.networking.ModMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EssenceSyncS2CPacket {

    private final int maxEssence;
    private final float essence;

    public EssenceSyncS2CPacket(float ess, int maxEss) {
        this.maxEssence = maxEss;
        this.essence = ess;
    }

    public EssenceSyncS2CPacket(FriendlyByteBuf buf) {
        this.maxEssence = buf.readInt();
        this.essence = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.maxEssence);
        buf.writeFloat(this.essence);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientEssenceData.setEssence(this.essence);
            ClientEssenceData.setMaxEssence(this.maxEssence);
        });
    }

    public static void send(ServerPlayer serverPlayer, PlayerEssence essence) {
        ModMessage.sendToPlayer(new EssenceSyncS2CPacket(
                essence.getEssence(), essence.getMaxEssence()),
                serverPlayer);
    }
}
