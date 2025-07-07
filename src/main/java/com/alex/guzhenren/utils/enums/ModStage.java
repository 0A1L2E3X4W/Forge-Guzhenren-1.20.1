package com.alex.guzhenren.utils.enums;

public enum ModStage {

    NULL("guzhenren.stage.null", 0.00F),
    INIT("guzhenren.stage.init", 0.00F),
    MID("guzhenren.stage.mid", 0.50F),
    UPPER("guzhenren.stage.upper", 1.50F),
    PEAK("guzhenren.stage.peak", 3.00F);

    private final String nameKey;
    private final float essenceModifier;

    ModStage(String key, float modifier) {
        this.nameKey = key; this.essenceModifier = modifier;
    }

    public String getNameKey() { return nameKey; }
    public float getEssenceModifier() { return essenceModifier; }
}
