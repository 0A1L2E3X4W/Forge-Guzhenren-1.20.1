package com.alex.guzhenren.networking.packet;

import com.alex.guzhenren.capability.PlayerAptitudesProvider;
import com.alex.guzhenren.networking.ModMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AptitudesSyncC2SPacket {

    public AptitudesSyncC2SPacket() {

    }

    public AptitudesSyncC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

            ServerPlayer serverPlayer = context.getSender();
            if (serverPlayer == null) return;
            ServerLevel serverLevel = serverPlayer.serverLevel();

            serverPlayer.getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE).ifPresent(aptitude -> {

                ModMessage.sendToPlayer(new AptitudesSyncS2CPacket(
                        aptitude.getLifespan(),
                        aptitude.getThoughts(),
                        aptitude.getSoul(),
                        aptitude.getLuck(),
                        aptitude.getMoral(),
                        aptitude.getRank(),
                        aptitude.getStage(),
                        aptitude.getTalent()),
                        serverPlayer);
            });
        });
    }
}
