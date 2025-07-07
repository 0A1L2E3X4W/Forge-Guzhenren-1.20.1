package com.alex.guzhenren.datagen.lang;

import com.alex.guzhenren.Guzhenren;
import com.alex.guzhenren.block.ModBlocks;
import com.alex.guzhenren.item.ModItems;
import com.alex.guzhenren.utils.enums.*;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLangZhCnProvider extends LanguageProvider {

    public ModLangZhCnProvider(PackOutput output) {
        super(output, Guzhenren.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {

        // GU MATERIALS
        addItem(ModItems.APERTURE, "人窍");
        addItem(ModItems.HUMAN_HEART, "人心");
        addItem(ModItems.PRIMEVAL_STONE, "元石");

        // MORTAL GU
        addItem(ModItems.HOPE_GU, "希望蛊");

        addItem(ModItems.YEAR_LIFESPAN_GU, "一年寿蛊");
        addItem(ModItems.TEN_YEAR_LIFESPAN_GU, "十年寿蛊");

        addItem(ModItems.PRIMEVAL_ELDER_GU_3, "三转元老蛊");
        addItem(ModItems.PRIMEVAL_ELDER_GU_4, "四转元老蛊");

        // FOOD
        addItem(ModItems.LIQUOR, "酒");

        // BLOCKS
        addBlock(ModBlocks.PRIMEVAL_STONE_BLOCK, "元石块");
        addBlock(ModBlocks.PRIMEVAL_STONE_ORE, "元石矿");
        addBlock(ModBlocks.DEEPSLATE_PRIMEVAL_STONE_ORE, "深层元石矿");
        addBlock(ModBlocks.NETHER_PRIMEVAL_STONE_ORE, "下界元石矿");
        addBlock(ModBlocks.END_STONE_PRIMEVAL_STONE_ORE, "末地元石矿");

        // PLANTS
        addBlock(ModBlocks.MOON_ORCHID, "月兰花");
        addBlock(ModBlocks.POTTED_MOON_ORCHID, "月兰花盆栽");

        // TOOLTIPS
        add("tooltip.guzhenren.hope", "开窍!!");
        add("tooltip.guzhenren.primeval_stone", "蛊界凡人的通用货币，可以为蛊师提供真元");

        addCreateTabTranslations();
        addGuiHudTextTranslations();
        addModTalentTranslations();
        addModRankTranslations();
        addModStageTranslations();
        addModPathTranslations();
        addExtremePhysiqueTranslations();
    }

    private void addGuiHudTextTranslations() {
        add("guzhenren.text.screen_title", "TITLE");
        add("guzhenren.text.rank", "修为");
        add("guzhenren.text.essence", "真元");
        add("guzhenren.text.lifespan", "寿命");
        add("guzhenren.text.thoughts", "念头");
        add("guzhenren.text.talent", "资质");
        add("guzhenren.text.storage", "元石存储");
        add("guzhenren.text.refinement_progress", "炼化进度");
        add("guzhenren.text.refinement_done", "已炼化");
    }

    private void addModPathTranslations() {
        add(ModPath.HEAVEN.getNameKey(), "天道");
        add(ModPath.TIME.getNameKey(), "宙道");
        add(ModPath.SPACE.getNameKey(), "宇道");
        add(ModPath.HUMAN.getNameKey(), "人道");
        add(ModPath.RULE.getNameKey(), "律道");

        add(ModPath.EARTH.getNameKey(), "土道");
        add(ModPath.WATER.getNameKey(), "水道");
        add(ModPath.METAL.getNameKey(), "金道");
        add(ModPath.WOOD.getNameKey(), "木道");
        add(ModPath.ICE_SNOW.getNameKey(), "冰雪道");
        add(ModPath.FIRE.getNameKey(), "炎道");
        add(ModPath.DARK.getNameKey(), "暗道");
        add(ModPath.LIGHT.getNameKey(), "光道");
        add(ModPath.CLOUD.getNameKey(), "云道");
        add(ModPath.MOON.getNameKey(), "月道");
        add(ModPath.WIND.getNameKey(), "风道");
        add(ModPath.QI.getNameKey(), "气道");
        add(ModPath.LIGHTNING.getNameKey(), "雷道");

        add(ModPath.STRENGTH.getNameKey(), "力道");
        add(ModPath.DREAM.getNameKey(), "梦道");
        add(ModPath.ENSLAVEMENT.getNameKey(), "奴道");
        add(ModPath.TRANSFORMATION.getNameKey(), "变化道");
        add(ModPath.INFORMATION.getNameKey(), "信道");
        add(ModPath.LUCK.getNameKey(), "运道");
        add(ModPath.REFINEMENT.getNameKey(), "炼道");
        add(ModPath.SOUND.getNameKey(), "音道");
        add(ModPath.THEFT.getNameKey(), "偷道");
        add(ModPath.BLADE.getNameKey(), "刃道");
        add(ModPath.WISDOM.getNameKey(), "智道");

        add(ModPath.KILLING.getNameKey(), "杀道");
        add(ModPath.BLOOD.getNameKey(), "血道");
        add(ModPath.POISON.getNameKey(), "毒道");
        add(ModPath.SOUL.getNameKey(), "魂道");
    }

    private void addCreateTabTranslations() {
        add("creative_tab.gu_material_tab", "蛊材类");
        add("creative_tab.gu_world_tab", "蛊界方块");
        add("creative_tab.mortal_gu_tab", "凡蛊");
    }

    private void addModTalentTranslations() {
        add(ModTalent.A.getNameKey(), "甲等");
        add(ModTalent.B.getNameKey(), "乙等");
        add(ModTalent.C.getNameKey(), "丙等");
        add(ModTalent.D.getNameKey(), "丁等");
        add(ModTalent.TEN_EXTREME.getNameKey(), "十绝体");
        add(ModTalent.NULL.getNameKey(), "无");
    }

    private void addModStageTranslations() {
        add(ModStage.NULL.getNameKey(), "");
        add(ModStage.INIT.getNameKey(), "初阶");
        add(ModStage.MID.getNameKey(), "中阶");
        add(ModStage.UPPER.getNameKey(), "高阶");
        add(ModStage.PEAK.getNameKey(), "巅峰");
    }

    private void addModRankTranslations() {
        add(ModRank.MORTAL.getNameKey(), "凡");
        add(ModRank.ONE.getNameKey(), "一转");
        add(ModRank.TWO.getNameKey(), "二转");
        add(ModRank.THREE.getNameKey(), "三转");
        add(ModRank.FOUR.getNameKey(), "四转");
        add(ModRank.FIVE.getNameKey(), "五转");
        add(ModRank.SIX.getNameKey(), "六转");
        add(ModRank.SEVEN.getNameKey(), "七转");
        add(ModRank.EIGHT.getNameKey(), "八转");
        add(ModRank.NINE.getNameKey(), "九转");
    }

    private void addExtremePhysiqueTranslations() {
        add(ModExtremePhysique.VERDANT_GREAT_SUN.getNameKey(), "太日阳莽体");
        add(ModExtremePhysique.DESOLATE_ANCIENT_MOON.getNameKey(), "古月阴荒体");
        add(ModExtremePhysique.NORTHERN_DARK_ICE_SOUL.getNameKey(), "北冥冰魄体");
        add(ModExtremePhysique.BOUNDLESS_FOREST_SAMSARA.getNameKey(), "森海轮回体");
        add(ModExtremePhysique.BLAZING_GLORY_LIGHTNING_BRILLIANCE.getNameKey(), "炎煌雷泽体");
        add(ModExtremePhysique.MYRIAD_GOLD_WONDROUS_ESSENCE.getNameKey(), "万金妙华体");
        add(ModExtremePhysique.GREAT_STRENGTH_TRUE_MARTIAL.getNameKey(), "大力真武体");
        add(ModExtremePhysique.CAREFREE_WISDOM_HEART.getNameKey(), "逍遥智心体");
        add(ModExtremePhysique.CENTRAL_EARTH_ESSENCE.getNameKey(), "厚土元央体");
        add(ModExtremePhysique.UNIVERSE_GREAT_DERIVATION.getNameKey(), "宇宙大衍体");
        add(ModExtremePhysique.PURE_DREAM_REALITY_SEEKER.getNameKey(), "纯梦求真体");
        add(ModExtremePhysique.NULL.getNameKey(), "");
    }
}
