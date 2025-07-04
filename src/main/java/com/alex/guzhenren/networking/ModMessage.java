package com.alex.guzhenren.networking;

import com.alex.guzhenren.Guzhenren;
import com.alex.guzhenren.networking.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessage {

    // INITIALIZE INSTANCE
    public static SimpleChannel INSTANCE;
    private static final String PROTOCOL_VERSION = "1.0";
    private static final ResourceLocation CHANNEL_NAME =
            ResourceLocation.fromNamespaceAndPath(Guzhenren.MOD_ID, "network");

    // packetId
    private static int packetId = 0;
    public static int getPacketId() { return packetId++; }

    public static void register() {
        SimpleChannel network = NetworkRegistry.ChannelBuilder
                .named(CHANNEL_NAME).networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .simpleChannel();
        INSTANCE = network;

        // SERVER TO CLIENT
        network.messageBuilder(EssenceSyncS2CPacket.class, getPacketId(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(EssenceSyncS2CPacket::new).encoder(EssenceSyncS2CPacket::toBytes)
                .consumerMainThread(EssenceSyncS2CPacket::handle).add();

        network.messageBuilder(AptitudesSyncS2CPacket.class, getPacketId(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(AptitudesSyncS2CPacket::new).encoder(AptitudesSyncS2CPacket::toBytes)
                .consumerMainThread(AptitudesSyncS2CPacket::handle).add();

        network.messageBuilder(FlagsSyncS2CPacket.class, getPacketId(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FlagsSyncS2CPacket::new).encoder(FlagsSyncS2CPacket::toBytes)
                .consumerMainThread(FlagsSyncS2CPacket::handle).add();
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
