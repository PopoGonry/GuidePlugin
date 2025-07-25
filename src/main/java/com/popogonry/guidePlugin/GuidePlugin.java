package com.popogonry.guidePlugin;

import com.popogonry.guidePlugin.MusicalInstrument.MusicalInstrumentGUIEvent;
import com.popogonry.guidePlugin.accessory.AccessoryGUIEvent;
import com.popogonry.guidePlugin.mission.MissionEvent;
import com.popogonry.guidePlugin.mission.MissionGUIEvent;
import com.popogonry.guidePlugin.mission.MissionRepository;
import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.missionReward.MissionRewardGUIEvent;
import com.popogonry.guidePlugin.evolutionaryBook.EvolutionaryBookGUIEvent;
import com.popogonry.guidePlugin.specialOre.SpecialOreGUIEvent;
import com.popogonry.guidePlugin.specialWeapon.SpecialWeaponGUIEvent;
import com.popogonry.guidePlugin.util.Reference;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GuidePlugin extends JavaPlugin {

    private static GuidePlugin serverInstance;

    @Override
    public void onEnable() {
        // Plugin startup logic

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().warning("PlaceholderAPI 등록에 실패했습니다. 플러그인을 비활성화합니다.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getLogger().info("PlaceholderAPI 등록에 성공했습니다.");

        serverInstance = this;

        getServer().getPluginManager().registerEvents(new GuidePluginEvent(), this);
        getServer().getPluginManager().registerEvents(new MissionRewardGUIEvent(), this);
        getServer().getPluginManager().registerEvents(new SpecialOreGUIEvent(), this);
        getServer().getPluginManager().registerEvents(new MusicalInstrumentGUIEvent(), this);
        getServer().getPluginManager().registerEvents(new EvolutionaryBookGUIEvent(), this);
        getServer().getPluginManager().registerEvents(new SpecialWeaponGUIEvent(), this);
        getServer().getPluginManager().registerEvents(new AccessoryGUIEvent(), this);
        getServer().getPluginManager().registerEvents(new MissionEvent(), this);
        getServer().getPluginManager().registerEvents(new MissionGUIEvent(), this);
        getServer().getPluginCommand("tuguide").setExecutor(new GuidePluginCommand());
        getServer().getPluginCommand("ng").setExecutor(new GuidePluginCommand());

        MissionService missionService = new MissionService();
        missionService.missionEventRegister();

        saveDefaultConfig();

        PluginRepository.loadAllData();
        MissionRepository missionRepository = new MissionRepository();
        missionRepository.initMission();

        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "Guide Plugin Enabled (Developer: PopoGonry)");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        PluginRepository.saveAllData();

        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "Guide Plugin Disabled (Developer: PopoGonry)");
    }

    public static GuidePlugin getServerInstance() {
        return serverInstance;
    }
}
