package com.popogonry.guidePlugin.mission.missionEvent;

import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import me.SuperRonanCraft.BetterRTP.player.rtp.RTP_TYPE;
import me.SuperRonanCraft.BetterRTP.references.customEvents.RTP_TeleportPostEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Mission2Event implements Listener {
    MissionService missionService = new MissionService();
    @EventHandler
    public void onRTPTeleportPostEventChange(RTP_TeleportPostEvent event) {
        Player player = event.getPlayer();

        Integer level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(player.getUniqueId(), 0);

        if(level != 2) {
            return;
        }
        if(event.getType() == RTP_TYPE.COMMAND) {
            missionService.missionClear(player, 2);
        }
    }

}
