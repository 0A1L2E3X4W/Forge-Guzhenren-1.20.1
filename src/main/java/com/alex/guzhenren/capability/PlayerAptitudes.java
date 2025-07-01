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
    private float lifespan;
    private float thoughts;
    private int soul;
    private int luck;
    private int moral;
    private ModRank rank;
    private ModStage stage;
    private ModTalent talent;

    public PlayerAptitudes() { resetProperties(); }

    // GETTER & SETTER
    public float getLifespan() { return lifespan; }
    public void setLifespan(float val) { lifespan = val; }
    public void addLifespan(float val) { lifespan += val; }
    public void subLifespan(float val) { lifespan = Math.max(lifespan - val, 0); }

    public float getThoughts() { return thoughts; }
    public void setThoughts(float val) { thoughts = val; }
    public void addThoughts(float val) { thoughts += val; }
    public void subThoughts(float val) { thoughts = Math.max(thoughts - val, 0); }

    public int getSoul() { return soul; }
    public void setSoul(int val) { soul = val; }
    public void addSoul(int val) { soul += val; }
    public void subSoul(int val) { soul = Math.max(soul - val, 0); }

    public int getLuck() { return luck; }
    public void setLuck(int val) { luck = val; }
    public void addLuck(int val) { luck += val; }
    public void subLuck(int val) { luck = Math.max(luck - val, 0); }

    public int getMoral() { return moral; }
    public void setMoral(int val) { moral = val; }
    public void addMoral(int val) { moral += val; }
    public void subMoral(int val) { moral = Math.max(moral - val, 0); }

    public ModRank getRank() { return rank; }
    public void setRank(ModRank val) { rank = val; }

    public ModStage getStage() { return stage; }
    public void setStage(ModStage val) { stage = val; }

    public ModTalent getTalent() { return talent; }
    public void setTalent(ModTalent val) { talent = val; }

    // COPY FROM
    public void copyFrom(PlayerAptitudes source) {
        if (source == null) { resetProperties(); return; }
        lifespan = source.lifespan; thoughts = source.thoughts;
        soul = source.soul; luck = source.luck; moral = source.moral;
        rank = source.rank; stage = source.stage; talent = source.talent;
    }

    // NBT DATA
    public void saveNbtData(CompoundTag nbt) {
        if (nbt == null) return;
        nbt.putFloat(KEY_LIFESPAN, lifespan);
        nbt.putFloat(KEY_THOUGHTS, thoughts);
        nbt.putInt(KEY_SOUL, soul);
        nbt.putInt(KEY_LUCK, luck);
        nbt.putInt(KEY_MORAL, moral);
        nbt.putString(KEY_RANK, rank != null ? rank.name() : ModRank.MORTAL.name());
        nbt.putString(KEY_STAGE, stage != null ? stage.name() : ModStage.INIT.name());
        nbt.putString(KEY_TALENT, talent != null ? talent.name() : ModTalent.NULL.name());
    }

    public void loadNbtData(CompoundTag nbt) {
        if (nbt == null) { resetProperties(); return; }
        this.setLifespan(nbt.contains(KEY_LIFESPAN) ? nbt.getFloat(KEY_LIFESPAN) : DEFAULT_LIFESPAN);
        this.setThoughts(nbt.contains(KEY_THOUGHTS) ? nbt.getFloat(KEY_THOUGHTS) : DEFAULT_THOUGHTS);
        this.setSoul(nbt.contains(KEY_SOUL) ? nbt.getInt(KEY_SOUL) : DEFAULT_SOUL);
        this.setLuck(nbt.contains(KEY_LUCK) ? nbt.getInt(KEY_LUCK) : DEFAULT_LUCK);
        this.setMoral(nbt.contains(KEY_MORAL) ? nbt.getInt(KEY_MORAL) : DEFAULT_MORAL);
        this.setRank(nbt.contains(KEY_RANK) ? ModRank.valueOf(nbt.getString(KEY_RANK)) : DEFAULT_RANK);
        this.setStage(nbt.contains(KEY_STAGE) ? ModStage.valueOf(nbt.getString(KEY_STAGE)) : DEFAULT_STAGE);
        this.setTalent(nbt.contains(KEY_TALENT) ? ModTalent.valueOf(nbt.getString(KEY_TALENT)) : DEFAULT_TALENT);
    }

    private void resetProperties() {
        this.lifespan = DEFAULT_LIFESPAN;
        this.thoughts = DEFAULT_THOUGHTS;
        this.soul = DEFAULT_SOUL;
        this.luck = DEFAULT_LUCK;
        this.moral = DEFAULT_MORAL;
        this.rank = DEFAULT_RANK;
        this.stage = DEFAULT_STAGE;
        this.talent = DEFAULT_TALENT;
    }
}
