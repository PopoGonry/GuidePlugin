package com.popogonry.guidePlugin.mission.missionEvent;

import com.popogonry.guidePlugin.GuidePlugin;
import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.guidePlugin.userMissionState.UserMissionStateRepository;
import me.clip.placeholderapi.PlaceholderAPI;
import net.advancedplugins.skills.api.SkillLevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;

import static net.advancedplugins.ae.api.AEAPI.getEnchantmentsOnItem;

public class Mission16Event implements Listener {
    MissionService missionService = new MissionService();
    @EventHandler
    public void onSkillLevelUpEvent(SkillLevelUpEvent event) {
        Player player = event.getPlayer();

        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if(level != 16) {
            return;
        }
        Bukkit.getScheduler().runTaskLater(GuidePlugin.getServerInstance(), () -> {
            if(hasSkillLevelAbove30(player)) missionService.missionClear(player, 16);
        }, 1L);


    }

    private static final String[] SKILLS = {
            "acrobatics", "alchemy", "archery", "axes",
            "crossbow", "defense", "elytra", "excavation",
            "fishing", "herbalism", "mining", "swords",
            "trident", "woodcutting"
    };

    /**
     * 플레이어의 모든 스킬 레벨 중 하나라도 30 이상이면 true 반환
     */
    public static boolean hasSkillLevelAbove30(Player player) {
        for (String skill : SKILLS) {
            String placeholder = "%advancedskills_level_" + skill + "%";
            String value = PlaceholderAPI.setPlaceholders(player, placeholder);

            try {
                int level = Integer.parseInt(value.trim());
                if (level >= 30) {
                    return true;
                }
            } catch (NumberFormatException e) {
                // 값이 숫자가 아닐 경우 로그 출력 (디버깅용)
                System.out.println("플레이스홀더 값이 숫자가 아닙니다: " + placeholder + " = " + value);
            }
        }
        return false;
    }
}
