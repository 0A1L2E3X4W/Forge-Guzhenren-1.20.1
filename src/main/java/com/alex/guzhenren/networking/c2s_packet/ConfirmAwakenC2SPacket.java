package com.alex.guzhenren.networking.c2s_packet;

import com.alex.guzhenren.networking.ModMessage;
import com.alex.guzhenren.utils.capability.PlayerFlagsUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ConfirmAwakenC2SPacket {

    public ConfirmAwakenC2SPacket() {

    }

    public ConfirmAwakenC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            PlayerFlagsUtils.setApertureAwaken(context.getSender(), true);
        });
    }

    public static void send() {
        ModMessage.sendToServer(new ConfirmAwakenC2SPacket());
    }
}
