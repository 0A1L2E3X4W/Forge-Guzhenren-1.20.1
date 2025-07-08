package com.alex.guzhenren.utils.capability;

import com.alex.guzhenren.capability.PlayerFlags;
import com.alex.guzhenren.capability.providers.PlayerFlagsProvider;
import com.alex.guzhenren.networking.s2c_packet.FlagsSyncS2CPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;
import java.util.function.Consumer;

public class PlayerFlagsUtils {

    private static Optional<PlayerFlags> getFlags(Player player) {
        return player.getCapability(PlayerFlagsProvider.PLAYER_FLAGS)
                .resolve()
                .map(Optional::of)
                .orElseThrow(() -> new IllegalStateException("PlayerFlags capability not resolved"));
    }

    private static void modifyAndSync(Player player, Consumer<PlayerFlags> modifier) {
        if (player instanceof ServerPlayer serverPlayer) {
            getFlags(player).ifPresent(flags -> {
                modifier.accept(flags);
                FlagsSyncS2CPacket.send(serverPlayer, flags);
            });
        }
    }

    // APERTURE AWAKEN
    public static boolean isApertureAwaken(Player player) {
        return getFlags(player)
                .map(PlayerFlags::isAwaken)
                .orElse(false);
    }

    public static void setApertureAwaken(Player player, boolean b) {
        modifyAndSync(player, flags -> flags.setAwaken(b));
    }
}
