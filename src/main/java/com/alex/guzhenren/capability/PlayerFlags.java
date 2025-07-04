package com.alex.guzhenren.capability;

import net.minecraft.nbt.CompoundTag;

public class PlayerFlags {

    // NBT Keys
    private static final String KEY_IS_AWAKEN = "guzhenren.player.flag.is_awaken";

    // Default values for new players
    private static final boolean DEFAULT_IS_AWAKEN = false;

    // PLAYER FLAGS
    private boolean isAwaken = DEFAULT_IS_AWAKEN;

    // GETTER & SETTER
    public boolean isAwaken() { return isAwaken; }
    public void setAwaken(boolean b) { isAwaken = b; }

    // COPY FROM
    public void copyFrom(PlayerFlags source) {
        isAwaken = source.isAwaken;
    }

    // NBT DATA
    public void saveNbtData(CompoundTag nbt) {
        nbt.putBoolean(KEY_IS_AWAKEN, isAwaken);
    }

    public void loadNbtData(CompoundTag nbt) {
        setAwaken(nbt.contains(KEY_IS_AWAKEN) ? nbt.getBoolean(KEY_IS_AWAKEN) : DEFAULT_IS_AWAKEN);
    }
}
