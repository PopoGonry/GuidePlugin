package com.popogonry.guidePlugin.accessory;

import com.popogonry.guidePlugin.GuidePlugin;
import com.popogonry.guidePlugin.util.ItemStackDataConfig;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AccessoryRepository {

    public static List<ItemStack> accessoryList = new ArrayList<>();


    private static final String FILE_NAME = "accessory.yml";
    private final String configBasePath;

    private final ItemStackDataConfig itemStackDataConfig;


    public AccessoryRepository() {
        this.configBasePath = GuidePlugin.getServerInstance().getDataFolder().getAbsolutePath();
        this.itemStackDataConfig = new ItemStackDataConfig(configBasePath, FILE_NAME);
    }

    public void reloadConfig() {
        itemStackDataConfig.reload();
    }
    public void saveConfig() {
        itemStackDataConfig.store();
    }

    public void storeAccessory() {
        itemStackDataConfig.storeItemStackList("accessoryList", accessoryList);
        accessoryList.clear();
    }

    public void saveAccessory() {
        itemStackDataConfig.storeItemStackList("accessoryList", accessoryList);
    }

    public void loadAccessory() {
        if(itemStackDataConfig.hasItemStackList("accessoryList")) {
            accessoryList = itemStackDataConfig.loadItemStackList("accessoryList");
        } else {
            accessoryList = new ArrayList<>();
        }
    }
}
