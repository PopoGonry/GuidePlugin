package com.popogonry.guidePlugin.mission.missionEvent;

import com.popogonry.guidePlugin.GuidePlugin;
import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.guidePlugin.userMissionState.UserMissionStateRepository;
import net.momirealms.customcrops.api.core.mechanic.crop.CropConfig;
import net.momirealms.customcrops.api.core.world.CustomCropsBlockState;
import net.momirealms.customcrops.api.event.CropBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


import javax.naming.Context;

public class Mission15Event implements Listener {

    MissionService missionService = new MissionService();

    // API로 수정
    @EventHandler
    public void onCropBreak(CropBreakEvent event) {
        if(!(event.entityBreaker() instanceof Player)) return;

        Player player = (Player) event.entityBreaker();

        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if (level != 15) return;

        String currentStage = event.cropStageItemID(); // 현재 스테이지 ID
        CropConfig config = event.cropConfig();        // 해당 작물 설정

        // config에서 마지막 단계 ID 가져오기 (API에 따라 구현 필요)
        String maxStage = config.stages().stream().toList().get(config.stages().size() - 1).stageID();

        if (!currentStage.equals(maxStage)) return;

        int currentValue = UserMissionStateRepository.userMissionStateHashMap.getOrDefault(player.getUniqueId(), 0);
        currentValue++;
        UserMissionStateRepository.userMissionStateHashMap.put(player.getUniqueId(), currentValue);

        if (currentValue >= 10) {
            missionService.missionClear(player, 15);
            Bukkit.getScheduler().runTaskLater(GuidePlugin.getServerInstance(), () -> {
                if(Mission16Event.hasSkillLevelAbove30(player)) missionService.missionClear(player, 16);
            }, 40L);
        }
    }
}
