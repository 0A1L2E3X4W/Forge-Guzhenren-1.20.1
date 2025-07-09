package com.alex.guzhenren.worldgen.dimension;

import com.alex.guzhenren.Guzhenren;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

public class ModDimensions {

    public static final ResourceKey<LevelStem> YELLOW_HEAVEN_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(Guzhenren.MOD_ID, "yellow_heaven"));
    public static final ResourceKey<Level> YELLOW_HEAVEN_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(Guzhenren.MOD_ID, "yellow_heaven"));
    public static final ResourceKey<DimensionType> YELLOW_HEAVEN_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(Guzhenren.MOD_ID, "yellow_heaven_type"));
}
