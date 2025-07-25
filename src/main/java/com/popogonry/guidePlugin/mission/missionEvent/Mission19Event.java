package com.popogonry.guidePlugin.mission.missionEvent;

import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.infinityTowerPlugin.InfinityTower.InfinityTowerEndEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Mission19Event implements Listener {
    MissionService missionService = new MissionService();

    @EventHandler
    public void onEnterInfinityTower(InfinityTowerEndEvent event) {

        Player player = event.getPlayer();

        int level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if (level != 19) return;

        missionService.missionClear(player, 19);
    }
}
