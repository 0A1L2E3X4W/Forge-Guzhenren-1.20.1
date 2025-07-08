package com.alex.guzhenren.utils.enums;

public enum ModPath {

    HEAVEN("enums.guzhenren.path.heaven"),
    SPACE("enums.guzhenren.path.space"),
    TIME("enums.guzhenren.path.time"),

    HUMAN("enums.guzhenren.path.human"),
    RULE("enums.guzhenren.path.rule"),
    QI("enums.guzhenren.path.qi"),
    FIRE("enums.guzhenren.path.fire"),
    WATER("enums.guzhenren.path.water"),
    ICE_SNOW("enums.guzhenren.path.ice_snow"),
    WOOD("enums.guzhenren.path.wood"),
    EARTH("enums.guzhenren.path.earth"),
    METAL("enums.guzhenren.path.metal"),
    LIGHT("enums.guzhenren.path.light"),
    DARK("enums.guzhenren.path.dark"),
    CLOUD("enums.guzhenren.path.cloud"),
    WIND("enums.guzhenren.path.wind"),
    SOUND("enums.guzhenren.path.sound"),
    MOON("enums.guzhenren.path.moon"),

    STRENGTH("enums.guzhenren.path.power"),
    WISDOM("enums.guzhenren.path.wisdom"),
    ENSLAVEMENT("enums.guzhenren.path.enslavement"),
    LIGHTNING("enums.guzhenren.path.lightning"),
    INFORMATION("enums.guzhenren.path.information"),
    REFINEMENT("enums.guzhenren.path.refinement"),
    TRANSFORMATION("enums.guzhenren.path.transformation"),
    BLADE("enums.guzhenren.path.blade"),
    LUCK("enums.guzhenren.path.luck"),
    THEFT("enums.guzhenren.path.theft"),
    DREAM("enums.guzhenren.path.dream"),

    BLOOD("enums.guzhenren.path.blood"),
    POISON("enums.guzhenren.path.poison"),
    SOUL("enums.guzhenren.path.soul"),
    KILLING("enums.guzhenren.path.killing");

    private final String nameKey;

    ModPath(String key) {
        this.nameKey = key;
    }

    // GETTERS
    public String getNameKey() { return nameKey; }
}
