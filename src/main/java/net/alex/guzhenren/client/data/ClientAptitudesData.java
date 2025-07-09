package net.alex.guzhenren.client.data;

import com.alex.guzhenren.utils.enums.*;
import net.alex.guzhenren.utils.enums.ModExtremePhysique;
import net.alex.guzhenren.utils.enums.ModRank;
import net.alex.guzhenren.utils.enums.ModStage;
import net.alex.guzhenren.utils.enums.ModTalent;

public class ClientAptitudesData {

    private static float lifespan;
    private static float thoughts;

    private static int soul;
    private static int luck;
    private static int moral;

    private static ModRank rank;
    private static ModStage stage;
    private static ModTalent talent;

    private static ModExtremePhysique extremePhysique;

    // GETTER & SETTER
    public static float getLifespan() { return lifespan; }
    public static void setLifespan(float v) { ClientAptitudesData.lifespan = v; }

    public static float getThoughts() { return thoughts; }
    public static void setThoughts(float v) { ClientAptitudesData.thoughts = v; }

    public static int getSoul() { return soul; }
    public static void setSoul(int i) { ClientAptitudesData.soul = i; }

    public static int getLuck() { return luck; }
    public static void setLuck(int i) { ClientAptitudesData.luck = i; }

    public static int getMoral() { return moral; }
    public static void setMoral(int i) { ClientAptitudesData.moral = i; }

    public static ModRank getRank() { return rank; }
    public static void setRank(ModRank rank) { ClientAptitudesData.rank = rank; }

    public static ModStage getStage() { return stage; }
    public static void setStage(ModStage stage) { ClientAptitudesData.stage = stage; }

    public static ModTalent getTalent() { return talent; }
    public static void setTalent(ModTalent talent) { ClientAptitudesData.talent = talent; }

    public static ModExtremePhysique getExtremePhysique() { return extremePhysique; }
    public static void setExtremePhysique(ModExtremePhysique extremePhysique) { ClientAptitudesData.extremePhysique = extremePhysique; }
}
