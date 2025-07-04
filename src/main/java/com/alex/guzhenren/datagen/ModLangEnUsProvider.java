package com.alex.guzhenren.datagen;

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
        addItem(ModItems.APERTURE, "Aperture");
        addItem(ModItems.HUMAN_HEART, "Human Heart");
        addItem(ModItems.PRIMEVAL_STONE, "Primeval Stone");

        addItem(ModItems.HOPE, "Hope Gu");

        addBlock(ModBlocks.PRIMEVAL_STONE_BLOCK, "Primeval Stone Block");
        addBlock(ModBlocks.PRIMEVAL_STONE_ORE, "Primeval Stone Ore");
        addBlock(ModBlocks.DEEPSLATE_PRIMEVAL_STONE_ORE, "Despslate Primeval Stone Ore");
        addBlock(ModBlocks.NETHER_PRIMEVAL_STONE_ORE, "Nether Primeval Stone Ore");
        addBlock(ModBlocks.END_STONE_PRIMEVAL_STONE_ORE, "End Stone Primeval Stone Ore");

        addBlock(ModBlocks.MOON_ORCHID, "Moon Orchid");
        addBlock(ModBlocks.POTTED_MOON_ORCHID, "Potted Moon Orchid");

        add("creative_tab.gu_material_tab", "Gu Materials");
        add("creative_tab.gu_world_tab", "Gu World Block");
        add("creative_tab.mortal_gu_tab", "Mortal Gu");

        add("guzhenren.text.lifespan", "Lifespan");
        add("guzhenren.text.screen_title", "TITLE");
        add("guzhenren.text.rank", "Rank");

        add(ModTalent.A.getNameKey(), "A");
        add(ModTalent.B.getNameKey(), "B");
        add(ModTalent.C.getNameKey(), "C");
        add(ModTalent.D.getNameKey(), "D");
        add(ModTalent.TEN_EXTREME.getNameKey(), "Ten Extreme Physique");
        add(ModTalent.NULL.getNameKey(), "Ordinary");

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

        add(ModStage.NULL.getNameKey(), "");
        add(ModStage.INIT.getNameKey(), "Init");
        add(ModStage.MID.getNameKey(), "Mid");
        add(ModStage.UPPER.getNameKey(), "Upper");
        add(ModStage.PEAK.getNameKey(), "Peak");
    }
}
