package com.popogonry.guidePlugin.mission.missionEvent;

import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import kr.hqservice.upgrade.api.event.HQUpgradeEvent;
import kr.hqservice.upgrade.api.event.HQUpgradeFinishEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Mission17Event implements Listener {
    MissionService missionService = new MissionService();
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // 플레이어 체크
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();

        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if (level != 17) return;

        Inventory inventory = event.getInventory();

        // 23번째 슬롯(인덱스 22) 클릭 여부
        if (event.getSlot() != 22) return;

        // 23번째 슬롯 아이템 확인
        ItemStack clickedItem = inventory.getItem(22);
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        ItemMeta clickedMeta = clickedItem.getItemMeta();
        if (clickedMeta == null || !clickedMeta.hasLore()) return;

        // 51번째 슬롯 아이템 확인
        ItemStack upgradeStone = inventory.getItem(50); // 51번째 슬롯 (인덱스 50)
        if (upgradeStone == null || !upgradeStone.hasItemMeta()) return;

        ItemMeta stoneMeta = upgradeStone.getItemMeta();
        String stoneName = ChatColor.stripColor(stoneMeta.getDisplayName());

        // 51번째 슬롯 아이템 이름/로어 조건 확인
        boolean validStone = false;
        if (stoneName.equals("신비한 아이템 강화석")) {
            if (stoneMeta.hasLore() && containsInLore(stoneMeta.getLore(), "아이템을 강화할 수 있는 신비로운 힘이 담긴 강화석이다.")) {
                validStone = true;
            }
        } else if (stoneName.equals("강력한 아이템 강화석")) {
            if (stoneMeta.hasLore() && containsInLore(stoneMeta.getLore(), "더 높은 단계의 아이템을 강화하기 위해 필요한 강화석이다.")) {
                validStone = true;
            }
        }

        if (!validStone) return;

        missionService.missionClear(player, 17);
    }

    private boolean containsInLore(List<String> lore, String target) {
        for (String line : lore) {
            if (ChatColor.stripColor(line).contains(target)) {
                return true;
            }
        }
        return false;
    }
}
