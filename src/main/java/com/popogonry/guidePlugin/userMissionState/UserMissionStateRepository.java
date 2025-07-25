package com.popogonry.guidePlugin.userMissionState;

import com.popogonry.guidePlugin.GuidePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class UserMissionStateRepository {

    private static final String CONFIG_FILE_NAME = "userMissionState.yml";
    private final String configBasePath;

    private final UserMissionStateDataConfig userMissionStateDataConfig;

    public static HashMap<UUID, Integer> userMissionStateHashMap = new HashMap<>();


    public UserMissionStateRepository() {
        configBasePath = GuidePlugin.getServerInstance().getDataFolder().getAbsolutePath();
        userMissionStateDataConfig = new UserMissionStateDataConfig(configBasePath,CONFIG_FILE_NAME);
    }

    public void reloadConfig() {
        userMissionStateDataConfig.reload();
    }

    public void saveConfig() {
        userMissionStateDataConfig.store();
    }

    public boolean hasUserMissionState(UUID uuid) {
        return userMissionStateDataConfig.hasUserMissionStateData(uuid);
    }

    public void storeUserMissionState(UUID uuid) {
        userMissionStateDataConfig.storeUserMissionStateData(uuid, userMissionStateHashMap.getOrDefault(uuid, 0));
        userMissionStateHashMap.remove(uuid);
    }

    public void saveUserMissionState(UUID uuid) {
        userMissionStateDataConfig.storeUserMissionStateData(uuid, userMissionStateHashMap.getOrDefault(uuid, 0));
    }

    public void loadUserMissionState(UUID uuid) {
        userMissionStateHashMap.put(uuid, userMissionStateDataConfig.loadUserMissionStateData(uuid));
    }

}
