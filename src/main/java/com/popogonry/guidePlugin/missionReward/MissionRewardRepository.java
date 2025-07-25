package com.popogonry.guidePlugin.missionReward;

import com.popogonry.guidePlugin.GuidePlugin;
import com.popogonry.guidePlugin.util.ItemStackDataConfig;
import com.popogonry.guidePlugin.util.Reference;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.*;

public class MissionRewardRepository {

    public static HashMap<Integer, List<ItemStack>> missionRewardHashMap = new HashMap<>();


    private static final String FILE_NAME = "missionReward.yml";
    private final String configBasePath;

    private final ItemStackDataConfig itemStackDataConfig;


    public MissionRewardRepository() {
        this.configBasePath = GuidePlugin.getServerInstance().getDataFolder().getAbsolutePath();
        this.itemStackDataConfig = new ItemStackDataConfig(configBasePath, FILE_NAME);
    }

    public void reloadConfig() {
        itemStackDataConfig.reload();
    }
    public void saveConfig() {
        itemStackDataConfig.store();
    }

    public void storeMissionReward() {
        for (Integer i : missionRewardHashMap.keySet()) {
            itemStackDataConfig.storeItemStackList(i.toString(), missionRewardHashMap.get(i));
        }
        missionRewardHashMap.clear();
    }

    public void saveMissionReward() {
        for (Integer i : missionRewardHashMap.keySet()) {
            itemStackDataConfig.storeItemStackList(i.toString(), missionRewardHashMap.get(i));
        }
    }

    public void loadMissionReward() {
        for (Integer i = 1; i <= Reference.guideMaxLevel; i++) {
            if(itemStackDataConfig.hasItemStackList(i.toString())) {
                missionRewardHashMap.put(i, itemStackDataConfig.loadItemStackList(i.toString()));
            } else {
                missionRewardHashMap.put(i, new ArrayList<>());
            }
        }
    }
}
