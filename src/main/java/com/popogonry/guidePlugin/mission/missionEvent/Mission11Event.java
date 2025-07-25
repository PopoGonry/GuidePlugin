package com.popogonry.guidePlugin.mission.missionEvent;

import com.popogonry.guidePlugin.GuidePlugin;
import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.guidePlugin.userMissionState.UserMissionStateRepository;
import net.advancedplugins.ae.api.AEAPI;
import net.advancedplugins.ae.api.EffectActivationEvent;
import net.advancedplugins.ae.api.EnchantActivateEvent;
import net.advancedplugins.ae.api.EnchantApplyEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

import static net.advancedplugins.ae.api.AEAPI.getEnchantmentsOnItem;


public class Mission11Event implements Listener {
    MissionService missionService = new MissionService();

    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {
        Player player = event.getEnchanter();

        // 유저 미션 레벨 확인
        Integer level = UserMissionLevelRepository.userMissionLevelHashMap
                .getOrDefault(player.getUniqueId(), 0);
        if (level != 11) {
            return;
        }

        Bukkit.getScheduler().runTaskLater(GuidePlugin.getServerInstance(), () -> {
            HashMap<String, Integer> enchantmentsOnItem = getEnchantmentsOnItem(event.getItem());

            if(enchantmentsOnItem.isEmpty()) return;
            // 미션 상태 증가
            int currentValue = UserMissionStateRepository.userMissionStateHashMap
                    .getOrDefault(player.getUniqueId(), 0);

            currentValue++;
            UserMissionStateRepository.userMissionStateHashMap.put(player.getUniqueId(), currentValue);

            // 10번 인첸트 완료 시 클리어
            if (currentValue >= 3) {
                missionService.missionClear(player, 11);
            }

        }, 1L);
    }
    @EventHandler
    public void onEnchantBookItem(EnchantApplyEvent event) {
        Player player = event.getPlayer();

        // 유저 미션 레벨 확인
        Integer level = UserMissionLevelRepository.userMissionLevelHashMap
                .getOrDefault(player.getUniqueId(), 0);
        if (level != 11) {
            return;
        }

        int currentValue = UserMissionStateRepository.userMissionStateHashMap.getOrDefault(player.getUniqueId(), 0);

        currentValue++;
        UserMissionStateRepository.userMissionStateHashMap.put(player.getUniqueId(), currentValue);

        if (currentValue >= 3) {
            missionService.missionClear(player, 11);
        }
    }
}
