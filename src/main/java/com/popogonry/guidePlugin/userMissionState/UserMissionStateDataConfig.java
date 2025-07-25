package com.popogonry.guidePlugin.userMissionState;

import com.popogonry.guidePlugin.util.Config;

import java.util.UUID;

public class UserMissionStateDataConfig extends Config {
    public UserMissionStateDataConfig(String basePath, String fileName) {
        super(basePath, fileName);
        loadDefaults();
    }

    public void storeUserMissionStateData(UUID uuid, int state) {
        getConfig().set(uuid.toString(), state);
        super.store();
    }

    public int loadUserMissionStateData(UUID uuid) {
        return getConfig().getInt(uuid.toString());
    }

    public boolean hasUserMissionStateData(UUID uuid) {
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
