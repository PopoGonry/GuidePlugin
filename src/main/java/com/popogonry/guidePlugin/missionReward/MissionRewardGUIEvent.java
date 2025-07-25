package com.popogonry.guidePlugin.missionReward;

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

public class MissionRewardGUIEvent implements Listener {
    @EventHandler
    public static void onClickMissionRewardSettingGUI(InventoryClickEvent event) {
        if (event.getView().getTitle().contains("Mission Reward Setting GUI")
                && event.getCurrentItem() != null
                && event.getCurrentItem().getType() != Material.AIR) {


            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            Inventory inventory = event.getInventory();

            int slot = event.getRawSlot();
            int level = Integer.parseInt(event.getView().getTitle().split(" ")[1]);


            MissionRewardGUI missionRewardGUI = new MissionRewardGUI();


            String[] strings1 = inventory.getItem(49).getItemMeta().getDisplayName().split("/");
            String[] strings2 = strings1[0].split(" ");
            int page = Integer.parseInt(strings2[1].replaceAll(" ", ""));
            int maxPage = Integer.parseInt(strings1[1].replaceAll(" ", ""));

            // Item List
            if (0 <= slot && slot <= 44) {
                if (event.getClick().isShiftClick() && event.getClick().isRightClick()) {
                    MissionRewardRepository.missionRewardHashMap.get(level).remove(slot + (45*(page-1)));
                    player.sendMessage(Reference.prefix_normal + "아이템이 제거 되었습니다.");
                    missionRewardGUI.openMissionRewardSettingGUI(player, level, page);
                }


            } else if (48 <= slot && slot <= 50) {
                ItemStack itemStack = inventory.getItem(slot);
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta.getDisplayName().contains("To")) {
                    String[] strings = itemMeta.getDisplayName().split(" ");
                     missionRewardGUI.openMissionRewardSettingGUI(player, level, Integer.parseInt(strings[1]));
                }
            }

            // Player Inventory
            else if (54 <= slot && slot <= 89) {
                MissionRewardRepository.missionRewardHashMap.get(level).add(new ItemStack(event.getCurrentItem()));
                missionRewardGUI.openMissionRewardSettingGUI(player, level, maxPage);
            }
            MissionRewardRepository missionRewardRepository = new MissionRewardRepository();
            missionRewardRepository.saveMissionReward();
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
        }
    }
}
