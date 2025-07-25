package com.popogonry.guidePlugin.mission.missionEvent;


import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.guidePlugin.userMissionState.UserMissionStateRepository;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;


public class Mission10Event implements Listener {
    MissionService missionService = new MissionService();

    @EventHandler
    public void onItemEnchant(EnchantItemEvent event) {
        Player player = event.getEnchanter();

        // 유저 미션 레벨 확인
        Integer level = UserMissionLevelRepository.userMissionLevelHashMap
                .getOrDefault(player.getUniqueId(), 0);
        if (level != 10) {
            return;
        }

        // 인첸트한 아이템이 곡괭이, 도끼, 괭이, 삽인지 확인
        Material type = event.getItem().getType();
        if (!(type.name().endsWith("_PICKAXE") ||
                type.name().endsWith("_AXE") ||
                type.name().endsWith("_HOE") ||
                type.name().endsWith("_SHOVEL"))) {
            return;
        }



        // 미션 상태 증가
        int currentValue = UserMissionStateRepository.userMissionStateHashMap
                .getOrDefault(player.getUniqueId(), 0);

        currentValue++;
        UserMissionStateRepository.userMissionStateHashMap.put(player.getUniqueId(), currentValue);

        // 10번 인첸트 완료 시 클리어
        if (currentValue >= 10) {
            missionService.missionClear(player, 10);
        }
    }
}
