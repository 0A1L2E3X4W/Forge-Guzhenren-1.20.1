package net.alex.guzhenren.utils.enums;

public enum ModTalent {

    A("enums.guzhenren.talent.a"),
    B("enums.guzhenren.talent.b"),
    C("enums.guzhenren.talent.c"),
    D("enums.guzhenren.talent.d"),
    TEN_EXTREME("enums.guzhenren.talent.ten_extreme"),
    NULL("enums.guzhenren.talent.null");

    private final String nameKey;
    ModTalent(String key) { this.nameKey = key; }

    public String getNameKey() { return this.nameKey; }
}
