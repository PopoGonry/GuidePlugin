package com.popogonry.guidePlugin.mission.missionEvent;

import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.guidePlugin.userMissionState.UserMissionStateRepository;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class Mission4Event implements Listener {

    MissionService missionService = new MissionService();

    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent event) {
        Player player = event.getPlayer();

        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if (level != 4) {
            return;
        }
        int amount = event.getAmount(); // 이번에 얻는 경험치량

        // 기존 값 불러오기
        int currentExp = UserMissionStateRepository.userMissionStateHashMap.getOrDefault(player.getUniqueId(), 0);

        // 새 경험치 추가
        currentExp += amount;

        UserMissionStateRepository.userMissionStateHashMap.put(player.getUniqueId(), currentExp);

        if(currentExp >= 1000) {
            missionService.missionClear(player, 4);
        }
    }
}
