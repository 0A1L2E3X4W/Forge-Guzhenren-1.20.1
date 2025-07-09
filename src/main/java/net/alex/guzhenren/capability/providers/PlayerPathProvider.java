package net.alex.guzhenren.capability.providers;

import net.alex.guzhenren.capability.PlayerPath;
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

public class PlayerPathProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerPath> PLAYER_PATH_DATA = CapabilityManager.get(new CapabilityToken<>() {});

    private PlayerPath playerPath;
    private final LazyOptional<PlayerPath> optional = LazyOptional.of(this::createPlayerPathData);

    private @NotNull PlayerPath createPlayerPathData() {
        if (playerPath == null) { playerPath = new PlayerPath(); }
        return playerPath;
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
