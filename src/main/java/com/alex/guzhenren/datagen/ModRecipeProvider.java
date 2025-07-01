package com.alex.guzhenren.datagen;

import com.alex.guzhenren.block.ModBlocks;
import com.alex.guzhenren.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    private static final List<ItemLike> PRIMEVAL_STONE_SMELTABLES = List.of(
            ModBlocks.PRIMEVAL_STONE_ORE.get(),
            ModBlocks.DEEPSLATE_PRIMEVAL_STONE_ORE.get(),
            ModBlocks.END_STONE_PRIMEVAL_STONE_ORE.get(),
            ModBlocks.NETHER_PRIMEVAL_STONE_ORE.get()
    );

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {

        oreSmelting(consumer, PRIMEVAL_STONE_SMELTABLES, RecipeCategory.MISC, ModItems.PRIMEVAL_STONE.get(),
                0.25f, 200, "primeval_stone");
        oreBlasting(consumer, PRIMEVAL_STONE_SMELTABLES, RecipeCategory.MISC, ModItems.PRIMEVAL_STONE.get(),
                0.25f, 100, "primeval_stone");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PRIMEVAL_STONE.get(), 9)
                .requires(ModBlocks.PRIMEVAL_STONE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.PRIMEVAL_STONE_BLOCK.get()), has(ModBlocks.PRIMEVAL_STONE_BLOCK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PRIMEVAL_STONE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.PRIMEVAL_STONE.get())
                .unlockedBy(getHasName(ModItems.PRIMEVAL_STONE.get()), has(ModItems.PRIMEVAL_STONE.get()))
                .save(consumer);
    }

    protected static void oreSmelting(
            @NotNull Consumer<FinishedRecipe> consumer,
            List<ItemLike> itemList,
            @NotNull RecipeCategory category,
            @NotNull ItemLike result,
            float experience, int cookingTime, @NotNull String group) {
        oreCooking(consumer, RecipeSerializer.SMELTING_RECIPE,
                itemList, category, result, experience, cookingTime, group, "_from_smelting");
    }

    protected static void oreBlasting(
            @NotNull Consumer<FinishedRecipe> consumer,
            List<ItemLike> itemList,
            @NotNull RecipeCategory category,
            @NotNull ItemLike result,
            float experience, int cookingTime, @NotNull String group) {
        oreCooking(consumer, RecipeSerializer.BLASTING_RECIPE,
                itemList, category, result, experience, cookingTime, group, "_from_blasting");
    }
}
