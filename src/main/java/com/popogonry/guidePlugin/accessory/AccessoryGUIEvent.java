package com.popogonry.guidePlugin.accessory;

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

public class AccessoryGUIEvent implements Listener {
    @EventHandler
    public static void onClickAccessoryGUI(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(Reference.prefix_normal + "Accessory Setting GUI")
                && event.getCurrentItem() != null
                && event.getCurrentItem().getType() != Material.AIR) {


            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();
            Inventory inventory = event.getInventory();

            int slot = event.getRawSlot();

            AccessoryGUI accessoryGUI = new AccessoryGUI();


            String[] strings1 = inventory.getItem(49).getItemMeta().getDisplayName().split("/");
            String[] strings2 = strings1[0].split(" ");
            int page = Integer.parseInt(strings2[1].replaceAll(" ", ""));
            int maxPage = Integer.parseInt(strings1[1].replaceAll(" ", ""));

            // Item List
            if (0 <= slot && slot <= 44) {
                if (event.getClick().isShiftClick() && event.getClick().isRightClick()) {
                    AccessoryRepository.accessoryList.remove(slot + (45*(page-1)));
                    player.sendMessage(Reference.prefix_normal + "아이템이 제거 되었습니다.");
                    accessoryGUI.openAccessorySettingGUI(player, page);
                }


            } else if (48 <= slot && slot <= 50) {
                ItemStack itemStack = inventory.getItem(slot);
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (itemMeta.getDisplayName().contains("To")) {
                    String[] strings = itemMeta.getDisplayName().split(" ");
                     accessoryGUI.openAccessorySettingGUI(player, Integer.parseInt(strings[1]));
                }
            }

            // Player Inventory
            else if (54 <= slot && slot <= 89) {
                ItemStack itemStack = new ItemStack(event.getCurrentItem());
                itemStack.setAmount(1);
                AccessoryRepository.accessoryList.add(itemStack);
                accessoryGUI.openAccessorySettingGUI(player, maxPage);
            }

            AccessoryRepository accessoryRepository = new AccessoryRepository();
            accessoryRepository.saveAccessory();
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
        }
    }
}
