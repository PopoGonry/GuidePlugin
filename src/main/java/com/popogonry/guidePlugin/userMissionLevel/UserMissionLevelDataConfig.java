package com.popogonry.guidePlugin.userMissionLevel;

import com.popogonry.guidePlugin.util.Config;

import java.util.UUID;

public class UserMissionLevelDataConfig extends Config {
    public UserMissionLevelDataConfig(String basePath, String fileName) {
        super(basePath, fileName);
        loadDefaults();
    }

    public void storeUserMissionLevelData(UUID uuid, int level) {
        getConfig().set(uuid.toString(), level);
        super.store();
    }

    public Integer loadUserMissionLevelData(UUID uuid) {
        return getConfig().getInt(uuid.toString());
    }

    public boolean hasUserMissionLevelData(UUID uuid) {
        return getConfig().contains(uuid.toString());
    }

    @Override
    public void loadDefaults() {

    }

    @Override
    public void applySettings() {
        getConfig().options().copyDefaults(true);
    }
}
