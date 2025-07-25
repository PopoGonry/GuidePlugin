package com.popogonry.guidePlugin.MusicalInstrument;

import com.popogonry.guidePlugin.util.GUI;
import com.popogonry.guidePlugin.util.Reference;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MusicalInstrumentGUI {
    public boolean openMusicalInstrumentSettingGUI(Player player, int page) {
        Inventory inventory = Bukkit.createInventory(player, 54, Reference.prefix_normal + "Musical Instrument Setting GUI");

        List<ItemStack> musicalInstrumentList = new ArrayList<>(MusicalInstrumentRepository.musicalInstrumentList);

        for (int i = 0 + (45*(page-1)); i < 45 + (45*(page-1)) && i < musicalInstrumentList.size(); i++) {
            inventory.setItem(i - (45*(page-1)), new ItemStack(musicalInstrumentList.get(i)));
        }

        // 48 49 50
        int maxPage = Math.max(1, (musicalInstrumentList.size() + 44) / 45);

        inventory.setItem(49, GUI.getCustomItemStack(Material.EMERALD, Reference.prefix + "Page " + page + " / " + maxPage, Collections.singletonList(ChatColor.GOLD + "Amount of Items: " + MusicalInstrumentRepository.musicalInstrumentList.size())));

        if(page > 1) {
            inventory.setItem(48, GUI.getCustomItemStack(Material.PAPER, Reference.prefix + "To " + (page - 1)));
        }

        if(page < maxPage) {
            inventory.setItem(50, GUI.getCustomItemStack(Material.PAPER, Reference.prefix + "To " + (page + 1)));
        }

        player.openInventory(inventory);

        return true;
    }
}
