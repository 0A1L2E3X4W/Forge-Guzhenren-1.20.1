package com.alex.guzhenren.utils.enums;

public enum ModPathRealm {

    ORDINARY("guzhenren.title.ordinary"),
    QUASI_MASTER("guzhenren.title.quasi_master"),
    MASTER("guzhenren.title.master"),
    QUASI_GRANDMASTER("guzhenren.title.quasi_grandmaster"),
    GRANDMASTER("guzhenren.title.grandmaster"),
    QUASI_SUPREME_GRANDMASTER("guzhenren.title.quasi_supreme_grandmaster"),
    SUPREME_GRANDMASTER("guzhenren.title.supreme_grandmaster");

    private final String nameKey;

    ModPathRealm(String key) {
        this.nameKey = key;
    }

    public String getNameKey() { return nameKey; }
}
