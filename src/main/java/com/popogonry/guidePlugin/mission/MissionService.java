package com.popogonry.guidePlugin.mission;

import com.popogonry.guidePlugin.GuidePlugin;
import com.popogonry.guidePlugin.mission.missionEvent.*;
import com.popogonry.guidePlugin.missionReward.MissionRewardRepository;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.guidePlugin.userMissionState.UserMissionStateRepository;
import com.popogonry.guidePlugin.util.Reference;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class MissionService {
    public void startMission(Player player) {
        if(UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0) != 0) {
            player.sendMessage(Reference.prefix_normal + "이미 뉴비 가이드가 진행중입니다.");
            return;
        }
        UserMissionLevelRepository.userMissionLevelHashMap.put(player.getUniqueId(), 1);
        missionStartTitle(player);
    }
    public void missionStartTitle(Player player) {
        player.sendTitle("§b§l뉴비 가이드", "§b§l미션이 시작됩니다.", 10, 70, 20);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.2f);
    }
    public void missionClearTitle(Player player, int clearLevel) {
        player.sendTitle("§6"+ clearLevel + " §f미션 클리어", "§e보상이 지급되었습니다. ", 10, 70, 20);
        player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
    }

    public void missionClear(Player player, int clearLevel) {
        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if(level != clearLevel) {
            return;
        }
        giveItemsOrDrop(player, MissionRewardRepository.missionRewardHashMap.get(clearLevel));
        UserMissionLevelRepository.userMissionLevelHashMap.put(player.getUniqueId(), clearLevel + 1);
        UserMissionStateRepository.userMissionStateHashMap.put(player.getUniqueId(), 0);
        Bukkit.getScheduler().runTaskLater(GuidePlugin.getServerInstance(), () -> {
            missionClearTitle(player, clearLevel);
        }, 20L); // 20L = 1초, 3초 = 60L
    }

    public static void giveItemsOrDrop(Player player, List<ItemStack> items) {
        Location dropLocation = player.getLocation(); // 플레이어 위치

        for (ItemStack item : items) {
            if (item == null) continue; // Null 체크

            // 인벤토리에 추가 시 남는 아이템(추가 불가 아이템) 반환
            HashMap<Integer, ItemStack> leftover = player.getInventory().addItem(item);

            // 남은 아이템이 있다면 땅에 드롭
            for (ItemStack remain : leftover.values()) {
                player.getWorld().dropItemNaturally(dropLocation, remain);
            }
        }
    }

    public void missionEventRegister() {
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission1Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission2Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission3Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission4Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission5Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission6Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission7Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission8Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission9Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission10Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission11Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission12Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission13Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission14Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission15Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission16Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission17Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission18Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission19Event(), GuidePlugin.getServerInstance());
        GuidePlugin.getServerInstance().getServer().getPluginManager().registerEvents(new Mission20Event(), GuidePlugin.getServerInstance());
    }


}
