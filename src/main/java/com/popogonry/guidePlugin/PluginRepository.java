
package com.popogonry.guidePlugin;


import com.popogonry.guidePlugin.MusicalInstrument.MusicalInstrumentRepository;
import com.popogonry.guidePlugin.accessory.AccessoryRepository;
import com.popogonry.guidePlugin.missionReward.MissionRewardRepository;
import com.popogonry.guidePlugin.evolutionaryBook.EvolutionaryBookRepository;
import com.popogonry.guidePlugin.specialOre.SpecialOreRepository;
import com.popogonry.guidePlugin.specialWeapon.SpecialWeaponRepository;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.guidePlugin.userMissionState.UserMissionStateRepository;
import com.popogonry.guidePlugin.util.Reference;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PluginRepository {
//    public static HashMap<UUID, Object> playerInputModeHashMap = new HashMap<>();
//    public static HashMap<UUID, ItemStack> playerCurrentItemHashMap = new HashMap<>();

    private static final String CONFIG_FILE_NAME = "config.yml";
    private final String configBasePath = GuidePlugin.getServerInstance().getDataFolder().getAbsolutePath();
    private final PluginDataConfig pluginDataConfig;
    public static PluginConfig pluginConfig;

    public PluginRepository() {
        this.pluginDataConfig = new PluginDataConfig(this.configBasePath, "config.yml");
    }

    public void reloadConfig() {
        this.pluginDataConfig.reload();
    }

    public void saveConfig() {
        this.pluginDataConfig.store();
    }

    public void loadPluginDataConfig() {
        pluginConfig = this.pluginDataConfig.loadPluginDataConfig();
    }

    public static void loadAllData() {
        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "GuidePlugin Data Load Start...");

        PluginRepository pluginRepository = new PluginRepository();
        pluginRepository.loadPluginDataConfig();

        MissionRewardRepository missionRewardRepository = new MissionRewardRepository();
        missionRewardRepository.loadMissionReward();

        SpecialOreRepository specialOreRepository = new SpecialOreRepository();
        specialOreRepository.loadSpecialOre();

        MusicalInstrumentRepository musicalInstrumentRepository = new MusicalInstrumentRepository();
        musicalInstrumentRepository.loadMusicalInstrument();

        EvolutionaryBookRepository evolutionaryBookRepository = new EvolutionaryBookRepository();
        evolutionaryBookRepository.saveEvolutionaryBook();

        SpecialWeaponRepository specialWeaponRepository = new SpecialWeaponRepository();
        specialWeaponRepository.loadSpecialWeapon();

        AccessoryRepository accessoryRepository = new AccessoryRepository();
        accessoryRepository.loadAccessory();

        loadOnlineUserData();

        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "GuidePlugin Data Load Complete!");
    }

    public static void saveAllData() {
        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "GuidePlugin Data Save Start...");

        MissionRewardRepository missionRewardRepository = new MissionRewardRepository();
        missionRewardRepository.saveMissionReward();

        SpecialOreRepository specialOreRepository = new SpecialOreRepository();
        specialOreRepository.saveSpecialOre();

        MusicalInstrumentRepository musicalInstrumentRepository = new MusicalInstrumentRepository();
        musicalInstrumentRepository.saveMusicalInstrument();

        EvolutionaryBookRepository evolutionaryBookRepository = new EvolutionaryBookRepository();
        evolutionaryBookRepository.saveEvolutionaryBook();

        SpecialWeaponRepository specialWeaponRepository = new SpecialWeaponRepository();
        specialWeaponRepository.saveSpecialWeapon();

        AccessoryRepository accessoryRepository = new AccessoryRepository();
        accessoryRepository.saveAccessory();


        storeOnlineUserData();

        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "GuidePlugin Data Save Complete!");
    }

    public static void loadUserData(Player player) {
        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + player.getName() + " User Data Load Start...");

        UserMissionLevelRepository userMissionLevelRepository = new UserMissionLevelRepository();
        userMissionLevelRepository.loadUserMissionLevel(player.getUniqueId());

        UserMissionStateRepository userMissionStateRepository = new UserMissionStateRepository();
        userMissionStateRepository.loadUserMissionState(player.getUniqueId());

        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + player.getName() + " User Data Load Complete!");
    }

    public static void storeUserData(Player player) {
        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + player.getName() + " User Data Save Start...");

        UserMissionLevelRepository userMissionLevelRepository = new UserMissionLevelRepository();
        userMissionLevelRepository.storeUserMissionLevel(player.getUniqueId());

        UserMissionStateRepository userMissionStateRepository = new UserMissionStateRepository();
        userMissionStateRepository.storeUserMissionState(player.getUniqueId());

        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + player.getName() + " User Data Save Complete!");
    }

    public static void loadOnlineUserData() {
        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "Online User Data Load Start...");
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            loadUserData(onlinePlayer);
        }
        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "Online User Data Load Complete!");

    }
    public static void storeOnlineUserData() {
        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "Online User Data Save Start...");
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            storeUserData(onlinePlayer);
        }
        Bukkit.getConsoleSender().sendMessage(Reference.prefix_normal + "Online User Data Save Complete!");
    }
    
}
