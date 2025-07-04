package com.alex.guzhenren.capability;

import com.alex.guzhenren.utils.enums.ModRank;
import com.alex.guzhenren.utils.enums.ModStage;
import com.alex.guzhenren.utils.enums.ModTalent;
import net.minecraft.nbt.CompoundTag;

public class PlayerAptitudes {

    // NBT Keys
    private static final String KEY_LIFESPAN = "guzhenren.player.lifespan";
    private static final String KEY_THOUGHTS = "guzhenren.player.thoughts";
    private static final String KEY_SOUL = "guzhenren.player.soul";
    private static final String KEY_LUCK = "guzhenren.player.luck";
    private static final String KEY_MORAL = "guzhenren.player.moral";
    private static final String KEY_RANK = "guzhenren.player.rank";
    private static final String KEY_STAGE = "guzhenren.player.stage";
    private static final String KEY_TALENT = "guzhenren.player.talent";

    // Default values for new players
    private static final float DEFAULT_LIFESPAN = 100.0f;
    private static final float DEFAULT_THOUGHTS = 1000.0f;
    private static final int DEFAULT_SOUL = 100;
    private static final int DEFAULT_LUCK = 0;
    private static final int DEFAULT_MORAL = 100;
    private static final ModRank DEFAULT_RANK = ModRank.MORTAL;
    private static final ModStage DEFAULT_STAGE = ModStage.NULL;
    private static final ModTalent DEFAULT_TALENT = ModTalent.NULL;

    // Player attributes
    private float lifespan = DEFAULT_LIFESPAN;
    private float thoughts = DEFAULT_THOUGHTS;
    private int soul = DEFAULT_SOUL;
    private int luck = DEFAULT_LUCK;
    private int moral = DEFAULT_MORAL;
    private ModRank rank = DEFAULT_RANK;
    private ModStage stage = DEFAULT_STAGE;
    private ModTalent talent = DEFAULT_TALENT;

    // GETTER & SETTER
    public float getLifespan() { return lifespan; }
    public void setLifespan(float v) { lifespan = v; }
    public void addLifespan(float v) { lifespan += v; }
    public void subLifespan(float v) { lifespan = Math.max(lifespan - v, 0); }

    public float getThoughts() { return thoughts; }
    public void setThoughts(float v) { thoughts = v; }
    public void addThoughts(float v) { thoughts += v; }
    public void subThoughts(float v) { thoughts = Math.max(thoughts - v, 0); }

    public int getSoul() { return soul; }
    public void setSoul(int i) { soul = i; }
    public void addSoul(int i) { soul += i; }
    public void subSoul(int i) { soul = Math.max(soul - i, 0); }

    public int getLuck() { return luck; }
    public void setLuck(int i) { luck = i; }
    public void addLuck(int i) { luck += i; }
    public void subLuck(int i) { luck = Math.max(luck - i, 0); }

    public int getMoral() { return moral; }
    public void setMoral(int i) { moral = i; }
    public void addMoral(int i) { moral += i; }
    public void subMoral(int i) { moral = Math.max(moral - i, 0); }

    public ModRank getRank() { return rank; }
    public void setRank(ModRank modRank) { rank = modRank; }

    public ModStage getStage() { return stage; }
    public void setStage(ModStage modStage) { stage = modStage; }

    public ModTalent getTalent() { return talent; }
    public void setTalent(ModTalent modTalent) { talent = modTalent; }

    // COPY FROM
    public void copyFrom(PlayerAptitudes source) {
        if (source == null) { return; }
        lifespan = source.lifespan; thoughts = source.thoughts;
        soul = source.soul; luck = source.luck; moral = source.moral;
        rank = source.rank; stage = source.stage; talent = source.talent;
    }

    // NBT DATA
    public void saveNbtData(CompoundTag nbt) {
        nbt.putFloat(KEY_LIFESPAN, lifespan);
        nbt.putFloat(KEY_THOUGHTS, thoughts);
        nbt.putInt(KEY_SOUL, soul);
        nbt.putInt(KEY_LUCK, luck);
        nbt.putInt(KEY_MORAL, moral);
        nbt.putString(KEY_RANK, rank.name());
        nbt.putString(KEY_STAGE, stage.name());
        nbt.putString(KEY_TALENT, talent.name());
    }

    public void loadNbtData(CompoundTag nbt) {
        if (nbt == null) { return; }
        setLifespan(nbt.contains(KEY_LIFESPAN) ? nbt.getFloat(KEY_LIFESPAN) : DEFAULT_LIFESPAN);
        setThoughts(nbt.contains(KEY_THOUGHTS) ? nbt.getFloat(KEY_THOUGHTS) : DEFAULT_THOUGHTS);
        setSoul(nbt.contains(KEY_SOUL) ? nbt.getInt(KEY_SOUL) : DEFAULT_SOUL);
        setLuck(nbt.contains(KEY_LUCK) ? nbt.getInt(KEY_LUCK) : DEFAULT_LUCK);
        setMoral(nbt.contains(KEY_MORAL) ? nbt.getInt(KEY_MORAL) : DEFAULT_MORAL);
        setRank(nbt.contains(KEY_RANK) ? ModRank.valueOf(nbt.getString(KEY_RANK)) : DEFAULT_RANK);
        setStage(nbt.contains(KEY_STAGE) ? ModStage.valueOf(nbt.getString(KEY_STAGE)) : DEFAULT_STAGE);
        setTalent(nbt.contains(KEY_TALENT) ? ModTalent.valueOf(nbt.getString(KEY_TALENT)) : DEFAULT_TALENT);
    }
}
