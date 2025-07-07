package com.alex.guzhenren.capability;

import com.alex.guzhenren.utils.enums.ModPath;
import com.alex.guzhenren.utils.enums.ModPathRealm;
import net.minecraft.nbt.CompoundTag;

import java.util.EnumMap;

public class PlayerPathData {

    private static final String KEY_PATHS = "guzhenren.player.path";
    private static final String KEY_PATH_ATTAINMENTS = "guzhenren.player.path_attainment";
    private static final String KEY_PATH_REALMS = "guzhenren.player.path_realms";

    private static final int DEFAULT_PATH_ATTAINMENT = 0;
    private static final ModPathRealm DEFAULT_PATH_REALM = ModPathRealm.ORDINARY;

    private final EnumMap<ModPath, Integer> pathAttainments = new EnumMap<>(ModPath.class);
    private final EnumMap<ModPath, ModPathRealm> pathRealms = new EnumMap<>(ModPath.class);

    public PlayerPathData() {
        for (ModPath path : ModPath.values()) {
            setPathAttainment(path, DEFAULT_PATH_ATTAINMENT);
            setPathRealms(path, DEFAULT_PATH_REALM);
        }
    }

    // PATH DATA GETTERS
    public EnumMap<ModPath, Integer> getPathAttainments() {
        return pathAttainments;
    }

    public EnumMap<ModPath, ModPathRealm> getPathRealms() {
        return pathRealms;
    }

    // PATH ATTAINMENT METHODS
    public int getPathAttainment(ModPath path) {
        return pathAttainments.getOrDefault(path, DEFAULT_PATH_ATTAINMENT);
    }

    public void setPathAttainment(ModPath path, int i) {
        pathAttainments.put(path, i);
    }

    public void addPathAttainment(ModPath path, int i) {
        setPathAttainment(path, getPathAttainment(path) + i);
    }

    public void subPathAttainment(ModPath path, int i) {
        setPathAttainment(path, Math.max(getPathAttainment(path) - i, 0));
    }

    // PATH REALM METHODS
    public ModPathRealm getPathRealm(ModPath path) {
        return pathRealms.getOrDefault(path, DEFAULT_PATH_REALM);
    }

    public void setPathRealms(ModPath path, ModPathRealm realm) {
        pathRealms.put(path, realm);
    }

    // COPY FROM METHOD
    public void copyFrom(PlayerPathData source) {
        if (source == null) { return; }
        for (ModPath path : ModPath.values()) {
            setPathAttainment(path, source.getPathAttainment(path));
        }
    }

    // NBT METHODS
    public void saveNbtData(CompoundTag nbt) {
        CompoundTag pathNbt = new CompoundTag();
        CompoundTag pathAttainmentNbt = new CompoundTag();
        CompoundTag pathRealmNbt = new CompoundTag();

        for (ModPath path : ModPath.values()) {
            pathAttainmentNbt.putInt(path.name(), getPathAttainment(path));
            pathRealmNbt.putString(path.name(), getPathRealm(path).name());
        }

        pathNbt.put(KEY_PATH_ATTAINMENTS, pathAttainmentNbt);
        pathNbt.put(KEY_PATH_REALMS, pathRealmNbt);
        nbt.put(KEY_PATHS, pathNbt);
    }

    public void loadNbtData(CompoundTag nbt) {
        if (!nbt.contains(KEY_PATHS)) { return; }
        CompoundTag pathNbt = nbt.getCompound(KEY_PATHS);

        if (pathNbt.contains(KEY_PATH_ATTAINMENTS)) {
            CompoundTag pathAttainmentNbt = pathNbt.getCompound(KEY_PATH_ATTAINMENTS);
            for (ModPath path : ModPath.values()) {
                setPathAttainment(path, pathAttainmentNbt.getInt(path.name()));
            }
        }

        if (pathNbt.contains(KEY_PATH_REALMS)) {
            CompoundTag pathRealmNbt = pathNbt.getCompound(KEY_PATH_REALMS);
            for (ModPath path : ModPath.values()) {
                setPathRealms(path, ModPathRealm.valueOf(pathRealmNbt.getString(path.name())));
            }
        }
    }
}
