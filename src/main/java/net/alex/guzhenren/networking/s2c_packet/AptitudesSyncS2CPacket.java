package net.alex.guzhenren.networking.s2c_packet;

import net.alex.guzhenren.capability.PlayerAptitudes;
import net.alex.guzhenren.client.data.ClientAptitudesData;
import net.alex.guzhenren.networking.ModMessage;
import com.alex.guzhenren.utils.enums.*;
import net.alex.guzhenren.utils.enums.ModExtremePhysique;
import net.alex.guzhenren.utils.enums.ModRank;
import net.alex.guzhenren.utils.enums.ModStage;
import net.alex.guzhenren.utils.enums.ModTalent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AptitudesSyncS2CPacket {

    private final float lifespan;
    private final float thoughts;
    private final int soul;
    private final int luck;
    private final int moral;
    private final ModRank rank;
    private final ModStage stage;
    private final ModTalent talent;
    private final ModExtremePhysique extremePhysique;

    public AptitudesSyncS2CPacket(
            float lifespan, float thoughts,
            int soul, int luck, int moral,
            ModRank rank, ModStage stage, ModTalent talent, ModExtremePhysique extremePhysique
    ) {
        this.lifespan = lifespan; this.thoughts = thoughts;
        this.soul = soul; this.luck = luck; this.moral = moral;
        this.rank = rank; this.stage = stage; this.talent = talent; this.extremePhysique = extremePhysique;
    }

    public AptitudesSyncS2CPacket(FriendlyByteBuf buf) {
        lifespan = buf.readFloat();
        thoughts = buf.readFloat();
        soul     = buf.readInt();
        luck     = buf.readInt();
        moral    = buf.readInt();
        rank     = buf.readEnum(ModRank.class);
        stage    = buf.readEnum(ModStage.class);
        talent   = buf.readEnum(ModTalent.class);
        extremePhysique = buf.readEnum(ModExtremePhysique.class);

    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(lifespan);
        buf.writeFloat(thoughts);
        buf.writeInt(soul);
        buf.writeInt(luck);
        buf.writeInt(moral);
        buf.writeEnum(rank);
        buf.writeEnum(stage);
        buf.writeEnum(talent);
        buf.writeEnum(extremePhysique);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientAptitudesData.setLifespan(lifespan);
            ClientAptitudesData.setThoughts(thoughts);
            ClientAptitudesData.setSoul(soul);
            ClientAptitudesData.setLuck(luck);
            ClientAptitudesData.setMoral(moral);
            ClientAptitudesData.setRank(rank);
            ClientAptitudesData.setStage(stage);
            ClientAptitudesData.setTalent(talent);
            ClientAptitudesData.setExtremePhysique(extremePhysique);
        });
    }

    public static void send(ServerPlayer serverPlayer, PlayerAptitudes aptitudes) {
        ModMessage.sendToPlayer(new AptitudesSyncS2CPacket(
                aptitudes.getLifespan(), aptitudes.getThoughts(),
                aptitudes.getSoul(), aptitudes.getLuck(), aptitudes.getMoral(),
                aptitudes.getRank(), aptitudes.getStage(), aptitudes.getTalent(), aptitudes.getExtremePhysique()),
                serverPlayer);
    }
}
