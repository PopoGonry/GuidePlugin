package com.popogonry.guidePlugin.mission.missionEvent;

import com.electro2560.dev.cluescrolls.events.PlayerScrollCreatedEvent;
import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.guidePlugin.userMissionState.UserMissionStateRepository;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class Mission5Event implements Listener {

    MissionService missionService = new MissionService();

    @EventHandler
    public void onPlayerScrollCreated(PlayerScrollCreatedEvent event) {
        Player player = event.getPlayer();

        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if (level != 5) {
            return;
        }
        missionService.missionClear(player, 5);
    }
}
