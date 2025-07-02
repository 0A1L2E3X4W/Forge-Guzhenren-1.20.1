package com.alex.guzhenren.client.data;

public class ClientEssenceData {

    private static int maxEssence;
    private static float essence;

    // GETTER & SETTER
    public static int getMaxEssence() { return maxEssence; }
    public static void setMaxEssence(int i) { ClientEssenceData.maxEssence = i; }

    public static float getEssence() { return essence; }
    public static void setEssence(float v) { ClientEssenceData.essence = v; }
}
