package com.popogonry.guidePlugin.util;

import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ItemStackDataConfig extends Config {
    public ItemStackDataConfig(String basePath, String fileName) {
        super(basePath, fileName);
        loadDefaults();
    }

    public void storeItemStackList(String key, List<ItemStack> itemStackList) {
        getConfig().set(key, itemStackList);
        super.store();
    }

    public List<ItemStack> loadItemStackList(String key) {
        List<ItemStack> itemStackList = new ArrayList<>();

        List<?> rawList = getConfig().getList(key);
        if (rawList != null) {
            for (Object obj : rawList) {
                if (obj instanceof ItemStack) {
                    itemStackList.add((ItemStack) obj);
                }
            }
        }

        return itemStackList;
    }

    public boolean hasItemStackList(String key) {
        return getConfig().contains(key);
    }

    public void removeItemStackList(String key) {
        getConfig().set(key, null);
    }

    @Override
    public void loadDefaults() {
    }

    @Override
    public void applySettings() {
        getConfig().options().copyDefaults(true);
    }
}
