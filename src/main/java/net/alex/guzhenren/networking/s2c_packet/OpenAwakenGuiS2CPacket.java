package net.alex.guzhenren.networking.s2c_packet;

import net.alex.guzhenren.client.gui.ClientAwakenGuiOverlay;
import net.alex.guzhenren.networking.ModMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenAwakenGuiS2CPacket {

    public OpenAwakenGuiS2CPacket() {

    }

    public OpenAwakenGuiS2CPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Minecraft.getInstance().setScreen(new ClientAwakenGuiOverlay());
        });
    }

    public static void send(ServerPlayer serverPlayer) {
        ModMessage.sendToPlayer(new OpenAwakenGuiS2CPacket(), serverPlayer);
    }
}
