package com.popogonry.guidePlugin.MusicalInstrument;

import com.popogonry.guidePlugin.GuidePlugin;
import com.popogonry.guidePlugin.util.ItemStackDataConfig;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MusicalInstrumentRepository {

    public static List<ItemStack> musicalInstrumentList = new ArrayList<>();


    private static final String FILE_NAME = "musicalInstrument.yml";
    private final String configBasePath;

    private final ItemStackDataConfig itemStackDataConfig;


    public MusicalInstrumentRepository() {
        this.configBasePath = GuidePlugin.getServerInstance().getDataFolder().getAbsolutePath();
        this.itemStackDataConfig = new ItemStackDataConfig(configBasePath, FILE_NAME);
    }

    public void reloadConfig() {
        itemStackDataConfig.reload();
    }
    public void saveConfig() {
        itemStackDataConfig.store();
    }

    public void storeMusicalInstrument() {
        itemStackDataConfig.storeItemStackList("musicalInstrumentList", musicalInstrumentList);
        musicalInstrumentList.clear();
    }

    public void saveMusicalInstrument() {
        itemStackDataConfig.storeItemStackList("musicalInstrumentList", musicalInstrumentList);
    }

    public void loadMusicalInstrument() {
        if(itemStackDataConfig.hasItemStackList("musicalInstrumentList")) {
            musicalInstrumentList = itemStackDataConfig.loadItemStackList("musicalInstrumentList");
        } else {
            musicalInstrumentList = new ArrayList<>();
        }
    }
}
