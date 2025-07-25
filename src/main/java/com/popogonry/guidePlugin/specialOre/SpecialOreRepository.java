package com.popogonry.guidePlugin.specialOre;

import com.popogonry.guidePlugin.GuidePlugin;
import com.popogonry.guidePlugin.util.ItemStackDataConfig;
import com.popogonry.guidePlugin.util.Reference;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpecialOreRepository {

    public static List<ItemStack> specialOreList = new ArrayList<>();


    private static final String FILE_NAME = "specialOre.yml";
    private final String configBasePath;

    private final ItemStackDataConfig itemStackDataConfig;


    public SpecialOreRepository() {
        this.configBasePath = GuidePlugin.getServerInstance().getDataFolder().getAbsolutePath();
        this.itemStackDataConfig = new ItemStackDataConfig(configBasePath, FILE_NAME);
    }

    public void reloadConfig() {
        itemStackDataConfig.reload();
    }
    public void saveConfig() {
        itemStackDataConfig.store();
    }

    public void storeSpecialOre() {
        itemStackDataConfig.storeItemStackList("specialOreList", specialOreList);
        specialOreList.clear();
    }

    public void saveSpecialOre() {
        itemStackDataConfig.storeItemStackList("specialOreList", specialOreList);
    }

    public void loadSpecialOre() {
        if(itemStackDataConfig.hasItemStackList("specialOreList")) {
            specialOreList = itemStackDataConfig.loadItemStackList("specialOreList");
        } else {
            specialOreList = new ArrayList<>();
        }
    }
}
