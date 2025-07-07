package com.alex.guzhenren.utils.enums;

public enum ModRank {

    ONE(1, "guzhenren.rank.one", 1.00F),
    TWO(2, "guzhenren.rank.two", 5.00F),
    THREE(3, "guzhenren.rank.three", 9.00F),
    FOUR(4, "guzhenren.rank.four", 13.00F),
    FIVE(5, "guzhenren.rank.five", 17.00F),
    SIX(6, "guzhenren.rank.six", 0.00F),
    SEVEN(7, "guzhenren.rank.seven", 0.00F),
    EIGHT(8, "guzhenren.rank.eight", 0.00F),
    NINE(9, "guzhenren.rank.nine", 0.00F),
    TEN(10, "guzhenren.rank.ten", 0.00F),
    MORTAL(0, "guzhenren.rank.mortal", 0.00F);

    private final int rankNum;
    private final String nameKey;
    private final float essenceModifier;

    ModRank(int num, String key, float modifier) {
        this.rankNum = num; this.nameKey = key; this.essenceModifier = modifier;
    }

    public int getRankNum() { return rankNum; }
    public String getNameKey() { return nameKey; }
    public float getEssenceModifier() { return essenceModifier; }
}
