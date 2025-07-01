package com.alex.guzhenren.utils.enums;

public enum ModTalent {

    A("guzhenren.talent.a"),
    B("guzhenren.talent.b"),
    C("guzhenren.talent.c"),
    D("guzhenren.talent.d"),
    TEN_EXTREME("guzhenren.talent.ten_extreme"),
    NULL("guzhenren.talent.null");

    private final String nameKey;
    ModTalent(String key) { this.nameKey = key; }

    public String getNameKey() { return this.nameKey; }
    public static ModTalent fromNameKey(String key) {
        for (ModTalent talent : values()) { if (talent.nameKey.equals(key)) { return talent; } }
        return NULL;
    }
}
