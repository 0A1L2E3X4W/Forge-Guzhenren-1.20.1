package com.alex.guzhenren.datagen.lang;

import com.alex.guzhenren.Guzhenren;
import com.alex.guzhenren.block.ModBlocks;
import com.alex.guzhenren.item.ModItems;
import com.alex.guzhenren.utils.enums.*;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLangEnUsProvider extends LanguageProvider {

    public ModLangEnUsProvider(PackOutput output) {
        super(output, Guzhenren.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        // GU MATERIALS
        addItem(ModItems.APERTURE, "Aperture");
        addItem(ModItems.HUMAN_HEART, "Human Heart");
        addItem(ModItems.PRIMEVAL_STONE, "Primeval Stone");

        // MORTAL GU
        addItem(ModItems.HOPE_GU, "Hope Gu");
        addItem(ModItems.YEAR_LIFESPAN_GU, "Year Lifespan Gu");
        addItem(ModItems.TEN_YEAR_LIFESPAN_GU, "Ten Years Lifespan Gu");
        addItem(ModItems.PRIMEVAL_ELDER_GU_3, "Primeval Elder Gu - Rank 3");
        addItem(ModItems.PRIMEVAL_ELDER_GU_4, "Primeval Elder Gu - Rank 4");

        // FOOD
        addItem(ModItems.LIQUOR, "Liquor");

        // BLOCKS
        addBlock(ModBlocks.PRIMEVAL_STONE_BLOCK, "Primeval Stone Block");
        addBlock(ModBlocks.PRIMEVAL_STONE_ORE, "Primeval Stone Ore");
        addBlock(ModBlocks.DEEPSLATE_PRIMEVAL_STONE_ORE, "Despslate Primeval Stone Ore");
        addBlock(ModBlocks.NETHER_PRIMEVAL_STONE_ORE, "Nether Primeval Stone Ore");
        addBlock(ModBlocks.END_STONE_PRIMEVAL_STONE_ORE, "End Stone Primeval Stone Ore");

        // PLANTS
        addBlock(ModBlocks.MOON_ORCHID, "Moon Orchid");
        addBlock(ModBlocks.POTTED_MOON_ORCHID, "Potted Moon Orchid");

        // TOOLTIPS
        add("tooltip.guzhenren.hope", "Awaken Aperture!!");
        add("tooltip.guzhenren.primeval_stone", "The common currency for mortals in the GU world," +
                "\nwhich can provide primeval essence to the GU Master.");

        addCreateTabTranslations();
        addGuiHudTextTranslations();
        addModTalentTranslations();
        addModRankTranslations();
        addModStageTranslations();
    }

    private void addGuiHudTextTranslations() {
        add("guzhenren.text.screen_title", "TITLE");
        add("guzhenren.text.essence", "Essence");
        add("guzhenren.text.rank", "Rank");
        add("guzhenren.text.lifespan", "Lifespan");
        add("guzhenren.text.thoughts", "Thoughts");
        add("guzhenren.text.storage", "Storage");
        add("guzhenren.text.refinement_progress", "Refinement Progress");
        add("guzhenren.text.refinement_done", "Refinement Done");
    }

    private void addModStageTranslations() {
        add(ModStage.NULL.getNameKey(), "");
        add(ModStage.INIT.getNameKey(), "Init");
        add(ModStage.MID.getNameKey(), "Mid");
        add(ModStage.UPPER.getNameKey(), "Upper");
        add(ModStage.PEAK.getNameKey(), "Peak");
    }

    private void addModRankTranslations() {
        add(ModRank.MORTAL.getNameKey(), "Mortal");
        add(ModRank.ONE.getNameKey(), "Rank One");
        add(ModRank.TWO.getNameKey(), "Rank Two");
        add(ModRank.THREE.getNameKey(), "Rank Three");
        add(ModRank.FOUR.getNameKey(), "Rank Four");
        add(ModRank.FIVE.getNameKey(), "Rank Five");
        add(ModRank.SIX.getNameKey(), "Rank Six");
        add(ModRank.SEVEN.getNameKey(), "Rank Seven");
        add(ModRank.EIGHT.getNameKey(), "Rank Eight");
        add(ModRank.NINE.getNameKey(), "Rank Nine");
    }

    private void addModTalentTranslations() {
        add(ModTalent.A.getNameKey(), "A");
        add(ModTalent.B.getNameKey(), "B");
        add(ModTalent.C.getNameKey(), "C");
        add(ModTalent.D.getNameKey(), "D");
        add(ModTalent.TEN_EXTREME.getNameKey(), "Ten Extreme Physique");
        add(ModTalent.NULL.getNameKey(), "Ordinary");
    }

    private void addCreateTabTranslations() {
        add("creative_tab.gu_material_tab", "Gu Materials");
        add("creative_tab.gu_world_tab", "Gu World Block");
        add("creative_tab.mortal_gu_tab", "Mortal Gu");
    }
}
