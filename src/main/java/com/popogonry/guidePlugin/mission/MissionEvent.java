package com.popogonry.guidePlugin.mission;

import com.popogonry.guidePlugin.GuidePlugin;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.guidePlugin.util.Reference;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MissionEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(GuidePlugin.getServerInstance(), () -> {

            int level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(event.getPlayer().getUniqueId(), 0);
            if(level >= 1 && level != Reference.guideMaxLevel) {
                Player player = event.getPlayer();
                player.sendMessage("§c=============================================");
                player.sendMessage("            §e현재 진행중인 가이드 미션");
                player.sendMessage("§a§l" + level + " §f: §f" + MissionRepository.missionMap.get(level).getTitle());
                player.sendMessage("§c=============================================");
            }

        }, 5L); // 20L = 1초, 3초 = 60L
    }
}
