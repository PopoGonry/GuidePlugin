package com.popogonry.guidePlugin.mission.missionEvent;

import com.popogonry.guidePlugin.GuidePlugin;
import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import me.angeschossen.lands.api.events.LandCreateEvent;
import me.angeschossen.lands.api.events.LandInvitePlayerEvent;
import me.angeschossen.lands.api.events.LandTrustPlayerEvent;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Mission7Event implements Listener {
    MissionService missionService = new MissionService();
    @EventHandler
    public void onLandCreateEvent(LandCreateEvent event) {
        Player player = event.getLandPlayer().getPlayer();

        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if(level != 7) {
            return;
        }
        Bukkit.getScheduler().runTaskLater(GuidePlugin.getServerInstance(), () -> {
            missionService.missionClear(player, 7);
        }, 40L); // 20L = 1초, 3초 = 60L

    }

    @EventHandler
    public void onLandInviteEvent(LandTrustPlayerEvent event) {
        Player player = Bukkit.getPlayer(event.getTargetUUID());

        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if(level != 7) {
            return;
        }
        missionService.missionClear(player, 7);
    }

    public static boolean isInLand(Player player) {
        String landName = PlaceholderAPI.setPlaceholders(player, "%lands_land_name%");
        if (landName == null) return false;

        String cleanName = landName.trim();
        return !(cleanName.isEmpty() || cleanName.equalsIgnoreCase("null") || cleanName.equals("§8None") || cleanName.contains("없음"));
    }
}

