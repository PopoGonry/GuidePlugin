package com.popogonry.guidePlugin.specialWeapon;

import com.popogonry.guidePlugin.GuidePlugin;
import com.popogonry.guidePlugin.util.ItemStackDataConfig;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SpecialWeaponRepository {

    public static List<ItemStack> specialWeaponList = new ArrayList<>();


    private static final String FILE_NAME = "specialWeapon.yml";
    private final String configBasePath;

    private final ItemStackDataConfig itemStackDataConfig;


    public SpecialWeaponRepository() {
        this.configBasePath = GuidePlugin.getServerInstance().getDataFolder().getAbsolutePath();
        this.itemStackDataConfig = new ItemStackDataConfig(configBasePath, FILE_NAME);
    }

    public void reloadConfig() {
        itemStackDataConfig.reload();
    }
    public void saveConfig() {
        itemStackDataConfig.store();
    }

    public void storeSpecialWeapon() {
        itemStackDataConfig.storeItemStackList("specialWeaponList", specialWeaponList);
        specialWeaponList.clear();
    }

    public void saveSpecialWeapon() {
        itemStackDataConfig.storeItemStackList("specialWeaponList", specialWeaponList);
    }

    public void loadSpecialWeapon() {
        if(itemStackDataConfig.hasItemStackList("specialWeaponList")) {
            specialWeaponList = itemStackDataConfig.loadItemStackList("specialWeaponList");
        } else {
            specialWeaponList = new ArrayList<>();
        }
    }
}
