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
    public void setEssence(float val) { essence = val; }
    public void addEssence(float val) { essence = Math.min(essence + val, maxEssence); }
    public void subEssence(float val) { essence = Math.max(essence - val, 0); }

    public int getMaxEssence() { return maxEssence; }
    public void setMaxEssence(int val) { maxEssence = val; }

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
