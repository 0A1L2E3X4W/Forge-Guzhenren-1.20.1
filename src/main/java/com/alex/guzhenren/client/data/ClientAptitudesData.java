package com.alex.guzhenren.client.data;

import com.alex.guzhenren.utils.enums.*;

public class ClientAptitudesData {

    private static float lifespan;
    private static float thoughts;

    private static int soul;
    private static int luck;
    private static int moral;

    private static ModRank rank;
    private static ModStage stage;
    private static ModTalent talent;

    // GETTER & SETTER
    public static float getLifespan() { return lifespan; }
    public static void setLifespan(float val) { ClientAptitudesData.lifespan = val; }

    public static float getThoughts() { return thoughts; }
    public static void setThoughts(float val) { ClientAptitudesData.thoughts = val; }

    public static int getSoul() { return soul; }
    public static void setSoul(int val) { ClientAptitudesData.soul = val; }

    public static int getLuck() { return luck; }
    public static void setLuck(int val) { ClientAptitudesData.luck = val; }

    public static int getMoral() { return moral; }
    public static void setMoral(int val) { ClientAptitudesData.moral = val; }

    public static ModRank getRank() { return rank; }
    public static void setRank(ModRank val) { ClientAptitudesData.rank = val; }

    public static ModStage getStage() { return stage; }
    public static void setStage(ModStage val) { ClientAptitudesData.stage = val; }

    public static ModTalent getTalent() { return talent; }
    public static void setTalent(ModTalent val) { ClientAptitudesData.talent = val; }
}
