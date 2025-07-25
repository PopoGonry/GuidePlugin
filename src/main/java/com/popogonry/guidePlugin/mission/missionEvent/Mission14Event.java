package com.popogonry.guidePlugin.mission.missionEvent;

import com.popogonry.guidePlugin.MusicalInstrument.MusicalInstrumentRepository;
import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Mission14Event implements Listener {
    MissionService missionService = new MissionService();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // 우클릭만 체크
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Player player = event.getPlayer();

        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if (level != 14) {
            return;
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        // 손에 든 아이템이 리스트에 있는지 확인
        if (itemInHand != null && containsSimilarItem(itemInHand)) {
            missionService.missionClear(player, 14);
        }
    }

    private boolean containsSimilarItem(ItemStack item) {
        if (item == null) return false;

        ItemMeta itemMeta = item.getItemMeta();
        List<String> itemLore = (itemMeta != null && itemMeta.hasLore()) ? itemMeta.getLore() : null;

        for (ItemStack target : MusicalInstrumentRepository.musicalInstrumentList) {
            if (target == null) continue;

            // 기본 isSimilar 비교
            if (item.isSimilar(target)) return true;

            // Lore 비교
            ItemMeta targetMeta = target.getItemMeta();
            List<String> targetLore = (targetMeta != null && targetMeta.hasLore()) ? targetMeta.getLore() : null;

            if (itemLore != null && targetLore != null && itemLore.equals(targetLore)) {
                return true;
            }
        }
        return false;
    }

}
