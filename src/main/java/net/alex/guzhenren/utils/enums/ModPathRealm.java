package net.alex.guzhenren.utils.enums;

public enum ModPathRealm {

    ORDINARY("enums.guzhenren.realms.ordinary"),
    QUASI_MASTER("enums.guzhenren.realms.quasi_master"),
    MASTER("enums.guzhenren.realms.master"),
    QUASI_GRANDMASTER("enums.guzhenren.realms.quasi_grandmaster"),
    GRANDMASTER("enums.guzhenren.realms.grandmaster"),
    QUASI_SUPREME_GRANDMASTER("enums.guzhenren.realms.quasi_supreme_grandmaster"),
    SUPREME_GRANDMASTER("enums.guzhenren.realms.supreme_grandmaster");

    private final String nameKey;

    ModPathRealm(String key) {
        this.nameKey = key;
    }

    public String getNameKey() { return nameKey; }
}
