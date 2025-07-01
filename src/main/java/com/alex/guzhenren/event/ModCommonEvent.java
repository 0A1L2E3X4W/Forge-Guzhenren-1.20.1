package com.alex.guzhenren.event;

import com.alex.guzhenren.Guzhenren;
import com.alex.guzhenren.capability.PlayerAptitudes;
import com.alex.guzhenren.capability.PlayerAptitudesProvider;
import com.alex.guzhenren.capability.PlayerEssence;
import com.alex.guzhenren.capability.PlayerEssenceProvider;
import com.alex.guzhenren.networking.ModMessage;
import com.alex.guzhenren.networking.packet.SyncAptitudeS2C;
import com.alex.guzhenren.networking.packet.SyncEssenceS2C;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Guzhenren.MOD_ID)
public class ModCommonEvent {

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer serverPlayer) {

                serverPlayer.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {
                    ModMessage.sendToPlayer(new SyncEssenceS2C(
                            essence.getEssence(), essence.getMaxEssence()),
                            serverPlayer);
                });

                serverPlayer.getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE).ifPresent(aptitudes -> {
                    ModMessage.sendToPlayer(new SyncAptitudeS2C(
                            aptitudes.getLifespan(), aptitudes.getThoughts(),
                            aptitudes.getSoul(), aptitudes.getLuck(), aptitudes.getMoral(),
                            aptitudes.getRank(), aptitudes.getStage(), aptitudes.getTalent()),
                            serverPlayer);
                });
            }
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {

            if (!event.getObject().getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).isPresent()) {
                event.addCapability(
                        ResourceLocation.fromNamespaceAndPath(
                                Guzhenren.MOD_ID, "property_essence"), new PlayerEssenceProvider());
            }

            if (!event.getObject().getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE).isPresent()) {
                event.addCapability(
                        ResourceLocation.fromNamespaceAndPath(
                                Guzhenren.MOD_ID, "property_aptitudes"), new PlayerAptitudesProvider());
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
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {

            event.player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE).ifPresent(essence -> {

                int playerMaxEssence = essence.getMaxEssence();
                float playerEssence = essence.getEssence();

                if (playerEssence < playerMaxEssence) {
                    essence.addEssence((float) playerMaxEssence / (20*60*10));

                    ModMessage.sendToPlayer(new SyncEssenceS2C(
                            essence.getEssence(), essence.getMaxEssence()),
                            ((ServerPlayer) event.player));
                }
            });

            event.player.getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE).ifPresent(aptitudes -> {

                float playerLifespan = aptitudes.getLifespan();

                if (playerLifespan > 0.1f) {
                    aptitudes.subLifespan(1f / (20 * 60 * 10));

                    ModMessage.sendToPlayer(new SyncAptitudeS2C(
                            aptitudes.getLifespan(), aptitudes.getThoughts(),
                            aptitudes.getSoul(), aptitudes.getLuck(), aptitudes.getMoral(),
                            aptitudes.getRank(), aptitudes.getStage(), aptitudes.getTalent()),
                            ((ServerPlayer) event.player));
                }
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerEssence.class);
        event.register(PlayerAptitudes.class);
    }
}
