package com.alex.guzhenren.client.data;

public class ClientEssenceData {

    private static int maxEssence;
    private static float essence;

    // GETTER & SETTER
    public static void setMaxEssence(int val) { ClientEssenceData.maxEssence = val; }
    public static int getMaxEssence() { return maxEssence; }

    public static void setEssence(float val) { ClientEssenceData.essence = val; }
    public static float getEssence() { return essence; }
}
