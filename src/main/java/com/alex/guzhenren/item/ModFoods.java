package com.alex.guzhenren.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    // ========== FOOD HAS EFFECTS ==========
    public static final FoodProperties LIQUOR = new FoodProperties.Builder().nutrition(2)
            .saturationMod(0.2F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 15*20, 2), 0.5F)
            .alwaysEat()
            .build();
    public static final FoodProperties HUMAN_HEART = new FoodProperties.Builder().nutrition(4)
            .saturationMod(0.2F)
            .build();
}
