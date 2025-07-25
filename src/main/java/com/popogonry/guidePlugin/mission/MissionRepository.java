package com.popogonry.guidePlugin.mission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MissionRepository {
    public static final HashMap<Integer, Mission> missionMap = new HashMap<>();

    public void initMission() {
        List<String> description = new ArrayList<>();

        // 1. 위키를 입력하여 서버 위키 확인하기
        description.add("§7서버 채팅창에 §a/위키 입력후 채팅에 나오는 클릭");
        description.add("§7메시지 클릭하여 위키 주소를 복사 혹은 이동합니다.");
        missionMap.put(1, new Mission(1, "위키를 입력하여 서버 위키 확인하기", description, 0));

        // 2. 야생 랜덤티피 이동하기
        description = new ArrayList<>();
        description.add("§a메뉴 §f-> §a이동 §f-> §a야생월드 §7클릭하여 이동할수 있습니다.");
        missionMap.put(2, new Mission(2, "야생 랜덤티피 이동하기", description, 0));

        // 3. 철 구하기
        description = new ArrayList<>();
        description.add("§7철 주괴를 흭득하면 됩니다.");
        missionMap.put(3, new Mission(3, "철 구하기", description, 0));

        // 4. 경험치 1,000 흭득하기
        description = new ArrayList<>();
        description.add("§7경험치를 §e1,000 §7흭득하면 됩니다.");
        description.add("§7(해당 미션을 받고부터 측정이 됩니다.)");
        missionMap.put(4, new Mission(4, "경험치 1,000 흭득하기", description, 1000));

        // 5. 임무지 구매하기
        description = new ArrayList<>();
        description.add("§a메뉴 §f-> §a퀘스트 §f-> §a임무 §7클릭하고 가운데 종이를 클릭하면");
        description.add("§e1,000 §7경험치를 지불하고 임무지를 구매할 수 있습니다.");
        missionMap.put(5, new Mission(5, "임무지 구매하고, 우클릭하기", description, 0));

        // 6. 임무지 미션 클리어하기
        description = new ArrayList<>();
        description.add("§7임무지에 적혀있는 임무들을 완수하고 보상을 받으세요");
        missionMap.put(6, new Mission(6, "임무지 미션 클리어하기", description, 0));

        // 7. 마을 생성 / 마을 가입하기
        description = new ArrayList<>();
        description.add("§7마을월드에서 §e/lands create (마을이름) §7으로 마을을 생성하거나");
        description.add("§e/lands create (마을이름) §7으로 마을을 생성하거나");
        description.add("§7다른 마을의 초대를 받고 §e/lands accept §7를 입력하여");
        description.add("§7마을 멤버로 가입하세요");
        missionMap.put(7, new Mission(7, "마을 생성 / 마을 가입하기", description, 0));

        // 8. 땅 구매하기
        description = new ArrayList<>();
        description.add("§e/lands claim §7으로 땅을 구매하세요");
        missionMap.put(8, new Mission(8, "땅 구매하기", description, 0));

        // 9. 아무 농작물 10번 수확하기
        description = new ArrayList<>();
        description.add("§7기존 바닐라 농작물 다 자란 작물 §e10개§7를 수확하면 됩니다.");
        missionMap.put(9, new Mission(9, "아무 농작물 10번 수확하기", description, 10));

        // 10. 도구에 인첸트 10번 하기
        description = new ArrayList<>();
        description.add("§7도구 §a곡괭이§f,§a도끼§f,§a괭이§f,§a삽 §7을 종류상관 없이");
        description.add("§7인첸트 테이블 에서 인첸트 §e10번 §7하면 됩니다.");
        missionMap.put(10, new Mission(10, "도구에 인첸트 10번 하기", description, 10));

        // 11. 특수 인첸트 붙이기 3회 성공하기
        description = new ArrayList<>();
        description.add("§7도구 무기 상관 없이 인첸트를 시도해서 특수인첸트를");
        description.add("§e3회 §7붙이면 클리어 됩니다.");
        missionMap.put(11, new Mission(11, "특수 인첸트 붙이기 3회 성공하기", description, 3));

        // 12. 특수광물 하나 흭득하기
        description = new ArrayList<>();
        description.add("§7기존에 광물들 §a석탄§f,§a철§f,§a금§f,§a구리§f,§a에메랄드§f,§a다이아");
        description.add("§7등등 광석들을 캐면 낮은 확률로 특수광물이 나옵니다.");
        missionMap.put(12, new Mission(12, "특수광물 하나 흭득하기", description, 0));

        // 13. 열대어 구하기
        description = new ArrayList<>();
        description.add("§7열대어 물고기를 흭득하면 됩니다.");
        missionMap.put(13, new Mission(13, "열대어 구하기", description, 0));

        // 14. 악기 1회 이상 연주하기
        description = new ArrayList<>();
        description.add("§7악기를 휙득하고 우클릭 하여 연주 하면 됩니다.");
        missionMap.put(14, new Mission(14, "악기 1회 이상 연주하기", description, 0));

        // 15. 특수작물 10회 이상 수확하기
        description = new ArrayList<>();
        description.add("§7경작지를 흭득하여 경작지에 물을 뿌리고");
        description.add("§7그 위에 특수작물 씨앗을 심어 농사를 할수 있습니다.");
        missionMap.put(15, new Mission(15, "특수작물 10회 이상 수확하기", description, 0));

        // 16. 종류 상관 없이 아무 스텟 스킬 30레벨 달성
        description = new ArrayList<>();
        description.add("§a메뉴 §f-> §a내정보 §f-> §a내스킬 §7에서");
        description.add("§7아무 스킬 레벨을 §e30레벨 §7달성하면 클리어 입니다.");
        missionMap.put(16, new Mission(16, "종류 상관 없이 아무 스텟 스킬 30레벨 달성", description, 0));

        // 17. 악세사리 흭득 후 강화 1회 시도하기
        description = new ArrayList<>();
        description.add("§7악세사리 흭득 방법은 악세사리 랜덤박스에서 랜덤하게");
        description.add("§7흭득할 수 있습니다. 악세사리 랜덤박스는");
        description.add("§7기본/프리미엄 보급상자에서 흭득 가능합니다.");
        missionMap.put(17, new Mission(17, "악세사리 흭득 후 강화 1회 시도하기", description, 0));

        // 18. 특수무기 레시피를 흭득하여 특수무기 제작하기
        description = new ArrayList<>();
        description.add("§7세계에는 알려지지 않은 비밀 레시피가 존재 합니다");
        description.add("§7해당 레시피를 얻어 특수무기를 제작해 보세요");
        description.add("§7특수무기는 특수광물로 제작할 수 있습니다.");
        description.add("§7이것 저것 조합을 시도해 보셔도 좋습니다.");
        missionMap.put(18, new Mission(18, "특수무기 레시피를 흭득하여 특수무기 제작하기", description, 0));

        // 19. 무한의탑 도전하기
        description = new ArrayList<>();
        description.add("§7무한의 탑 입장권 우클릭 시 무한의 탑에 입장이 가능합니다.");
        missionMap.put(19, new Mission(19, "무한의탑 도전하기", description, 0));

        // 20. 특수무기 진화 1회 성공하기
        description = new ArrayList<>();
        description.add("§7진화서를 특수무기에 끌어와 클릭시 진화를 시도 합니다.");
        description.add("§7진화서의 경우에는 보통 기본/프리미엄 박스에서 흭득 가능합니다.");
        missionMap.put(20, new Mission(20, "특수무기 진화 1회 성공하기", description, 0));

        // 21. 다음 준비중
        description = new ArrayList<>();
        description.add("§7이후 미션은 추가 예정입니다!");
        missionMap.put(21, new Mission(21, "미션이 모두 완료되었습니다!", description, 0));

    }
}
