package com.popogonry.guidePlugin.mission.missionEvent;

import com.popogonry.guidePlugin.mission.MissionRepository;
import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class Mission3Event implements Listener {
    MissionService missionService = new MissionService();
    @EventHandler
    public void onInventoryChange(PlayerInventorySlotChangeEvent event) {
        Player player = event.getPlayer();

        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if(level != 3) {
            return;
        }

        if (event.getNewItemStack().getType() == Material.IRON_INGOT) {
            missionService.missionClear(player, 3);
        }
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player player) {
            Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
            if(level != 3) {
                return;
            }

            if (event.getItem().getItemStack().getType() == Material.IRON_INGOT) {
                missionService.missionClear(player, 3);

            }
        }
    }
}
