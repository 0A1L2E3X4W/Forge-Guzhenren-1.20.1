package com.alex.guzhenren.capability.providers;

import com.alex.guzhenren.capability.PlayerPathData;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerPathDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerPathData> PLAYER_PATH_DATA = CapabilityManager.get(new CapabilityToken<>() {});

    private PlayerPathData playerPathData;
    private final LazyOptional<PlayerPathData> optional = LazyOptional.of(this::createPlayerPathData);

    private @NotNull PlayerPathData createPlayerPathData() {
        if (playerPathData == null) { playerPathData = new PlayerPathData(); }
        return playerPathData;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if (capability == PLAYER_PATH_DATA) { return optional.cast(); }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerPathData().saveNbtData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerPathData().loadNbtData(nbt);
    }
}
