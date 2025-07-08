package com.alex.guzhenren.utils.capability;

import com.alex.guzhenren.capability.PlayerPathData;
import com.alex.guzhenren.capability.providers.PlayerPathDataProvider;
import com.alex.guzhenren.networking.s2c_packet.PathDataSyncS2CPacket;
import com.alex.guzhenren.utils.enums.ModPath;
import com.alex.guzhenren.utils.enums.ModPathRealm;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;
import java.util.function.Consumer;

public class PlayerPathDataUtils {

    private static Optional<PlayerPathData> getPathData(Player player) {
        return player.getCapability(PlayerPathDataProvider.PLAYER_PATH_DATA)
                .resolve()
                .map(Optional::of)
                .orElseThrow(() -> new IllegalStateException("PlayerPathData capability not resolved"));
    }

    private static void modifyAndSync(Player player, Consumer<PlayerPathData> modifier) {
        if (player instanceof ServerPlayer serverPlayer) {
            getPathData(player).ifPresent(pathData -> {
                modifier.accept(pathData);
                PathDataSyncS2CPacket.send(serverPlayer, pathData);
            });
        }
    }

    // ATTAINMENT
    public static int getPathAttainment(Player player, ModPath path) {
        return getPathData(player)
                .map(pathData -> pathData.getPathAttainment(path))
                .orElse(0);
    }

    public static void setPathAttainment(Player player, ModPath path, int i) {
        modifyAndSync(player, pathData -> pathData.setPathAttainment(path, i));
    }

    public static void addPathAttainment(Player player, ModPath path, int i) {
        modifyAndSync(player, pathData -> pathData.addPathAttainment(path, i));
    }

    public static void subPathAttainment(Player player, ModPath path, int i) {
        modifyAndSync(player, pathData -> pathData.subPathAttainment(path, i));
    }

    // REALMS
    public static ModPathRealm getPathRealm(Player player, ModPath path) {
        return getPathData(player)
                .map(pathData -> pathData.getPathRealm(path))
                .orElse(ModPathRealm.ORDINARY);
    }

    public static void setPathRealm(Player player, ModPath path, ModPathRealm realm) {
        modifyAndSync(player, pathData -> pathData.setPathRealm(path, realm));
    }
}
