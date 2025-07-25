package com.popogonry.guidePlugin.mission.missionEvent;

import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.specialOre.SpecialOreRepository;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Mission12Event implements Listener {

    MissionService missionService = new MissionService();

    // Paper 전용 슬롯 변경 이벤트
    @EventHandler
    public void onInventoryChange(PlayerInventorySlotChangeEvent event) {
        Player player = event.getPlayer();

        int level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
        if (level != 12) return;

        ItemStack newItem = event.getNewItemStack();
        if (newItem != null && containsSimilarItem(newItem)) {
            missionService.missionClear(player, 12);
        }
    }

    // 바닥에서 아이템을 주울 때
    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player player) {
            int level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);
            if (level != 12) return;

            ItemStack pickedItem = event.getItem().getItemStack();
            if (pickedItem != null && containsSimilarItem(pickedItem)) {
                missionService.missionClear(player, 12);
            }
        }
    }

    // 리스트에 비슷한 아이템이 있는지 확인
    private boolean containsSimilarItem(ItemStack item) {
        for (ItemStack target : SpecialOreRepository.specialOreList) {
            if (item.isSimilar(target)) return true;
        }
        return false;
    }
}
