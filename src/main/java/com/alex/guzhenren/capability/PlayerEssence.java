package com.alex.guzhenren.capability;

import net.minecraft.nbt.CompoundTag;

public class PlayerEssence {

    private static final String KEY_ESSENCE = "guzhenren.player.essence";
    private static final String KEY_MAX_ESSENCE = "guzhenren.player.max_essence";

    private static final float DEFAULT_ESSENCE = 0f;
    private static final int DEFAULT_MAX_ESSENCE = 0;

    private float essence = DEFAULT_ESSENCE;
    private int maxEssence = DEFAULT_MAX_ESSENCE;

    // GETTER & SETTER
    public float getEssence() { return essence; }
    public void setEssence(float v) { essence = v; }
    public void addEssence(float v) { essence = Math.min(essence + v, maxEssence); }
    public void subEssence(float v) { essence = Math.max(essence - v, 0); }

    public int getMaxEssence() { return maxEssence; }
    public void setMaxEssence(int i) { maxEssence = i; }

    // COPY FROM
    public void copyFrom(PlayerEssence source) {
        essence = source.essence;
        maxEssence = source.maxEssence;
    }

    // NBT DATA
    public void saveNbtData(CompoundTag nbt) {
        nbt.putFloat(KEY_ESSENCE, essence);
        nbt.putInt(KEY_MAX_ESSENCE, maxEssence);
    }

    public void loadNbtData(CompoundTag nbt) {
        this.setEssence(nbt.contains(KEY_ESSENCE) ? nbt.getFloat(KEY_ESSENCE) : DEFAULT_ESSENCE);
        this.setMaxEssence(nbt.contains(KEY_MAX_ESSENCE) ? nbt.getInt(KEY_MAX_ESSENCE) : DEFAULT_MAX_ESSENCE);
    }
}
