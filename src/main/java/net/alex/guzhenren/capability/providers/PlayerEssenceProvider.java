package net.alex.guzhenren.capability.providers;

import net.alex.guzhenren.capability.PlayerEssence;
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

public class PlayerEssenceProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerEssence> PLAYER_ESSENCE = CapabilityManager.get(new CapabilityToken<>() {});
    
    private PlayerEssence playerEssence;
    private final LazyOptional<PlayerEssence> optional = LazyOptional.of(this::createPlayerEssence);

    private PlayerEssence createPlayerEssence() {
        if (playerEssence == null) { playerEssence = new PlayerEssence(); }
        return playerEssence;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if (capability == PLAYER_ESSENCE) { return optional.cast(); }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerEssence().saveNbtData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerEssence().loadNbtData(nbt);
    }
}
