package com.popogonry.guidePlugin.userMissionLevel;

import com.popogonry.guidePlugin.GuidePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class UserMissionLevelRepository {

    private static final String CONFIG_FILE_NAME = "userMissionLevel.yml";
    private final String configBasePath;

    private final UserMissionLevelDataConfig userMissionLevelDataConfig;

    public static HashMap<UUID, Integer> userMissionLevelHashMap = new HashMap<>();


    public UserMissionLevelRepository() {
        configBasePath = GuidePlugin.getServerInstance().getDataFolder().getAbsolutePath();
        userMissionLevelDataConfig = new UserMissionLevelDataConfig(configBasePath,CONFIG_FILE_NAME);
    }

    public void reloadConfig() {
        userMissionLevelDataConfig.reload();
    }

    public void saveConfig() {
        userMissionLevelDataConfig.store();
    }

    public boolean hasUserMissionLevel(UUID uuid) {
        return userMissionLevelDataConfig.hasUserMissionLevelData(uuid);
    }

    public void storeUserMissionLevel(UUID uuid) {
        userMissionLevelDataConfig.storeUserMissionLevelData(uuid, userMissionLevelHashMap.getOrDefault(uuid, 0));
        userMissionLevelHashMap.remove(uuid);
    }

    public void saveUserMissionLevel(UUID uuid) {
        userMissionLevelDataConfig.storeUserMissionLevelData(uuid, userMissionLevelHashMap.getOrDefault(uuid, 0));
    }

    public void loadUserMissionLevel(UUID uuid) {
        userMissionLevelHashMap.put(uuid, userMissionLevelDataConfig.loadUserMissionLevelData(uuid));
    }

}
