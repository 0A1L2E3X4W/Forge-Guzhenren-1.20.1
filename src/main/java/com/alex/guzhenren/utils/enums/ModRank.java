package com.alex.guzhenren.utils.enums;

public enum ModRank {

    ONE(1, "guzhenren.rank.one"),
    TWO(2, "guzhenren.rank.two"),
    THREE(3, "guzhenren.rank.three"),
    FOUR(4, "guzhenren.rank.four"),
    FIVE(5, "guzhenren.rank.five"),
    SIX(6, "guzhenren.rank.six"),
    SEVEN(7, "guzhenren.rank.seven"),
    EIGHT(8, "guzhenren.rank.eight"),
    NINE(9, "guzhenren.rank.nine"),
    TEN(10, "guzhenren.rank.ten"),
    MORTAL(0, "guzhenren.rank.mortal");

    private final int rankNum;
    private final String nameKey;
    ModRank(int num, String key) { this.rankNum = num; this.nameKey = key; }

    public int getRankNum() { return this.rankNum; }
    public String getNameKey() { return this.nameKey; }
}
