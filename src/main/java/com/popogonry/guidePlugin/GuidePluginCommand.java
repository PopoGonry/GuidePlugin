package com.popogonry.guidePlugin;

import com.popogonry.guidePlugin.MusicalInstrument.MusicalInstrumentGUI;
import com.popogonry.guidePlugin.accessory.AccessoryGUI;
import com.popogonry.guidePlugin.mission.MissionGUI;
import com.popogonry.guidePlugin.mission.MissionService;
import com.popogonry.guidePlugin.missionReward.MissionRewardGUI;
import com.popogonry.guidePlugin.evolutionaryBook.EvolutionaryBookGUI;
import com.popogonry.guidePlugin.specialOre.SpecialOreGUI;
import com.popogonry.guidePlugin.specialWeapon.SpecialWeaponGUI;
import com.popogonry.guidePlugin.userMissionLevel.UserMissionLevelRepository;
import com.popogonry.guidePlugin.userMissionState.UserMissionStateRepository;
import com.popogonry.guidePlugin.util.Reference;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GuidePluginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("tuguide")) {
            if(args.length == 0 && sender instanceof Player) {
                MissionService missionService = new MissionService();
//                missionService.startMission((Player) sender);
                missionService.missionStartTitle((Player) sender);
                return true;
            }

            if(args.length == 1) {
                if (args[0].equalsIgnoreCase("menu") && sender instanceof Player) {
                    MissionGUI missionGUI = new MissionGUI();
                    Player player = (Player) sender;
                    missionGUI.openMissionGUI(player);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                    return true;
                }
            }

            if (!sender.isOp()) {
                sendtuguideCommandHelp(sender);
                return false;
            }

            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("userList")) {
                    printUserMissionInfo(sender);
                    return true;
                }
            }
            else if(args.length == 2) {
                if (args[0].equalsIgnoreCase("reward") && sender instanceof Player) {
                    MissionRewardGUI missionRewardGUI = new MissionRewardGUI();
                    Player player = (Player) sender;
                    missionRewardGUI.openMissionRewardSettingGUI(player, Integer.parseInt(args[1]), 1);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                    return true;
                }
                else if (args[0].equalsIgnoreCase("reset")) {
                    if(args[1].equalsIgnoreCase("all")) {
                        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                            UserMissionStateRepository.userMissionStateHashMap.put(offlinePlayer.getUniqueId(), 0);
                            UserMissionStateRepository userMissionStateRepository = new UserMissionStateRepository();
                            userMissionStateRepository.saveUserMissionState(offlinePlayer.getUniqueId());

                            UserMissionLevelRepository.userMissionLevelHashMap.put(offlinePlayer.getUniqueId(), 0);
                            UserMissionLevelRepository userMissionLevelRepository = new UserMissionLevelRepository();
                            userMissionLevelRepository.saveUserMissionLevel(offlinePlayer.getUniqueId());
                        }
                        sender.sendMessage(Reference.prefix_normal + "전체 유저의 미션 레벨 및 미션 상태가 초기화 되었습니다.");
                    }
                    else {
                        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                        if(offlinePlayer == null) {
                            sender.sendMessage(Reference.prefix_error + "존재하지 않는 유저입니다.");
                            return false;
                        }
                        else {
                            UserMissionLevelRepository.userMissionLevelHashMap.put(offlinePlayer.getUniqueId(), 0);
                            UserMissionLevelRepository userMissionLevelRepository = new UserMissionLevelRepository();
                            userMissionLevelRepository.saveUserMissionLevel(offlinePlayer.getUniqueId());

                            UserMissionStateRepository.userMissionStateHashMap.put(offlinePlayer.getUniqueId(), 0);
                            UserMissionStateRepository userMissionStateHashMap = new UserMissionStateRepository();
                            userMissionStateHashMap.saveUserMissionState(offlinePlayer.getUniqueId());
                            sender.sendMessage(Reference.prefix_normal + offlinePlayer.getName() + "의 미션 레벨 및 미션 상태가 초기화 되었습니다.");

                        }
                    }
                    return true;
                }
            }
            else if(args.length == 3) {
                if (args[0].equalsIgnoreCase("set")) {
                    if(!StringUtils.isNumeric(args[2])) {
                        sender.sendMessage(Reference.prefix_error + "미션 넘버에 숫자만 입력 가능합니다.");
                        return false;
                    }
                    if(Integer.parseInt(args[2]) < 0 || Reference.guideMaxLevel < Integer.parseInt(args[2])) {
                        sender.sendMessage(Reference.prefix_error + "미션 넘버는 0~" + Reference.guideMaxLevel + " 사이의 숫자만 입력 가능합니다.");
                        return false;
                    }
                    if(args[1].equalsIgnoreCase("all")) {
                        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                            UserMissionStateRepository.userMissionStateHashMap.put(offlinePlayer.getUniqueId(), 0);
                            UserMissionStateRepository userMissionStateRepository = new UserMissionStateRepository();
                            userMissionStateRepository.saveUserMissionState(offlinePlayer.getUniqueId());

                            UserMissionLevelRepository.userMissionLevelHashMap.put(offlinePlayer.getUniqueId(), Integer.parseInt(args[2]));
                            UserMissionLevelRepository userMissionLevelRepository = new UserMissionLevelRepository();
                            userMissionLevelRepository.saveUserMissionLevel(offlinePlayer.getUniqueId());

                            sender.sendMessage(Reference.prefix_normal + offlinePlayer.getName() + "의 미션 레벨이 " + args[2] + "로 변경되었습니다.");
                        }
                    }
                    else {
                        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                        if(offlinePlayer == null) {
                            sender.sendMessage(Reference.prefix_error + "존재하지 않는 유저입니다.");
                            return false;
                        }
                        else {
                            UserMissionStateRepository.userMissionStateHashMap.put(offlinePlayer.getUniqueId(), 0);
                            UserMissionStateRepository userMissionStateRepository = new UserMissionStateRepository();
                            userMissionStateRepository.saveUserMissionState(offlinePlayer.getUniqueId());

                            UserMissionLevelRepository.userMissionLevelHashMap.put(offlinePlayer.getUniqueId(), Integer.parseInt(args[2]));
                            UserMissionLevelRepository userMissionLevelRepository = new UserMissionLevelRepository();
                            userMissionLevelRepository.saveUserMissionLevel(offlinePlayer.getUniqueId());

                            sender.sendMessage(Reference.prefix_normal + offlinePlayer.getName() + "의 미션 레벨이 " + args[2] + "로 변경되었습니다.");
                        }
                    }
                    return true;
                }
            }
        }
        else if (command.getName().equalsIgnoreCase("ng")) {
            if (!sender.isOp()) {
                return false;
            }
            if(args.length == 1) {
                if (args[0].equalsIgnoreCase("특수광물설정") && sender instanceof Player) {
                    SpecialOreGUI specialOreGUI = new SpecialOreGUI();
                    Player player = (Player) sender;
                    specialOreGUI.openSpecialOreSettingGUI(player, 1);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                    return true;
                }
                else if (args[0].equalsIgnoreCase("악기설정") && sender instanceof Player) {
                    MusicalInstrumentGUI musicalInstrumentGUI = new MusicalInstrumentGUI();
                    Player player = (Player) sender;
                    musicalInstrumentGUI.openMusicalInstrumentSettingGUI(player, 1);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                    return true;
                }
                else if (args[0].equalsIgnoreCase("진화서설정") && sender instanceof Player) {
                    EvolutionaryBookGUI evolutionaryBookGUI = new EvolutionaryBookGUI();
                    Player player = (Player) sender;
                    evolutionaryBookGUI.openEvolutionaryBookSettingGUI(player, 1);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                    return true;
                }
                else if (args[0].equalsIgnoreCase("특수무기설정") && sender instanceof Player) {
                    SpecialWeaponGUI specialWeaponGUI = new SpecialWeaponGUI();
                    Player player = (Player) sender;
                    specialWeaponGUI.openSpecialWeaponSettingGUI(player, 1);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                    return true;
                }
                else if (args[0].equalsIgnoreCase("악세사리설정") && sender instanceof Player) {
                    AccessoryGUI accessoryGUI = new AccessoryGUI();
                    Player player = (Player) sender;
                    accessoryGUI.openAccessorySettingGUI(player, 1);
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                    return true;
                }

            }
        }

        sendtuguideCommandHelp(sender);
        return false;
    }
    // 명령어 목록 출력
    private void sendtuguideCommandHelp(CommandSender sender) {
        sender.sendMessage("§e----- [ GuidePlugin 명령어 목록 ] -----");
        sender.sendMessage("§6/tuguide §f- 미션을 시작하거나 관련 정보를 표시합니다.");
        sender.sendMessage("§6/tuguide menu §f- 미션 GUI를 엽니다.");

        if (sender.isOp()) {
            sender.sendMessage("§6/tuguide userList §f- 모든 유저의 미션 상태와 레벨을 확인합니다.");
            sender.sendMessage("§6/tuguide reward <미션번호> §f- 미션 보상 GUI를 엽니다.");
            sender.sendMessage("§6/tuguide reset <유저명|all> §f- 특정 유저 또는 전체 유저의 미션 데이터를 초기화합니다.");
            sender.sendMessage("§6/tuguide set <유저명|all> <레벨> §f- 특정 유저 또는 전체 유저의 미션 레벨을 설정합니다.");

            sender.sendMessage("§6/ng 특수광물설정 §f- 특수 광물 GUI를 엽니다.");
            sender.sendMessage("§6/ng 악기설정 §f- 악기 GUI를 엽니다.");
            sender.sendMessage("§6/ng 진화서설정 §f- 진화서 GUI를 엽니다.");
            sender.sendMessage("§6/ng 특수무기설정 §f- 특수 무기 GUI를 엽니다.");
            sender.sendMessage("§6/ng 악세사리설정 §f- 악세사리 GUI를 엽니다.");
        }

        sender.sendMessage("§e-----------------------------------");
    }

    public static void printUserMissionInfo(CommandSender sender) {
        sender.sendMessage("§e==== [User Mission Info] ====");

        // 모든 UUID를 합쳐서 한 번에 처리 (state와 level 둘 다 확인)
        Set<UUID> allUUIDs = new HashSet<>();
        allUUIDs.addAll(UserMissionStateRepository.userMissionStateHashMap.keySet());
        allUUIDs.addAll(UserMissionLevelRepository.userMissionLevelHashMap.keySet());

        for (UUID uuid : allUUIDs) {
            int state = UserMissionStateRepository.userMissionStateHashMap.getOrDefault(uuid, 0);
            int level = UserMissionLevelRepository.userMissionLevelHashMap.getOrDefault(uuid, 0);

            // UUID → 플레이어 이름 변환
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
            String playerName = (offlinePlayer.getName() != null) ? offlinePlayer.getName() : "Unknown";

            sender.sendMessage("§7" + playerName + " §f| §bLevel: " + level + " §f| §aState: " + state);
        }
    }
}
