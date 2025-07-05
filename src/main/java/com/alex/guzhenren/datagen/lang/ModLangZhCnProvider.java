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
        addItem(ModItems.HOPE, "希望蛊");
        addItem(ModItems.YEAR_LIFESPAN_GU, "一年寿蛊");
        addItem(ModItems.TEN_YEAR_LIFESPAN_GU, "十年寿蛊");

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
    }

    private void addCreateTabTranslations() {
        add("creative_tab.gu_material_tab", "蛊材类");
        add("creative_tab.gu_world_tab", "蛊界方块");
        add("creative_tab.mortal_gu_tab", "凡蛊");
    }

    private void addGuiHudTextTranslations() {
        add("guzhenren.text.screen_title", "TITLE");
        add("guzhenren.text.rank", "修为");
        add("guzhenren.text.essence", "真元");
        add("guzhenren.text.lifespan", "寿命");
        add("guzhenren.text.thoughts", "念头");
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
        add(ModRank.MORTAL.getNameKey(), "凡人");
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
}
