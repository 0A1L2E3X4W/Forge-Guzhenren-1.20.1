package net.alex.guzhenren.networking.s2c_packet;

import net.alex.guzhenren.capability.PlayerEssence;
import net.alex.guzhenren.client.data.ClientEssenceData;
import net.alex.guzhenren.networking.ModMessage;
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
        maxEssence = buf.readInt();
        essence = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(maxEssence);
        buf.writeFloat(essence);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientEssenceData.setEssence(essence);
            ClientEssenceData.setMaxEssence(maxEssence);
        });
    }

    public static void send(ServerPlayer serverPlayer, PlayerEssence essence) {
        ModMessage.sendToPlayer(new EssenceSyncS2CPacket(
                essence.getCurrentEssence(), essence.getMaxEssence()),
                serverPlayer);
    }
}
