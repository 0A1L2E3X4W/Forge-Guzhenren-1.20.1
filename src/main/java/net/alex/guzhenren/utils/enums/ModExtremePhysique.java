package net.alex.guzhenren.utils.enums;

import java.util.List;

public enum ModExtremePhysique {

    NULL("enums.guzhenren.extreme_physique.null"),
    VERDANT_GREAT_SUN("enums.guzhenren.extreme_physique.verdant_great_sun"),
    DESOLATE_ANCIENT_MOON("enums.guzhenren.extreme_physique.desolate_ancient_moon"),
    NORTHERN_DARK_ICE_SOUL("enums.guzhenren.extreme_physique.northern_dark_ice_soul"),
    BOUNDLESS_FOREST_SAMSARA("enums.guzhenren.extreme_physique.boundless_forest_samsara"),
    BLAZING_GLORY_LIGHTNING_BRILLIANCE("enums.guzhenren.extreme_physique.blazing_glory_lightning_brilliance"),
    MYRIAD_GOLD_WONDROUS_ESSENCE("enums.guzhenren.extreme_physique.myriad_gold_wondrous_essence"),
    GREAT_STRENGTH_TRUE_MARTIAL("enums.guzhenren.extreme_physique.great_strength_true_martial"),
    CAREFREE_WISDOM_HEART("enums.guzhenren.extreme_physique.carefree_wisdom_heart"),
    UNIVERSE_GREAT_DERIVATION("enums.guzhenren.extreme_physique.universe_great_derivation"),
    CENTRAL_EARTH_ESSENCE("enums.guzhenren.extreme_physique.central_earth_essence"),
    PURE_DREAM_REALITY_SEEKER("enums.guzhenren.extreme_physique.pure_dream_reality_seeker");

    private final String nameKey;

    ModExtremePhysique(String key) {
        this.nameKey = key;
    }

    // GETTERS
    public String getNameKey() { return nameKey; }
    public static List<ModExtremePhysique> getNaturalPhysiques() {
        return List.of(
            VERDANT_GREAT_SUN,
            DESOLATE_ANCIENT_MOON,
            NORTHERN_DARK_ICE_SOUL,
            BOUNDLESS_FOREST_SAMSARA,
            BLAZING_GLORY_LIGHTNING_BRILLIANCE,
            MYRIAD_GOLD_WONDROUS_ESSENCE,
            GREAT_STRENGTH_TRUE_MARTIAL,
            CAREFREE_WISDOM_HEART,
            UNIVERSE_GREAT_DERIVATION,
            CENTRAL_EARTH_ESSENCE
        );
    }
}
