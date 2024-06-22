package kr.trueme.composetest.ui.presentation.screen.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FoodData {
    public static Map<String, Map<String, Object>> getFoodMap() {
        Map<String, Map<String, Object>> foodMap = new HashMap<>();

        foodMap.put("감자탕", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "매운맛", "담백한", "구수한", "시원한", "뜨거운", "국물", "돼지", "한식")));
            put("온도", new ArrayList<>(List.of("보통","추움")));
            put("시간대", new ArrayList<>(List.of("점심","저녁")));
        }});
        foodMap.put("비빔밥", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "뜨거운", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("물냉면", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "순한맛", "차가운", "국물", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "더움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("비빔냉면", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "단맛", "매운맛", "차가운", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "더움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("떡볶이", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("단맛", "매운맛", "뜨거운", "볶은", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "야식")));
        }});
        foodMap.put("로제떡볶이", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("단맛", "순한맛", "담백한", "뜨거운", "볶은", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "야식")));
        }});
        foodMap.put("김치전", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "매운맛", "뜨거운", "구운", "기름진", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "야식")));
        }});
        foodMap.put("전복죽", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "순한맛", "담백한", "뜨거운", "한식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("아침", "점심")));
        }});
        foodMap.put("호박죽", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "단맛", "순한맛", "담백한", "뜨거운", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("아침", "점심")));
        }});
        foodMap.put("단팥죽", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "단맛", "순한맛", "느끼한", "뜨거운", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("아침", "점심")));
        }});
        foodMap.put("잔치국수", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "순한맛", "담백한", "뜨거운", "국물", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("아침", "점심")));
        }});
        foodMap.put("비빔국수", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "짠맛", "매운맛", "차가운", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심")));
        }});
        foodMap.put("갈비탕", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("순한맛", "담백한", "구수한", "뜨거운", "기름진", "국물", "돼지", "소", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("삼겹살", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("느끼한", "뜨거운", "구운", "기름진", "돼지", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("저녁")));
        }});
        foodMap.put("삼계탕", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("담백한", "시원한", "뜨거운", "기름진", "국물", "닭", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "더움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("보쌈", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("담백한", "뜨거운", "기름진", "돼지", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("저녁", "야식")));
        }});
        foodMap.put("족발", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "뜨거운", "기름진", "돼지", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("저녁", "야식")));
        }});
        foodMap.put("찜닭", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "단맛", "매운맛", "담백한", "뜨거운", "찐", "기름진", "국물", "닭", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁", "야식")));
        }});
        foodMap.put("부대찌개", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "단맛", "담백한", "뜨거운", "국물", "돼지", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("돼지불백", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "단맛", "순한맛", "뜨거운", "볶은", "기름진", "돼지", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("갈비", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "단맛", "순한맛", "뜨거운", "구운", "기름진", "돼지", "소", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("소불고기", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "단맛", "뜨거운", "구운", "소", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("한우", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "느끼한", "담백한", "뜨거운", "구운", "기름진", "소", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("돼지국밥", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "담백한", "구수한", "뜨거운", "국물", "돼지", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁", "야식")));
        }});
        foodMap.put("소머리국밥", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "담백한", "구수한", "뜨거운", "국물", "소", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁", "야식")));
        }});
        foodMap.put("순대국밥", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "담백한", "구수한", "뜨거운", "국물", "돼지", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁", "야식")));
        }});
        foodMap.put("제육볶음", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "단맛", "매운맛", "뜨거운", "볶은", "기름진", "돼지", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁", "야식")));
        }});
        foodMap.put("육개장", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "매운맛", "구수한", "시원한", "뜨거운", "기름진", "국물", "소", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("누룽지탕", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "순한맛", "담백한", "구수한", "뜨거운", "국물", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("아침", "점심")));
        }});
        foodMap.put("김치찌개", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "매운맛", "시원한", "뜨거운", "국물", "돼지", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("된장찌개", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "순한맛", "구수한", "시원한", "뜨거운", "국물", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("닭도리탕", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "매운맛", "시원한", "뜨거운", "기름진", "국물", "닭", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("설렁탕", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "순한맛", "담백한", "뜨거운", "국물", "소", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("간장게장", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "순한맛", "차가운", "한식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("양념게장", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "단맛", "매운맛", "차가운", "한식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("꽃게탕", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "매운맛", "시원한", "뜨거운", "국물", "한식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("조개탕", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "순한맛", "담백한", "시원한", "뜨거운", "국물", "한식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("조개구이", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("순한맛", "뜨거운", "구운", "한식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("장어구이", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "순한맛", "뜨거운", "구운", "기름진", "생선", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("바지락칼국수", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "순한맛", "담백한", "시원한", "뜨거운", "국물", "한식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("라면", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "짠맛", "시원한", "매운맛", "뜨거운", "튀긴", "국물", "한식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁", "야식")));
        }});
        foodMap.put("짜장면", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "짠맛", "단맛", "순한맛", "느끼한", "뜨거운", "볶은", "기름진", "국물", "중식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("간짜장", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "짠맛", "단맛", "순한맛", "느끼한", "뜨거운", "볶은", "기름진", "중식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("사천짜장", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "짠맛", "단맛", "매운맛", "느끼한", "뜨거운", "볶은", "기름진", "중식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("해물짬뽕", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "짠맛", "매운맛", "느끼한", "시원한", "뜨거운", "기름진", "국물", "중식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("고기짬뽕", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "짠맛", "매운맛", "느끼한", "시원한", "뜨거운", "기름진", "국물", "돼지", "소", "중식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("탕수육", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("순한맛", "느끼한", "담백한", "뜨거운", "튀긴", "기름진", "돼지", "중식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("볶음밥", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "순한맛", "느끼한", "뜨거운", "볶은", "기름진", "중식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
         foodMap.put("김치볶음밥", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "매운맛", "느끼한", "뜨거운", "볶은", "기름진", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("깐풍기", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "단맛", "매운맛", "느끼한", "뜨거운", "튀긴", "볶은", "기름진", "닭", "중식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("깐쇼새우", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "매운맛", "느끼한", "뜨거운", "튀긴", "볶은", "기름진", "중식", "일식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("마라탕", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "짠맛", "매운맛", "느끼한", "뜨거운", "기름진", "국물", "소", "중식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁", "야식")));
        }});
        foodMap.put("마라샹궈", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "매운맛", "순한맛", "느끼한", "담백한", "구수한", "시원한", "뜨거운", "기름진", "국물", "소", "중식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("샤브샤브", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "순한맛", "담백한", "뜨거운", "국물", "소", "일식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("양꼬치", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("매운맛", "순한맛", "느끼한", "뜨거운", "구운", "기름진", "중식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("회", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("차가운", "생선", "일식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("저녁", "야식")));
        }});
        foodMap.put("초밥/스시", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "차가운", "생선", "일식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("제육덮밥", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "매운맛", "뜨거운", "볶은", "기름진", "돼지", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("오징어덮밥", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "매운맛", "뜨거운", "볶은", "기름진", "한식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("카츠동", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "순한맛", "느끼한", "뜨거운", "튀긴", "기름진", "돼지", "일식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("라멘", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "짠맛", "순한맛", "느끼한", "담백한", "뜨거운", "기름진", "국물", "돼지", "일식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("냉모밀", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "순한맛", "차가운", "국물", "일식")));
            put("온도", new ArrayList<>(List.of("보통", "더움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("온모밀", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "순한맛", "뜨거운", "국물", "일식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("야키소바", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "짠맛", "순한맛", "느끼한", "뜨거운", "볶은", "기름진", "돼지", "일식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("우동", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "짠맛", "순한맛", "느끼한", "시원한", "뜨거운", "기름진", "국물", "일식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("토마토스파게티", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "단맛", "순한맛", "뜨거운", "볶은", "기름진", "양식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("까르보나라", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "단맛", "순한맛", "느끼한", "뜨거운", "볶은", "기름진", "돼지", "양식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("크림스파게티", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "단맛", "순한맛", "느끼한", "뜨거운", "볶은", "기름진", "양식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("로제스파게티", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "단맛", " 순한맛", "느끼한", "뜨거운", "볶은", "기름진", "양식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("해물스파게티", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "단맛", "순한맛", "뜨거운", "볶은", "기름진", "양식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("알리오 에 올리오", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "짠맛", "순한맛", "느끼한", "뜨거운", "볶은", "기름진", "양식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("스테이크", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "순한맛", "느끼한", "뜨거운", "구운", "기름진", "소", "양식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("필라프", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "순한맛", "느끼한", "뜨거운", "볶은", "기름진", "닭", "양식", "아시안", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("리소토", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "순한맛", "느끼한", "뜨거운", "볶은", "기름진", "양식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("샐러드", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("단맛", "순한맛", "차가운", "닭",  "양식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심")));
        }});
        foodMap.put("샌드위치", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("빵", "순한맛", "차가운", "돼지", "양식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심")));
        }});
        foodMap.put("토스트", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("빵", "단맛", "순한맛", "뜨거운", "구운", "양식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심")));
        }});
        foodMap.put("멘보샤", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("빵", "짠맛", "뜨거운", "튀긴", "기름진", "중식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("후라이드치킨", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "순한맛", "느끼한", "뜨거운", "튀긴", "기름진", "닭", "패스트푸드")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("저녁", "야식")));
        }});
        foodMap.put("양념치킨", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("단맛", "매운맛", "뜨거운", "튀긴", "기름진", "닭", "패스트푸드")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("저녁", "야식")));
        }});
        foodMap.put("간장치킨", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "단맛", "순한맛", "뜨거운", "튀긴", "기름진", "닭", "패스트푸드")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("저녁", "야식")));
        }});
        foodMap.put("치즈피자", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("빵", "짠맛", "순한맛", "느끼한", "뜨거운", "구운", "기름진", "패스트푸드")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁", "야식")));
        }});
        foodMap.put("불고기피자", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("빵", "짠맛", "단맛", "순한맛", "느끼한", "뜨거운", "구운", "기름진", "소", "패스트푸드")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁", "야식")));
        }});
        foodMap.put("치킨피자", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("빵", "짠맛", "매운맛", "느끼한", "뜨거운", "구운", "기름진", "닭", "패스트푸드")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁", "야식")));
        }});
        foodMap.put("쉬림프피자", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("빵", "짠맛", "순한맛", "느끼한", "뜨거운", "구운", "기름진", "패스트푸드", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁", "야식")));
        }});
        foodMap.put("불고기버거", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("빵", "짠맛", "단맛", "순한맛", "뜨거운", "구운", "기름진", "소", "패스트푸드")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("새우버거", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("빵", "짠맛", "순한맛", "뜨거운", "튀긴", "기름진", "패스트푸드", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("치킨버거", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("빵", "짠맛", "순한맛", "뜨거운", "튀긴", "기름진", "닭", "패스트푸드")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("치즈버거", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("빵", "짠맛", "단맛", "순한맛", "느끼한", "담백한", "뜨거운", "구운", "기름진", "소", "패스트푸드")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("카레", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "단맛", "매운맛", "뜨거운", "닭", "일식", "아시안" )));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("쌀국수", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "단맛", "순한맛", "담백한", "뜨거운", "국물", "소", "아시안")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("팟타이", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("면", "짠맛", "매운맛", "느끼한", "뜨거운", "볶은", "기름진", "아시안", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("나시고렝", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "매운맛", "느끼한", "뜨거운", "볶은", "기름진", "아시안")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("타코", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("빵", "짠맛", "순한맛", "뜨거운", "구운", "기름진", "생선", "양식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심")));
        }});
        foodMap.put("부리토", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("빵", "짠맛", "순한맛", "뜨거운", "닭", "소", "양식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심")));
        }});
        foodMap.put("짜장밥", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "단맛", "순한맛", "느끼한", "뜨거운", "볶은", "기름진", "중식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("짬뽕밥", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "매운맛", "느끼한", "시원한", "뜨거운", "기름진", "국물", "중식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("만두", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("순한맛", "뜨거운", "찐", "돼지", "중식")));
            put("온도", new ArrayList<>(List.of("보통", "추움")));
            put("시간대", new ArrayList<>(List.of("아침", "점심", "저녁")));
        }});
        foodMap.put("소갈비찜", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "순한맛", "뜨거운", "찐", "기름진", "국물", "소", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("대게찜", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("짠맛", "순한맛", "느끼한", "뜨거운", "기름진", "한식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("아귀찜", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "매운맛", "시원한", "뜨거운", "찐", "생선", "한식", "해산물")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("돈까스", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "순한맛", "느끼한", "뜨거운", "튀긴", "기름진", "돼지", "일식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});
        foodMap.put("닭갈비", new HashMap<String, Object>() {{
            put("속성", new ArrayList<>(List.of("밥", "짠맛", "매운맛", "담백한", "뜨거운", "구운", "볶은", "닭", "한식")));
            put("온도", new ArrayList<>(List.of("보통")));
            put("시간대", new ArrayList<>(List.of("점심", "저녁")));
        }});

        // 여기에 더 많은 음식을 추가할 수 있습니다.

        return foodMap;

    }
}

