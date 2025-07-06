package com.alex.guzhenren.event;

import com.alex.guzhenren.Guzhenren;
import com.alex.guzhenren.capability.*;
import com.alex.guzhenren.capability.providers.PlayerAptitudesProvider;
import com.alex.guzhenren.capability.providers.PlayerEssenceProvider;
import com.alex.guzhenren.capability.providers.PlayerFlagsProvider;
import com.alex.guzhenren.networking.packet.AptitudesSyncS2CPacket;
import com.alex.guzhenren.networking.packet.EssenceSyncS2CPacket;
import com.alex.guzhenren.networking.packet.FlagsSyncS2CPacket;
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

@Mod.EventBusSubscriber(modid = Guzhenren.MOD_ID)
public class ModCommonEvent {

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
            }
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {

            if (!event.getObject().getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).isPresent()) {
                event.addCapability(ResourceLocation.fromNamespaceAndPath(
                        Guzhenren.MOD_ID, "property_essence"), new PlayerEssenceProvider());
            }

            if (!event.getObject().getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE).isPresent()) {
                event.addCapability(ResourceLocation.fromNamespaceAndPath(
                        Guzhenren.MOD_ID, "property_aptitudes"), new PlayerAptitudesProvider());
            }

            if (!event.getObject().getCapability(PlayerFlagsProvider.PLAYER_FLAGS).isPresent()) {
                event.addCapability(ResourceLocation.fromNamespaceAndPath(
                        Guzhenren.MOD_ID, "property_flags"), new PlayerFlagsProvider());
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
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerEssence.class);
        event.register(PlayerAptitudes.class);
        event.register(PlayerFlags.class);
    }

    // 玩家每tick事件
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {

            event.player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                int playerMaxEssence = essence.getMaxEssence();
                float playerEssence = essence.getEssence();
                if (playerEssence < playerMaxEssence) {
                    essence.addEssence((float) playerMaxEssence / (20*60*10));
                    EssenceSyncS2CPacket.send((ServerPlayer) event.player, essence);
                }
            });

            event.player.getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE).ifPresent(aptitudes -> {
                float playerLifespan = aptitudes.getLifespan();
                if (playerLifespan > 0.1F) {
                    aptitudes.subLifespan(1.0F / (20.0F * 60.0F * 10.0F));
                    aptitudes.subThoughts(100.0F / (20.0F * 60.0F * 10.0F));
                    AptitudesSyncS2CPacket.send((ServerPlayer) event.player, aptitudes);
                }
            });
        }
    }

    // 玩家睡觉事件
    @SubscribeEvent
    public static void onPlayerWakeUp(PlayerWakeUpEvent event) {

        Player player = event.getEntity();
        if (player.level().isClientSide()) {
            return;
        }

        if (!event.updateLevel()) {
            player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                essence.setEssence(essence.getMaxEssence());
                EssenceSyncS2CPacket.send((ServerPlayer) player, essence);
            });

            player.getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE).ifPresent(aptitudes -> {
                float currentThoughts = aptitudes.getThoughts();
                if (currentThoughts < 800.0F) { aptitudes.setThoughts(800.0F); }
                AptitudesSyncS2CPacket.send((ServerPlayer) player, aptitudes);
            });
        }
    }
}
