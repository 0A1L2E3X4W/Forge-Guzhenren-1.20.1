package net.alex.guzhenren.utils.capability;

import net.alex.guzhenren.capability.PlayerAptitudes;
import net.alex.guzhenren.capability.providers.PlayerAptitudesProvider;
import net.alex.guzhenren.networking.s2c_packet.AptitudesSyncS2CPacket;
import net.alex.guzhenren.utils.enums.ModExtremePhysique;
import net.alex.guzhenren.utils.enums.ModRank;
import net.alex.guzhenren.utils.enums.ModStage;
import net.alex.guzhenren.utils.enums.ModTalent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;
import java.util.function.Consumer;

public class PlayerAptitudesUtils {

    private static Optional<PlayerAptitudes> getAptitude(Player player) {
        return player.getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE)
                .resolve()
                .map(Optional::of)
                .orElseThrow(() -> new IllegalStateException("PlayerAptitude capability not resolved"));
    }

    private static void modifyAndSync(Player player, Consumer<PlayerAptitudes> modifier) {
        if (player instanceof ServerPlayer serverPlayer) {
            getAptitude(player).ifPresent(aptitude -> {
                modifier.accept(aptitude);
                AptitudesSyncS2CPacket.send(serverPlayer, aptitude);
            });
        }
    }

    // LIFESPAN
    public static float getLifespan(Player player) {
        return getAptitude(player)
                .map(PlayerAptitudes::getLifespan)
                .orElse(0.0F);
    }

    public static void addLifespan(Player player, float v) {
        modifyAndSync(player, aptitude -> aptitude.addLifespan(v));
    }

    public static void subLifespan(Player player, float v) {
        modifyAndSync(player, aptitude -> aptitude.subLifespan(v));
    }

    // THOUGHTS
    public static float getThoughts(Player player) {
        return getAptitude(player)
                .map(PlayerAptitudes::getThoughts)
                .orElse(0.0F);
    }

    public static void setThoughts(Player player, float v) {
        modifyAndSync(player, aptitude -> aptitude.setThoughts(v));
    }

    public static void addThoughts(Player player, float v) {
        modifyAndSync(player, aptitude -> aptitude.addThoughts(v));
    }

    public static void subThoughts(Player player, float v) {
        modifyAndSync(player, aptitude -> aptitude.subThoughts(v));
    }

    // LUCK
    public static int getLuck(Player player) {
        return getAptitude(player)
                .map(PlayerAptitudes::getLuck)
                .orElse(0);
    }

    public static void addLuck(Player player, int i) {
        modifyAndSync(player, aptitude -> aptitude.addLuck(i));
    }

    public static void subLuck(Player player, int i) {
        modifyAndSync(player, aptitude -> aptitude.subLuck(i));
    }

    // RANK
    public static ModRank getRank(Player player) {
        return getAptitude(player)
                .map(PlayerAptitudes::getRank)
                .orElse(ModRank.MORTAL);
    }

    public static void setRank(Player player, ModRank rank) {
        modifyAndSync(player, aptitude -> aptitude.setRank(rank));
    }

    // STAGE
    public static ModStage getStage(Player player) {
        return getAptitude(player)
                .map(PlayerAptitudes::getStage)
                .orElse(ModStage.NULL);
    }

    public static void setStage(Player player, ModStage stage) {
        modifyAndSync(player, aptitude -> aptitude.setStage(stage));
    }

    // ESSENCE MODIFIER
    public static float getEssenceModifier(Player player) {
        return getAptitude(player)
                .map(aptitude ->
                        aptitude.getRank().getEssenceModifier() +
                                aptitude.getStage().getEssenceModifier()
                )
                .orElse(1.0F);
    }

    // TALENT
    public static ModTalent getTalent(Player player) {
        return getAptitude(player)
                .map(PlayerAptitudes::getTalent)
                .orElse(ModTalent.NULL);
    }

    public static void setTalent(Player player, ModTalent talent) {
        modifyAndSync(player, aptitude -> aptitude.setTalent(talent));
    }

    // EXTREME PHYSIQUE
    public static ModExtremePhysique getExtremePhysique(Player player) {
        return getAptitude(player)
                .map(PlayerAptitudes::getExtremePhysique)
                .orElse(ModExtremePhysique.NULL);
    }

    public static void setExtremePhysique(Player player, ModExtremePhysique extremePhysique) {
        modifyAndSync(player, aptitude -> aptitude.setExtremePhysique(extremePhysique));
    }
}
