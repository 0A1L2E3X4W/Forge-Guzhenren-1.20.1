package com.alex.guzhenren.capability;

import net.minecraft.nbt.CompoundTag;

public class PlayerEssence {

    // NAME KEYS
    private static final String KEY_ESSENCE = "guzhenren.player.current_essence";
    private static final String KEY_MAX_ESSENCE = "guzhenren.player.max_essence";

    // DEFAULT VALUES
    private static final int DEFAULT_MAX_ESSENCE = 0;
    private static final float DEFAULT_ESSENCE = 0.0F;

    // ESSENCE DATA
    private int maxEssence = DEFAULT_MAX_ESSENCE;
    private float currentEssence = DEFAULT_ESSENCE;

    // GETTER & SETTER
    public float getCurrentEssence() { return currentEssence; }
    public void setCurrentEssence(float v) { currentEssence = v; }
    public void addCurrentEssence(float v) { currentEssence = Math.min(currentEssence + v, maxEssence); }
    public void subCurrentEssence(float v) { currentEssence = Math.max(currentEssence - v, 0.0F); }

    public int getMaxEssence() { return maxEssence; }
    public void setMaxEssence(int i) { maxEssence = i; }

    // COPY FROM
    public void copyFrom(PlayerEssence source) {
        currentEssence = source.currentEssence;
        maxEssence = source.maxEssence;
    }

    // NBT DATA
    public void saveNbtData(CompoundTag nbt) {
        nbt.putFloat(KEY_ESSENCE, currentEssence);
        nbt.putInt(KEY_MAX_ESSENCE, maxEssence);
    }

    public void loadNbtData(CompoundTag nbt) {
        this.setCurrentEssence(nbt.contains(KEY_ESSENCE) ? nbt.getFloat(KEY_ESSENCE) : DEFAULT_ESSENCE);
        this.setMaxEssence(nbt.contains(KEY_MAX_ESSENCE) ? nbt.getInt(KEY_MAX_ESSENCE) : DEFAULT_MAX_ESSENCE);
    }
}
