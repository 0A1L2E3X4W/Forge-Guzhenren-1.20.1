package net.alex.guzhenren.worldgen;

import net.alex.guzhenren.GuzhenrenMod;
import net.alex.guzhenren.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_PRIMEVAL_STONE_ORE_KEY = registerKey("primeval_stone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_PRIMEVAL_STONE_ORE_KEY = registerKey("nether_primeval_stone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_PRIMEVAL_STONE_ORE_KEY = registerKey("end_primeval_stone_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceable = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceable = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldSapphireOres = List.of(OreConfiguration.target(stoneReplaceable,
                        ModBlocks.PRIMEVAL_STONE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_PRIMEVAL_STONE_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_PRIMEVAL_STONE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSapphireOres, 12));

        register(context, NETHER_PRIMEVAL_STONE_ORE_KEY, Feature.ORE, new OreConfiguration(netherReplaceable,
                ModBlocks.NETHER_PRIMEVAL_STONE_ORE.get().defaultBlockState(), 12));

        register(context, END_PRIMEVAL_STONE_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceable,
                ModBlocks.END_STONE_PRIMEVAL_STONE_ORE.get().defaultBlockState(), 12));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(GuzhenrenMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
