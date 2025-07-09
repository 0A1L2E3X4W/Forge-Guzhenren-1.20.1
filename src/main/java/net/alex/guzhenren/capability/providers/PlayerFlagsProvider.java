package net.alex.guzhenren.capability.providers;

import net.alex.guzhenren.capability.PlayerFlags;
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

public class PlayerFlagsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerFlags> PLAYER_FLAGS = CapabilityManager.get(new CapabilityToken<>() {});
    
    private PlayerFlags playerFlags;
    private final LazyOptional<PlayerFlags> optional = LazyOptional.of(this::createPlayerFlags);

    private PlayerFlags createPlayerFlags() {
        if (playerFlags == null) { playerFlags = new PlayerFlags(); }
        return playerFlags;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if (capability == PLAYER_FLAGS) { return optional.cast(); }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerFlags().saveNbtData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerFlags().loadNbtData(nbt);
    }
}
