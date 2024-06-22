package kr.trueme.composetest.ui.presentation.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.presentation.compnent.CircleButton
import kr.trueme.composetest.ui.presentation.compnent.StandardTabRow
import kr.trueme.composetest.ui.theme.Gray300
import kr.trueme.composetest.ui.theme.Gray400
import kr.trueme.composetest.ui.theme.Gray800
import kr.trueme.composetest.ui.theme.NanumsquareFamily
import kr.trueme.composetest.ui.theme.PretendardFamily

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "뭐 먹지?",
                    color = MaterialTheme.colors.primary,
                    style = TextStyle(
                        fontFamily = NanumsquareFamily,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp,
                        lineHeight = 24.sp,
                        letterSpacing = .5.sp
                    )
                )
            },
            actions = {
                IconButton(
                    modifier = Modifier,
                    onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = "back",
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 0.dp,
        )
        StandardTabRow(
            modifier = Modifier,
            titles = listOf("추천", "테마")
        ) { index ->
            when (index) {
                0 -> {
                    Column(modifier = Modifier.padding(16.dp, 8.dp)) {
                        ProvideTextStyle(
                            value = TextStyle(
                                fontFamily = PretendardFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                                lineHeight = 20.sp,
                                letterSpacing = 0.sp
                            )
                        ) {
                            Text(text = "오늘 뭐먹을까?")
                            Spacer(modifier = Modifier.height(8.dp))

                            TodayFoodRecommend()

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(text = "요즘 뜨고 있는 음식 \uD83D\uDD25")
                            Spacer(modifier = Modifier.height(4.dp))
                            LazyRow(modifier = Modifier.height(100.dp),
                                content = {
                                    item {
                                        RankImage(rank = 1, imageId = R.drawable.food_gukbap)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        RankImage(rank = 2, imageId = R.drawable.food_naengmyeon)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        RankImage(rank = 3, imageId = R.drawable.food_bunsik)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        RankImage(rank = 4, imageId = R.drawable.food_bossam)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        RankImage(rank = 5, imageId = R.drawable.dessert_icecream)
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(text = "아직도")
                            Row {
                                Text(text = "뭘 먹을지 고민 된다면?")
                                Spacer(modifier = Modifier.width(4.dp))
                                Image(
                                    painter = painterResource(id = R.drawable.img_thinking_face),
                                    contentDescription = "",
                                    modifier = Modifier.size(26.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Row(modifier = Modifier) {
                                Image(
                                    painter = painterResource(id = R.drawable.img_question),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .align(Alignment.Top)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(text = "음식 맞춤 추천")
                                    Text(
                                        text = "여러가지 질문으로 음식을 추천 받습니다.",
                                        style = TextStyle(
                                            fontFamily = PretendardFamily,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 12.sp,
                                            lineHeight = 12.sp,
                                            letterSpacing = 0.sp
                                        ),
                                        color = Gray400
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    CircleButton(
                                        text = "음식 추천 받기",
                                        onClick = {
                                            navController.navigate("question")
                                        }
                                    )
                                }

                            }

                        }
                    }

                }

                1 -> {
                    Column(modifier = Modifier.padding(16.dp, 8.dp)) {
                        ProvideTextStyle(
                            value = TextStyle(
                                fontFamily = PretendardFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                                lineHeight = 20.sp,
                                letterSpacing = 0.sp
                            )
                        ) {
                            Text(text = "테마")

                            Text(text = "유명인 맛집 추천")
                            Spacer(Modifier.height(8.dp))
                            LazyRow() {
                                item {
                                    FamousItem(name = "충북순대", category = "한식",
                                        address = "서울 동작구 서달로14길 16", imageId = R.drawable.famous1
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    FamousItem(name = "기라성", category = "중식",
                                        address = "창북리 880-3 기라성", imageId = R.drawable.famous2
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    FamousItem(name = "반포 미소의 집", category = "분식",
                                        address = "서울 서초구 신반포로 27-6", imageId = R.drawable.famous3
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FamousItem(
    modifier: Modifier = Modifier,
    name: String,
    category: String,
    address: String,
    @DrawableRes imageId: Int,
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        elevation = 1.dp
    ) {
        Box(
            modifier = Modifier
                .size(180.dp, 150.dp)
                .background(Color.White)
        ) {
            Column {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                )
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = name,
                        style = TextStyle(
                            fontFamily = PretendardFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            lineHeight = 16.sp,
                            letterSpacing = 0.sp
                        )
                    )
                    Text(
                        text = address,
                        style = TextStyle(
                            fontFamily = PretendardFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            lineHeight = 12.sp,
                            letterSpacing = 0.sp
                        ),
                        color = Gray300
                    )
                }
            }
        }

    }
}

@Composable
fun TodayFoodRecommend() {
    val itemName = listOf("비빔밥", "감자탕", "파스타", "국수", "라멘")
    val images = listOf(
        R.drawable.food_bibimbap,
        R.drawable.food_gamjatang,
        R.drawable.food_pastai,
        R.drawable.food_guksu,
        R.drawable.food_ramen
    )


    val pagerState = rememberPagerState(initialPage = 1) { 5 }
    HorizontalPager(
        state = pagerState,
        pageSpacing = 15.dp,
        contentPadding = PaddingValues(horizontal = 100.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            elevation = 1.dp
        ) {
            if (pagerState.currentPage == it) {
                Box(
                    modifier = Modifier
                        .size(180.dp, 170.dp)
                        .background(Color.White)
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = images[it]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(120.dp)
                                .fillMaxWidth()
                        )
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = itemName[it],
                                style = TextStyle(
                                    fontFamily = PretendardFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    lineHeight = 16.sp,
                                    letterSpacing = 0.sp
                                )
                            )
                        }

                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .size(180.dp, 150.dp)
                        .background(Color.White)
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = images[it]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth()
                        )
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = itemName[it],
                                style = TextStyle(
                                    fontFamily = PretendardFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    lineHeight = 16.sp,
                                    letterSpacing = 0.sp
                                )
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun RankImage(
    rank: Int,
    @DrawableRes imageId: Int
) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .clip(
                RoundedCornerShape(8.dp)
            )
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(30.dp)
                .background(Gray800)
                .align(Alignment.TopStart)
        ) {
            Text(
                text = "${rank}",
                color = Color.White
            )
        }
    }
}

@Composable
fun ImageWithBookmark() {
    Box(
        modifier = Modifier
            .size(150.dp)
            .clip(
                RoundedCornerShape(8.dp)
            ) // 원하는 크기로 설정
    ) {
        Image(
            painter = painterResource(id = R.drawable.food_naengmyeon), // 자신의 이미지 리소스를 사용
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .alpha(0.6f) // 불투명도 설정
                .align(Alignment.BottomEnd) // 우측 아래에 위치하도록 설정
        ) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = null,
                tint = Color.White // 아이콘 색상
            )
        }
    }
}

@Composable
private fun FoodItem() {

}