package kr.trueme.composetest.ui.domain.reposiotry

import kr.trueme.composetest.R
import kr.trueme.composetest.ui.domain.model.FoodImage

object DefaultFoods {

    val foodMap = mutableMapOf<String, FoodImage>()
    val categoryFoodMap = mutableMapOf<String, MutableList<FoodImage>>()


    init {
        addFood("비빔밥", "한식", FoodImage("비빔밥", R.drawable.food_bibimbap))
        addFood("갈비탕", "한식", FoodImage("갈비탕", R.drawable.food_gamjatang))
        addFood("감자탕", "한식", FoodImage("감자탕", R.drawable.food_galbitang))
        addFood("국밥", "한식", FoodImage("국밥", R.drawable.food_gukbap))
        addFood("국수", "한식", FoodImage("국수", R.drawable.food_guksu))
        addFood("냉면", "한식", FoodImage("냉면", R.drawable.food_naengmyeon))
        addFood("분식", "한식", FoodImage("분식", R.drawable.food_bunsik))
        addFood("전", "한식", FoodImage("전", R.drawable.food_jeon))
        addFood("삼계탕", "한식", FoodImage("삼계탕", R.drawable.food_samgyetang))
        addFood("보쌈", "한식", FoodImage("보쌈", R.drawable.food_bossam))
        addFood("족발", "한식", FoodImage("족발", R.drawable.food_jokbal))
        addFood("찜닭", "한식", FoodImage("찜닭", R.drawable.food_jjimtalk))

        
        addFood("짜장면", "중식", FoodImage("짜장면", R.drawable.food_jjajjangmyeon))
        addFood("마라탕", "중식", FoodImage("마라탕", R.drawable.food_maratang))
        addFood("양꼬치", "중식", FoodImage("양꼬치", R.drawable.food_yangkkochi))
        addFood("양장피", "중식", FoodImage("양장피", R.drawable.food_yangjangpi))
        addFood("짬뽕", "중식", FoodImage("짬뽕", R.drawable.food_jjamppong))
        addFood("볶음밥", "중식", FoodImage("볶음밥", R.drawable.food_bokkeumbap))
        addFood("마파두부", "중식", FoodImage("마파두부", R.drawable.food_mapadubu))

        addFood("회", "일식", FoodImage("회", R.drawable.food_hoe))
        addFood("초밥", "일식", FoodImage("초밥", R.drawable.food_chobap))
        addFood("덮밥", "일식", FoodImage("덮밥", R.drawable.food_deopbap))
        
        addFood("파스타", "양식", FoodImage("파스타", R.drawable.food_paseuta))
        addFood("스테이크", "양식", FoodImage("스테이크", R.drawable.food_seuteikeu))
        addFood("필라프", "양식", FoodImage("필라프", R.drawable.food_pillapeu))
        addFood("리조또", "양식", FoodImage("리조또", R.drawable.food_rijotto))


        addFood("쌀국수", "아시아", FoodImage("쌀국수", R.drawable.food_ssalguksu))
        addFood("팟타이", "아시아", FoodImage("팟타이", R.drawable.food_pastai))
        addFood("나시고랭", "아시아", FoodImage("나시고랭", R.drawable.food_nasigoraeng))
        addFood("똠얌꿍", "아시아", FoodImage("똠얌꿍", R.drawable.food_ttomyangkkung))
        addFood("월남쌈", "아시아", FoodImage("월남쌈", R.drawable.food_wolnamssam))
        addFood("락사", "아시아", FoodImage("락사", R.drawable.food_raksa))
        addFood("솜땀", "아시아", FoodImage("솜땀", R.drawable.food_somttam))
        
        addFood("치킨", "패스트푸드", FoodImage("치킨", R.drawable.food_chikin))
        addFood("피자", "패스트푸드", FoodImage("피자", R.drawable.food_pija))
        addFood("햄버거", "패스트푸드", FoodImage("햄버거", R.drawable.food_haembeogeo))
        addFood("샌드위치", "패스트푸드", FoodImage("샌드위치", R.drawable.food_saendeuwichi))
        addFood("핫도그", "패스트푸드", FoodImage("핫도그", R.drawable.food_deopbap))
        addFood("부리또", "패스트푸드", FoodImage("부리또", R.drawable.food_buritto))
        addFood("케밥", "패스트푸드", FoodImage("케밥", R.drawable.food_kebap))
        addFood("타코야끼", "패스트푸드", FoodImage("타코야끼", R.drawable.food_takoyakki))

        addFood("빙수", "디저트", FoodImage("빙수", R.drawable.dessert_bingsu))
        addFood("초콜릿", "디저트", FoodImage("초콜릿", R.drawable.dessert_chokollit))
        addFood("츄러스", "디저트", FoodImage("츄러스", R.drawable.dessert_chyureoseu2))
        addFood("쿠키", "디저트", FoodImage("쿠키", R.drawable.dessert_cookie))
        addFood("아이스크림", "디저트", FoodImage("아이스크림", R.drawable.dessert_icecream))
        addFood("케익", "디저트", FoodImage("케익", R.drawable.dessert_keikeu))
        addFood("마카롱", "디저트", FoodImage("마카롱", R.drawable.dessert_makarong))
        addFood("머핀", "디저트", FoodImage("머핀", R.drawable.dessert_meopin))
        addFood("팝콘", "디저트", FoodImage("팝콘", R.drawable.dessert_papkon))
        addFood("푸딩", "디저트", FoodImage("푸딩", R.drawable.dessert_puding))
        addFood("사탕", "디저트", FoodImage("사탕", R.drawable.dessert_satang))
    }

    fun containFoodByName(name: String): Boolean = foodMap.containsKey(name)

    fun getFoodsByCategory(name: String): List<FoodImage> = categoryFoodMap.getOrDefault(name, listOf())

    fun findImageByName(name: String): FoodImage? = foodMap[name]

    fun addFood(name: String, category: String, foodImage: FoodImage) {
        foodMap[name] = foodImage
        categoryFoodMap.getOrPut(category) {
            mutableListOf()
        }.also {
            it.add(foodImage)
        }
    }
}