package com.alex.guzhenren.networking.packet;

import com.alex.guzhenren.capability.PlayerPathData;
import com.alex.guzhenren.client.data.ClientPathData;
import com.alex.guzhenren.networking.ModMessage;
import com.alex.guzhenren.utils.enums.ModPath;
import com.alex.guzhenren.utils.enums.ModPathRealm;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.EnumMap;
import java.util.function.Supplier;

public class PathDataSyncS2CPacket {

    private EnumMap<ModPath, Integer> pathAttainments = new EnumMap<>(ModPath.class);
    private EnumMap<ModPath, ModPathRealm> pathRealms = new EnumMap<>(ModPath.class);

    public PathDataSyncS2CPacket(
            EnumMap<ModPath,Integer> attainments, EnumMap<ModPath,ModPathRealm> realms) {
        this.pathAttainments = attainments;
        this.pathRealms = realms;
    }

    public PathDataSyncS2CPacket(FriendlyByteBuf buf) {
        for (ModPath path : ModPath.values()) {
            buf.readInt();
        }

        for (ModPath path : ModPath.values()) {
            buf.readEnum(ModPathRealm.class);
        }
    }

    public void toBytes(FriendlyByteBuf buf) {
        for (ModPath path : ModPath.values()) {
            buf.writeInt(pathAttainments.getOrDefault(path, 0));
        }

        for (ModPath path : ModPath.values()) {
            buf.writeEnum(pathRealms.getOrDefault(path, ModPathRealm.ORDINARY));
        }
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientPathData.setPathAttainments(pathAttainments);
            ClientPathData.setPathRealms(pathRealms);
        });
    }

    public static void send(ServerPlayer serverPlayer, PlayerPathData pathData) {
        ModMessage.sendToPlayer(new PathDataSyncS2CPacket(
                pathData.getPathAttainments(),
                pathData.getPathRealms()),
                serverPlayer);
    }
}
