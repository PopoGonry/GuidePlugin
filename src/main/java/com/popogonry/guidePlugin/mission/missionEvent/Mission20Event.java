package com.popogonry.guidePlugin.mission.missionEvent;

import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.infinityTowerPlugin.InfinityTower.InfinityTowerEndEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import yd.kingdom.evolution.api.event.EvolutionEvent;

public class Mission20Event implements Listener {
    MissionService missionService = new MissionService();

    @EventHandler
    public void onEvolution(EvolutionEvent event) {

        Player player = event.getPlayer();

        int level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if (level != 20) return;

        missionService.missionClear(player, 20);
    }
}
    