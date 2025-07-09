package net.alex.guzhenren.event;

import net.alex.guzhenren.GuzhenrenMod;
import com.alex.guzhenren.capability.*;
import com.alex.guzhenren.capability.providers.*;
import com.alex.guzhenren.networking.s2c_packet.*;
import com.alex.guzhenren.utils.capability.*;
import net.alex.guzhenren.capability.PlayerAptitudes;
import net.alex.guzhenren.capability.PlayerEssence;
import net.alex.guzhenren.capability.PlayerFlags;
import net.alex.guzhenren.capability.PlayerPath;
import net.alex.guzhenren.capability.providers.PlayerAptitudesProvider;
import net.alex.guzhenren.capability.providers.PlayerEssenceProvider;
import net.alex.guzhenren.capability.providers.PlayerFlagsProvider;
import net.alex.guzhenren.capability.providers.PlayerPathProvider;
import net.alex.guzhenren.networking.s2c_packet.AptitudesSyncS2CPacket;
import net.alex.guzhenren.networking.s2c_packet.EssenceSyncS2CPacket;
import net.alex.guzhenren.networking.s2c_packet.FlagsSyncS2CPacket;
import net.alex.guzhenren.networking.s2c_packet.PathDataSyncS2CPacket;
import net.alex.guzhenren.utils.capability.PlayerAptitudesUtils;
import net.alex.guzhenren.utils.capability.PlayerEssenceUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GuzhenrenMod.MOD_ID)
public class ModCommonEvent {

    // CAPABILITY相关事件
    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer serverPlayer) {

                serverPlayer.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                    EssenceSyncS2CPacket.send(serverPlayer, essence);
                });

                serverPlayer.getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE).ifPresent(aptitudes -> {
                    AptitudesSyncS2CPacket.send(serverPlayer, aptitudes);
                });

                serverPlayer.getCapability(PlayerFlagsProvider.PLAYER_FLAGS).ifPresent(flags -> {
                    FlagsSyncS2CPacket.send(serverPlayer, flags);
                });

                serverPlayer.getCapability(PlayerPathProvider.PLAYER_PATH_DATA).ifPresent(pathData -> {
                    PathDataSyncS2CPacket.send(serverPlayer, pathData);
                });
            }
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {

            if (!event.getObject().getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).isPresent()) {
                event.addCapability(ResourceLocation.fromNamespaceAndPath(
                        GuzhenrenMod.MOD_ID, "property_essence"), new PlayerEssenceProvider());
            }

            if (!event.getObject().getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE).isPresent()) {
                event.addCapability(ResourceLocation.fromNamespaceAndPath(
                        GuzhenrenMod.MOD_ID, "property_aptitudes"), new PlayerAptitudesProvider());
            }

            if (!event.getObject().getCapability(PlayerFlagsProvider.PLAYER_FLAGS).isPresent()) {
                event.addCapability(ResourceLocation.fromNamespaceAndPath(
                        GuzhenrenMod.MOD_ID, "property_flags"), new PlayerFlagsProvider());
            }

            if (!event.getObject().getCapability(PlayerPathProvider.PLAYER_PATH_DATA).isPresent()) {
                event.addCapability(ResourceLocation.fromNamespaceAndPath(
                        GuzhenrenMod.MOD_ID, "property_path_data"), new PlayerPathProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {

            event.getOriginal().getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });

            event.getOriginal().getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });

            event.getOriginal().getCapability(PlayerFlagsProvider.PLAYER_FLAGS).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerFlagsProvider.PLAYER_FLAGS).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });

            event.getOriginal().getCapability(PlayerPathProvider.PLAYER_PATH_DATA).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerPathProvider.PLAYER_PATH_DATA).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerEssence.class);
        event.register(PlayerAptitudes.class);
        event.register(PlayerFlags.class);
        event.register(PlayerPath.class);
    }

    // 玩家每tick事件
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {

            int playerMaxEssence = PlayerEssenceUtils.getMaxEssence(event.player);
            float playerCurrentEssence = PlayerEssenceUtils.getCurrentEssence(event.player);

            if (playerMaxEssence > playerCurrentEssence && playerMaxEssence > 0) {
                PlayerEssenceUtils.addCurrentEssence(event.player, playerMaxEssence / (20.0F * 60.0F * 10.0F));
            }

            PlayerAptitudesUtils.subLifespan(event.player, 1.0F / (20.0F * 60.0F * 10.0F));
            PlayerAptitudesUtils.subThoughts(event.player, 100.0F / (20.0F * 60.0F * 10.0F));
        }
    }

    // 玩家睡觉事件
    @SubscribeEvent
    public static void onPlayerWakeUp(PlayerWakeUpEvent event) {

        Player player = event.getEntity();

        if (player.level().isClientSide()) return;
        if (event.updateLevel()) return;

        PlayerEssenceUtils.setCurrentEssence(player, PlayerEssenceUtils.getMaxEssence(player));

        if (PlayerAptitudesUtils.getThoughts(player) < 800.0F) {
            PlayerAptitudesUtils.setThoughts(player, 800.0F);
        }
    }
}
