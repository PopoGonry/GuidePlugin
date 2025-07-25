package com.popogonry.guidePlugin.mission.missionEvent;


import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import me.angeschossen.lands.api.events.ChunkPostClaimEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Mission8Event implements Listener {
    MissionService missionService = new MissionService();
    @EventHandler
    public void onLandCreateEvent(ChunkPostClaimEvent event) {
        Player player = event.getLandPlayer().getPlayer();

        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if(level != 8) {
            return;
        }
        missionService.missionClear(player, 8);
    }

}

