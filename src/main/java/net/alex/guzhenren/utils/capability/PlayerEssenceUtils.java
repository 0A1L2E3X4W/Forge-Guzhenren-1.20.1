package net.alex.guzhenren.utils.capability;

import net.alex.guzhenren.capability.PlayerEssence;
import net.alex.guzhenren.capability.providers.PlayerEssenceProvider;
import net.alex.guzhenren.networking.s2c_packet.EssenceSyncS2CPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;
import java.util.function.Consumer;

public class PlayerEssenceUtils {

    private static Optional<PlayerEssence> getEssence(Player player) {
        return player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE)
                .resolve()
                .map(Optional::of)
                .orElseThrow(() -> new IllegalStateException("PlayerEssence capability not resolved"));
    }

    private static void modifyAndSync(Player player, Consumer<PlayerEssence> modifier) {
        if (player instanceof ServerPlayer serverPlayer) {
            getEssence(player).ifPresent(essence -> {
                modifier.accept(essence);
                EssenceSyncS2CPacket.send(serverPlayer, essence);
            });
        }
    }

    // MAX ESSENCE
    public static int getMaxEssence(Player player) {
        return getEssence(player)
                .map(PlayerEssence::getMaxEssence)
                .orElse(0);
    }

    public static void setMaxEssence(Player player, int i) {
        modifyAndSync(player, essence -> essence.setMaxEssence(i));
    }

    // CURRENT ESSENCE
    public static float getCurrentEssence(Player player) {
        return getEssence(player)
                .map(PlayerEssence::getCurrentEssence)
                .orElse(0.0F);
    }

    public static void setCurrentEssence(Player player, float v) {
        modifyAndSync(player, essence -> essence.setCurrentEssence(v));
    }

    public static void addCurrentEssence(Player player, float v) {
        modifyAndSync(player, essence -> essence.addCurrentEssence(v));
    }

    public static void subCurrentEssence(Player player, float v) {
        modifyAndSync(player, essence -> essence.subCurrentEssence(v));
    }
}
