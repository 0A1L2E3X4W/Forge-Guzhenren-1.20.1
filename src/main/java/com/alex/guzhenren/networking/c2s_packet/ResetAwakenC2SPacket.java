package com.alex.guzhenren.networking.c2s_packet;

import com.alex.guzhenren.networking.ModMessage;
import com.alex.guzhenren.utils.capability.PlayerAptitudesUtils;
import com.alex.guzhenren.utils.capability.PlayerEssenceUtils;
import com.alex.guzhenren.utils.capability.PlayerFlagsUtils;
import com.alex.guzhenren.utils.enums.ModExtremePhysique;
import com.alex.guzhenren.utils.enums.ModRank;
import com.alex.guzhenren.utils.enums.ModStage;
import com.alex.guzhenren.utils.enums.ModTalent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ResetAwakenC2SPacket {

    public ResetAwakenC2SPacket() {

    }

    public ResetAwakenC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            PlayerAptitudesUtils.setTalent(context.getSender(), ModTalent.NULL);
            PlayerAptitudesUtils.setExtremePhysique(context.getSender(), ModExtremePhysique.NULL);
            PlayerAptitudesUtils.setRank(context.getSender(), ModRank.MORTAL);
            PlayerAptitudesUtils.setStage(context.getSender(), ModStage.NULL);
            PlayerEssenceUtils.setMaxEssence(context.getSender(), 0);
            PlayerEssenceUtils.setCurrentEssence(context.getSender(), 0.0F);
            PlayerFlagsUtils.setApertureAwaken(context.getSender(), false);
        });
    }

    public static void send() {
        ModMessage.sendToServer(new ResetAwakenC2SPacket());
    }
}
