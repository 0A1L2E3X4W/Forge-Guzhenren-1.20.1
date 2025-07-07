package com.alex.guzhenren.utils.enums;

public enum ModExtremePhysique {

    NULL("guzhenren.extreme_physique.null"),
    VERDANT_GREAT_SUN("guzhenren.extreme_physique.verdant_great_sun"),
    DESOLATE_ANCIENT_MOON("guzhenren.extreme_physique.desolate_ancient_moon"),
    NORTHERN_DARK_ICE_SOUL("guzhenren.extreme_physique.northern_dark_ice_soul"),
    BOUNDLESS_FOREST_SAMSARA("guzhenren.extreme_physique.boundless_forest_samsara"),
    BLAZING_GLORY_LIGHTNING_BRILLIANCE("guzhenren.extreme_physique.blazing_glory_lightning_brilliance"),
    MYRIAD_GOLD_WONDROUS_ESSENCE("guzhenren.extreme_physique.myriad_gold_wondrous_essence"),
    GREAT_STRENGTH_TRUE_MARTIAL("guzhenren.extreme_physique.great_strength_true_martial"),
    CAREFREE_WISDOM_HEART("guzhenren.extreme_physique.carefree_wisdom_heart"),
    UNIVERSE_GREAT_DERIVATION("guzhenren.extreme_physique.universe_great_derivation"),
    CENTRAL_EARTH_ESSENCE("guzhenren.extreme_physique.central_earth_essence"),
    PURE_DREAM_REALITY_SEEKER("guzhenren.extreme_physique.pure_dream_reality_seeker");

    private final String nameKey;

    ModExtremePhysique(String key) {
        this.nameKey = key;
    }

    // GETTERS
    public String getNameKey() { return nameKey; }
}
