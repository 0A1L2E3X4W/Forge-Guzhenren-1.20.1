package com.alex.guzhenren.client.data;

import com.alex.guzhenren.utils.enums.ModPath;
import com.alex.guzhenren.utils.enums.ModPathRealm;

import java.util.EnumMap;

public class ClientPathData {

    private static EnumMap<ModPath, Integer> pathAttainments = new EnumMap<>(ModPath.class);
    private static EnumMap<ModPath, ModPathRealm> pathRealms = new EnumMap<>(ModPath.class);

    // GETTER & SETTER
    public static int getPathAttainment(ModPath path) {
        return pathAttainments.getOrDefault(path, 0);
    }

    public static void setPathAttainments(EnumMap<ModPath, Integer> attainments) {
        ClientPathData.pathAttainments = attainments;
    }

    public static ModPathRealm getPathRealm(ModPath path) {
        return pathRealms.getOrDefault(path, ModPathRealm.ORDINARY);
    }

    public static void setPathRealms(EnumMap<ModPath, ModPathRealm> realms) {
        ClientPathData.pathRealms = realms;
    }
}
