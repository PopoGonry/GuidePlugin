package com.popogonry.guidePlugin.mission;

import com.popogonry.guidePlugin.PluginRepository;
import com.popogonry.guidePlugin.missionReward.MissionRewardGUI;
import com.popogonry.guidePlugin.missionReward.MissionRewardRepository;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.guidePlugin.util.Reference;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MissionGUIEvent implements Listener {
    @EventHandler
    public static void onClickMissionGUI(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(PluginRepository.pluginConfig.getGuiName())
                && event.getCurrentItem() != null
                && event.getCurrentItem().getType() != Material.AIR) {


            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            Inventory inventory = event.getInventory();

            int slot = event.getRawSlot();

            Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);

            if(level == 0) {
                UserMissionLevelRepository.userMissionLevelHashMap.put(player.getUniqueId(), 1);
                MissionGUI missionGUI = new MissionGUI();
                missionGUI.openMissionGUI(player);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
            }
        }
    }
}
