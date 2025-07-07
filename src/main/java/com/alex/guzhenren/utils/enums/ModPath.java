package com.alex.guzhenren.utils.enums;

public enum ModPath {

    HEAVEN("guzhenren.path.heaven"),
    SPACE("guzhenren.path.space"),
    TIME("guzhenren.path.time"),

    HUMAN("guzhenren.path.human"),
    RULE("guzhenren.path.rule"),
    QI("guzhenren.path.qi"),
    FIRE("guzhenren.path.fire"),
    WATER("guzhenren.path.water"),
    ICE_SNOW("guzhenren.path.ice_snow"),
    WOOD("guzhenren.path.wood"),
    EARTH("guzhenren.path.earth"),
    METAL("guzhenren.path.metal"),
    LIGHT("guzhenren.path.light"),
    DARK("guzhenren.path.dark"),
    CLOUD("guzhenren.path.cloud"),
    WIND("guzhenren.path.wind"),
    SOUND("guzhenren.path.sound"),
    MOON("guzhenren.path.moon"),

    STRENGTH("guzhenren.path.power"),
    WISDOM("guzhenren.path.wisdom"),
    ENSLAVEMENT("guzhenren.path.enslavement"),
    LIGHTNING("guzhenren.path.lightning"),
    INFORMATION("guzhenren.path.information"),
    REFINEMENT("guzhenren.path.refinement"),
    TRANSFORMATION("guzhenren.path.transformation"),
    BLADE("guzhenren.path.blade"),
    LUCK("guzhenren.path.luck"),
    THEFT("guzhenren.path.theft"),
    DREAM("guzhenren.path.dream"),

    BLOOD("guzhenren.path.blood"),
    POISON("guzhenren.path.poison"),
    SOUL("guzhenren.path.soul"),
    KILLING("guzhenren.path.killing");

    private final String nameKey;

    ModPath(String key) {
        this.nameKey = key;
    }

    // GETTERS
    public String getNameKey() { return nameKey; }
}
