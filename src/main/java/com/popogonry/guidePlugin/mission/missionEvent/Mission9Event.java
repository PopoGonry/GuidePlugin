package com.popogonry.guidePlugin.mission.missionEvent;

import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.guidePlugin.userMissionState.UserMissionStateRepository;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Mission9Event implements Listener {
    MissionService missionService = new MissionService();

    @EventHandler
    public void onCropHarvest(BlockBreakEvent event) {
        Player player = event.getPlayer();

        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if(level != 9) {
            return;
        }
        Block block = event.getBlock();

        // 농작물 체크
        if (isFullyGrownCrop(block)) {
            int currentValue = UserMissionStateRepository.userMissionStateHashMap.getOrDefault(player.getUniqueId(), 0);

            currentValue++;

            UserMissionStateRepository.userMissionStateHashMap.put(player.getUniqueId(), currentValue);

            if(currentValue >= 10) {
                missionService.missionClear(player, 9);
            }
        }
    }

    private boolean isFullyGrownCrop(Block block) {
        // 바닐라 농작물인지 확인
        if (block.getBlockData() instanceof Ageable ageable) {
            return ageable.getAge() == ageable.getMaximumAge();
        }
        return false;
    }
}
