package com.alex.guzhenren.networking;

import com.alex.guzhenren.Guzhenren;
import com.alex.guzhenren.networking.packet.SyncAptitudeC2S;
import com.alex.guzhenren.networking.packet.SyncAptitudeS2C;
import com.alex.guzhenren.networking.packet.SyncEssenceC2S;
import com.alex.guzhenren.networking.packet.SyncEssenceS2C;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessage {

    public static SimpleChannel INSTANCE;
    private static final String PROTOCOL_VERSION = "1.0";
    private static final ResourceLocation CHANNEL_NAME = ResourceLocation.fromNamespaceAndPath(
            Guzhenren.MOD_ID, "network");

    // packetId
    private static int packetId = 0;
    public static int getPacketId() { return packetId++; }

    public static void register() {
        SimpleChannel network = NetworkRegistry.ChannelBuilder
                .named(CHANNEL_NAME).networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .simpleChannel();
        INSTANCE = network;

        // CLIENT TO SERVER
        network.messageBuilder(SyncEssenceC2S.class, getPacketId(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SyncEssenceC2S::new).encoder(SyncEssenceC2S::toBytes)
                .consumerMainThread(SyncEssenceC2S::handle).add();

        network.messageBuilder(SyncAptitudeC2S.class, getPacketId(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SyncAptitudeC2S::new).encoder(SyncAptitudeC2S::toBytes)
                .consumerMainThread(SyncAptitudeC2S::handle).add();

        // SERVER TO CLIENT
        network.messageBuilder(SyncEssenceS2C.class, getPacketId(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncEssenceS2C::new).encoder(SyncEssenceS2C::toBytes)
                .consumerMainThread(SyncEssenceS2C::handle).add();

        network.messageBuilder(SyncAptitudeS2C.class, getPacketId(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncAptitudeS2C::new).encoder(SyncAptitudeS2C::toBytes)
                .consumerMainThread(SyncAptitudeS2C::handle).add();
    }

    public static <MEG> void sendToServer(MEG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MEG> void sendToPlayer(MEG message, ServerPlayer serverPlayer) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), message);
    }

    public static void sendToAllClients(Object message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
