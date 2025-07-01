package com.alex.guzhenren.networking.packet;

import com.alex.guzhenren.client.data.ClientEssenceData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncEssenceS2C {

    private final int maxEssence;
    private final float essence;

    public SyncEssenceS2C(float ess, int maxEss) {
        this.maxEssence = maxEss;
        this.essence = ess;
    }

    public SyncEssenceS2C(FriendlyByteBuf buf) {
        this.maxEssence = buf.readInt();
        this.essence = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.maxEssence);
        buf.writeFloat(this.essence);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientEssenceData.setEssence(this.essence);
            ClientEssenceData.setMaxEssence(this.maxEssence);
        });
        return true;
    }
}
