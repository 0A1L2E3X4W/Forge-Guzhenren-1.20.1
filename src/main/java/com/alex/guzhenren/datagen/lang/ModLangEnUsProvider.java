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
        addModPathTranslations();
        addExtremePhysiqueTranslations();
        addPathRealmsTranslations();
    }

    private void addGuiHudTextTranslations() {
        add("guzhenren.text.screen_title", "TITLE");

        add("guzhenren.text.essence", "Essence");
        add("guzhenren.text.rank", "Rank");
        add("guzhenren.text.lifespan", "Lifespan");
        add("guzhenren.text.thoughts", "Thoughts");
        add("guzhenren.text.talent", "Talent");
        add("guzhenren.text.physique", "Physique");
        add("guzhenren.text.path", "Path");
        add("guzhenren.text.realm", "Realm");

        add("guzhenren.text.storage", "Storage");

        add("guzhenren.text.refinement_progress", "Refinement Progress");
        add("guzhenren.text.refinement_done", "Refinement Done");

        add("guzhenren.text.button.main_page", "Main");
        add("guzhenren.text.button.attainments_page", "Attainments");
        add("guzhenren.text.button.realms_page", "Realms");
        add("guzhenren.text.button.reset", "Reset");
        add("guzhenren.text.button.confirm", "Confirm");
    }

    private void addModPathTranslations() {
        add(ModPath.HEAVEN.getNameKey(), "Heaven Path");
        add(ModPath.TIME.getNameKey(), "Time Path");
        add(ModPath.SPACE.getNameKey(), "Space Path");
        add(ModPath.HUMAN.getNameKey(), "Human Path");
        add(ModPath.RULE.getNameKey(), "Rule Path");

        add(ModPath.EARTH.getNameKey(), "Earth Path");
        add(ModPath.WATER.getNameKey(), "Water Path");
        add(ModPath.METAL.getNameKey(), "Metal Path");
        add(ModPath.WOOD.getNameKey(), "Wood Path");
        add(ModPath.ICE_SNOW.getNameKey(), "Ice & Snow Path");
        add(ModPath.FIRE.getNameKey(), "Fire Path");
        add(ModPath.DARK.getNameKey(), "Dark Path");
        add(ModPath.LIGHT.getNameKey(), "Light Path");
        add(ModPath.CLOUD.getNameKey(), "Cloud Path");
        add(ModPath.MOON.getNameKey(), "Moon Path");
        add(ModPath.WIND.getNameKey(), "Wind Path");
        add(ModPath.QI.getNameKey(), "QI Path");
        add(ModPath.LIGHTNING.getNameKey(), "Lightning Path");

        add(ModPath.STRENGTH.getNameKey(), "Strength Path");
        add(ModPath.DREAM.getNameKey(), "Dream Path");
        add(ModPath.ENSLAVEMENT.getNameKey(), "Enslavement Path");
        add(ModPath.TRANSFORMATION.getNameKey(), "Transformation Path");
        add(ModPath.INFORMATION.getNameKey(), "Information Path");
        add(ModPath.LUCK.getNameKey(), "Luck Path");
        add(ModPath.REFINEMENT.getNameKey(), "Refinement Path");
        add(ModPath.SOUND.getNameKey(), "Sound Path");
        add(ModPath.THEFT.getNameKey(), "Theft Path");
        add(ModPath.BLADE.getNameKey(), "Blade Path");
        add(ModPath.WISDOM.getNameKey(), "Wisdom Path");

        add(ModPath.KILLING.getNameKey(), "Killing Path");
        add(ModPath.BLOOD.getNameKey(), "Blood Path");
        add(ModPath.POISON.getNameKey(), "Poison Path");
        add(ModPath.SOUL.getNameKey(), "Soul Path");
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

    private void addExtremePhysiqueTranslations() {
        add(ModExtremePhysique.VERDANT_GREAT_SUN.getNameKey(), "Verdant Great Sun");
        add(ModExtremePhysique.DESOLATE_ANCIENT_MOON.getNameKey(), "Desolate Ancient Moon");
        add(ModExtremePhysique.NORTHERN_DARK_ICE_SOUL.getNameKey(), "Northern Dark Ice Soul");
        add(ModExtremePhysique.BOUNDLESS_FOREST_SAMSARA.getNameKey(), "Boundless Forest Samsara");
        add(ModExtremePhysique.BLAZING_GLORY_LIGHTNING_BRILLIANCE.getNameKey(), "Blazing Glory Lightning Brilliance");
        add(ModExtremePhysique.MYRIAD_GOLD_WONDROUS_ESSENCE.getNameKey(), "Myriad Gold Wondrous Essence");
        add(ModExtremePhysique.GREAT_STRENGTH_TRUE_MARTIAL.getNameKey(), "Great Strength True Martial");
        add(ModExtremePhysique.CAREFREE_WISDOM_HEART.getNameKey(), "Carefree Wisdom Heart");
        add(ModExtremePhysique.CENTRAL_EARTH_ESSENCE.getNameKey(), "Central Earth Essence");
        add(ModExtremePhysique.UNIVERSE_GREAT_DERIVATION.getNameKey(), "Universe Great Derivation");
        add(ModExtremePhysique.PURE_DREAM_REALITY_SEEKER.getNameKey(), "Pure Dream Reality Seeker");
        add(ModExtremePhysique.NULL.getNameKey(), "");
    }

    private void addPathRealmsTranslations() {
        add(ModPathRealm.ORDINARY.getNameKey(), "Ordinary");
        add(ModPathRealm.QUASI_MASTER.getNameKey(), "Quasi-Master");
        add(ModPathRealm.MASTER.getNameKey(), "Master");
        add(ModPathRealm.QUASI_GRANDMASTER.getNameKey(), "Quasi-GrandMaster");
        add(ModPathRealm.GRANDMASTER.getNameKey(), "GrandMaster");
        add(ModPathRealm.QUASI_SUPREME_GRANDMASTER.getNameKey(), "Quasi-Supreme-GrandMaster");
        add(ModPathRealm.SUPREME_GRANDMASTER.getNameKey(), "Supreme-GrandMaster");
    }
}
