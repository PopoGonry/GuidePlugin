package com.popogonry.guidePlugin.mission.missionEvent;

import com.electro2560.dev.cluescrolls.events.PlayerScrollCompletedEvent;
import com.electro2560.dev.cluescrolls.events.PlayerScrollCreatedEvent;
import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Mission6Event implements Listener {

    MissionService missionService = new MissionService();

    @EventHandler
    public void onPlayerScrollCreated(PlayerScrollCompletedEvent event) {
        Player player = event.getPlayer();

        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if (level != 6) {
            return;
        }
        missionService.missionClear(player, 6);
    }
}
