package com.alex.guzhenren.datagen.providers.loot;

import com.alex.guzhenren.block.ModBlocks;
import com.alex.guzhenren.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // DROP SELF
        dropSelf(ModBlocks.PRIMEVAL_STONE_BLOCK.get());

        // CUSTOM ORE DROP
        add(ModBlocks.PRIMEVAL_STONE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.PRIMEVAL_STONE_ORE.get(), ModItems.PRIMEVAL_STONE.get()));
        add(ModBlocks.DEEPSLATE_PRIMEVAL_STONE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_PRIMEVAL_STONE_ORE.get(), ModItems.PRIMEVAL_STONE.get()));
        add(ModBlocks.END_STONE_PRIMEVAL_STONE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.END_STONE_PRIMEVAL_STONE_ORE.get(), ModItems.PRIMEVAL_STONE.get()));
        add(ModBlocks.NETHER_PRIMEVAL_STONE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.NETHER_PRIMEVAL_STONE_ORE.get(), ModItems.PRIMEVAL_STONE.get()));

        // CUSTOM FLOWER
        dropSelf(ModBlocks.MOON_ORCHID.get());
        add(ModBlocks.POTTED_MOON_ORCHID.get(), createPotFlowerItemTable(ModBlocks.MOON_ORCHID.get()));
    }

    protected LootTable.Builder createCopperLikeOreDrops(Block block, Item item) {
        return createSilkTouchDispatchTable(block, applyExplosionDecay(block,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(7.0F, 14.0F)))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
