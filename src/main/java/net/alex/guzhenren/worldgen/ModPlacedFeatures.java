package net.alex.guzhenren.worldgen;

import net.alex.guzhenren.GuzhenrenMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> PRIMEVAL_STONE_ORE_PLACED_KEY =
            registerKey("primeval_stone_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_PRIMEVAL_STONE_ORE_PLACED_KEY =
            registerKey("nether_primeval_stone_ore_placed");
    public static final ResourceKey<PlacedFeature> END_PRIMEVAL_STONE_ORE_PLACED_KEY =
            registerKey("end_primeval_stone_ore_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, PRIMEVAL_STONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(
                        ModConfiguredFeatures.OVERWORLD_PRIMEVAL_STONE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, NETHER_PRIMEVAL_STONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(
                        ModConfiguredFeatures.NETHER_PRIMEVAL_STONE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, END_PRIMEVAL_STONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(
                        ModConfiguredFeatures.END_PRIMEVAL_STONE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(GuzhenrenMod.MOD_ID, name));
    }

    private static void register(
            BootstapContext<PlacedFeature> context,
            ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> configuration,
            List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
