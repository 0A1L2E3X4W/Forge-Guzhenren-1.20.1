package net.alex.guzhenren.capability;

import net.minecraft.nbt.CompoundTag;

public class PlayerEssence {

    // NAME KEYS
    private static final String KEY_MAX_ESSENCE = "guzhenren.player.max_essence";
    private static final String KEY_CURRENT_ESSENCE = "guzhenren.player.current_essence";

    // DEFAULT VALUES
    private static final int DEFAULT_MAX_ESSENCE = 0;
    private static final float DEFAULT_CURRENT_ESSENCE = 0.0F;

    // ESSENCE DATA
    private int maxEssence = DEFAULT_MAX_ESSENCE;
    private float currentEssence = DEFAULT_CURRENT_ESSENCE;

    // COPY FROM
    public void copyFrom(PlayerEssence source) {
        maxEssence = source.maxEssence;
        currentEssence = source.currentEssence;
    }

    // NBT DATA SAVE
    public void saveNbtData(CompoundTag nbt) {
        nbt.putInt(KEY_MAX_ESSENCE, maxEssence);
        nbt.putFloat(KEY_CURRENT_ESSENCE, currentEssence);
    }

    // NBT DATA LOAD
    public void loadNbtData(CompoundTag nbt) {
        this.setMaxEssence(nbt.contains(KEY_MAX_ESSENCE) ? nbt.getInt(KEY_MAX_ESSENCE) : DEFAULT_MAX_ESSENCE);
        this.setCurrentEssence(nbt.contains(KEY_CURRENT_ESSENCE) ? nbt.getFloat(KEY_CURRENT_ESSENCE) : DEFAULT_CURRENT_ESSENCE);
    }

    // GETTER & SETTER
    public float getCurrentEssence() { return currentEssence; }
    public void setCurrentEssence(float v) { currentEssence = v; }
    public void addCurrentEssence(float v) { currentEssence = Math.min(currentEssence + v, maxEssence); }
    public void subCurrentEssence(float v) { currentEssence = Math.max(currentEssence - v, 0.0F); }
    public int getMaxEssence() { return maxEssence; }
    public void setMaxEssence(int i) { maxEssence = i; }
}
