package com.alex.guzhenren.networking.packet;

import com.alex.guzhenren.client.data.ClientAptitudesData;
import com.alex.guzhenren.utils.enums.ModRank;
import com.alex.guzhenren.utils.enums.ModStage;
import com.alex.guzhenren.utils.enums.ModTalent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncAptitudeS2C {

    private final float lifespan;
    private final float thoughts;
    private final int soul;
    private final int luck;
    private final int moral;
    private final ModRank rank;
    private final ModStage stage;
    private final ModTalent talent;

    public SyncAptitudeS2C(
            float lifespan, float thoughts,
            int soul, int luck, int moral,
            ModRank rank, ModStage stage, ModTalent talent
    ) {
        this.lifespan = lifespan;
        this.thoughts = thoughts;
        this.soul = soul;
        this.luck = luck;
        this.moral = moral;
        this.rank = rank;
        this.stage = stage;
        this.talent = talent;
    }

    public SyncAptitudeS2C(FriendlyByteBuf buf) {
        lifespan = buf.readFloat();
        thoughts = buf.readFloat();
        soul = buf.readInt();
        luck = buf.readInt();
        moral = buf.readInt();
        rank = buf.readEnum(ModRank.class);
        stage = buf.readEnum(ModStage.class);
        talent = buf.readEnum(ModTalent.class);

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
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
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
        });
        return true;
    }
}
