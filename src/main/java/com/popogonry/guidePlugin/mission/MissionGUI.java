package com.popogonry.guidePlugin.mission;

import com.popogonry.guidePlugin.PluginRepository;
import com.popogonry.guidePlugin.mission.missionEvent.Mission16Event;
import com.popogonry.guidePlugin.mission.missionEvent.Mission7Event;
import com.popogonry.guidePlugin.missionReward.MissionRewardRepository;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.guidePlugin.userMissionState.UserMissionStateRepository;
import com.popogonry.guidePlugin.util.GUI;
import com.popogonry.guidePlugin.util.Reference;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MissionGUI {
    public boolean openMissionGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 27, PluginRepository.pluginConfig.getGuiName());
        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);


        if(level == 0) {
            inventory.setItem(13, GUI.getCustomItemStack(Material.BOOK, "§6책을 눌러, 가이드 미션을 시작하세요!"));
        }
        else {
            List<String> lore = conversionStringList(PluginRepository.pluginConfig.getItemLore(), level);

            if(level == 4) {
                lore.add("§6획득 경험치 : " + UserMissionStateRepository.userMissionStateHashMap.getOrDefault(player.getUniqueId(), 0) + " / 1000");
            }
            else if(level == 7) {
                MissionService missionService = new MissionService();
                if(Mission7Event.isInLand(player)) missionService.missionClear(player, 7);
            }
            else if(level == 9) {
                lore.add("§6수확한 작물 : " + UserMissionStateRepository.userMissionStateHashMap.getOrDefault(player.getUniqueId(), 0) + " / 10");
            }
            else if(level == 10) {
                lore.add("§6도구 인첸트 : " + UserMissionStateRepository.userMissionStateHashMap.getOrDefault(player.getUniqueId(), 0) + " / 10");
            }
            else if(level == 11) {
                lore.add("§6특수 인첸트 : " + UserMissionStateRepository.userMissionStateHashMap.getOrDefault(player.getUniqueId(), 0) + " / 3");
            }
            else if(level == 15) {
                lore.add("§6특수 작물 : " + UserMissionStateRepository.userMissionStateHashMap.getOrDefault(player.getUniqueId(), 0) + " / 10");
            }
            else if(level == 16) {
                MissionService missionService = new MissionService();
                if(Mission16Event.hasSkillLevelAbove30(player)) missionService.missionClear(player, 16);
            }



            inventory.setItem(13, GUI.getCustomItemStack(Material.WRITABLE_BOOK, conversionString(PluginRepository.pluginConfig.getItemName(), level), lore));
        }


        player.openInventory(inventory);

        return true;
    }
    public String conversionString(String old, int missionLevel) {
        String returnString = old.replaceAll("&", "§");
        returnString = returnString.replaceAll("\\{미션넘버}", missionLevel + "");
        returnString = returnString.replaceAll("\\{미션제목}", MissionRepository.missionMap.get(missionLevel).getTitle());
        return returnString;
    }
    public List<String> conversionStringList(List<String> oldList, int missionLevel) {
        List<String> returnList = new ArrayList<>();

        for (String s : oldList) {
            if(s.contains("{미션클리어설명}")) {
                returnList.addAll(MissionRepository.missionMap.get(missionLevel).getDescription());
            }
            else {
                returnList.add(conversionString(s, missionLevel));
            }
        }

        return returnList;
    }
}
