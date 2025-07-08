package com.alex.guzhenren.utils.enums;

public enum ModStage {

    NULL("enums.guzhenren.stage.null", 0.00F),
    INIT("enums.guzhenren.stage.init", 0.00F),
    MID("enums.guzhenren.stage.mid", 0.50F),
    UPPER("enums.guzhenren.stage.upper", 1.50F),
    PEAK("enums.guzhenren.stage.peak", 3.00F);

    private final String nameKey;
    private final float essenceModifier;

    ModStage(String key, float modifier) {
        this.nameKey = key; this.essenceModifier = modifier;
    }

    public String getNameKey() { return nameKey; }
    public float getEssenceModifier() { return essenceModifier; }
}
