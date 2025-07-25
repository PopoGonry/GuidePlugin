package com.popogonry.guidePlugin.mission.missionEvent;

import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class Mission13Event implements Listener {
    MissionService missionService = new MissionService();
    // 슬롯 변경 이벤트 (Paper 전용)
    @EventHandler
    public void onInventoryChange(PlayerInventorySlotChangeEvent event) {
        Player player = event.getPlayer();

        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if(level != 13) {
            return;
        }

        if (event.getNewItemStack().getType() == Material.TROPICAL_FISH) {
            missionService.missionClear(player, 13);
        }
    }

    // 바닥에서 아이템을 주울 때
    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player player) {
            Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
            if(level != 13) {
                return;
            }

            if (event.getItem().getItemStack().getType() == Material.TROPICAL_FISH) {
                missionService.missionClear(player, 13);

            }
        }
    }
}
