package com.alex.guzhenren.utils.enums;

public enum ModStage {

    NULL("guzhenren.stage.null"),
    INIT("guzhenren.stage.init"),
    MID("guzhenren.stage.mid"),
    UPPER("guzhenren.stage.upper"),
    PEAK("guzhenren.stage.peak");

    private final String nameKey;
    ModStage(String key) { this.nameKey = key; }

    public String getNameKey() { return this.nameKey; }
}
