package net.alex.guzhenren.capability.providers;

import net.alex.guzhenren.capability.PlayerAptitudes;
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

public class PlayerAptitudesProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerAptitudes> PLAYER_APTITUDE = CapabilityManager.get(new CapabilityToken<>() {});

    private PlayerAptitudes playerAptitude;
    private final LazyOptional<PlayerAptitudes> optional = LazyOptional.of(this::createPlayerAptitude);

    private PlayerAptitudes createPlayerAptitude() {
        if (playerAptitude == null) { playerAptitude = new PlayerAptitudes(); }
        return playerAptitude;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if (capability == PLAYER_APTITUDE) { return optional.cast(); }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerAptitude().saveNbtData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerAptitude().loadNbtData(nbt);
    }
}
