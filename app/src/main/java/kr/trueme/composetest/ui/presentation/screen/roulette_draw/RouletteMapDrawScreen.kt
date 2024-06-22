package kr.trueme.composetest.ui.presentation.screen.roulette_draw

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.data.datasource.network.searchMap
import kr.trueme.composetest.ui.domain.utils.FoodBundleRandom
import kr.trueme.composetest.ui.presentation.compnent.BottomSheetDragHandle
import kr.trueme.composetest.ui.presentation.compnent.ChipGroup
import kr.trueme.composetest.ui.presentation.compnent.ChipGroupFlow
import kr.trueme.composetest.ui.presentation.compnent.ChipState
import kr.trueme.composetest.ui.presentation.compnent.ChipStyle
import kr.trueme.composetest.ui.presentation.compnent.CircleButton
import kr.trueme.composetest.ui.presentation.compnent.OrangeButton
import kr.trueme.composetest.ui.presentation.screen.map.components.RadiusSettingChip
import kr.trueme.composetest.ui.theme.Gray400
import kr.trueme.composetest.ui.theme.Orange400
import kr.trueme.composetest.ui.theme.PretendardFamily
import kr.trueme.composetest.ui.theme.SpaceLarge


@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun RouletteMapDrawScreen(navController: NavController) {
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BottomSheetDragHandle()
                CircleButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "기본",
                    onClick = {
//                        if (selectFoodBundle != null) {
//                            val json = FoodBundleRandom.getJsonFromFoodBundleRandomItem(selectFoodBundle)
//                            navController.navigate("random_scroll/$json")
//                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBar(
                title = {},
                navigationIcon =
                {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "back",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                },
                actions = {
                    RadiusSettingChip()
                },
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp
            )
            Column(
                modifier = Modifier.padding(16.dp, 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "음식 태그를 선택 해주세요",
                    style = TextStyle(
                        fontFamily = PretendardFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        lineHeight = 24.sp,
                    )
                )
                Text(
                    text = "선택된 음식 태그에 맞는 음식을 랜덤으로 골라줘요.",
                    color = Gray400,
                    style = TextStyle(
                        fontFamily = PretendardFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(
                            minWidth = ButtonDefaults.MinWidth,
                            minHeight = ButtonDefaults.MinHeight
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "location",
                        tint = MaterialTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "현재 위치를 기준으로 찾기",
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(36.dp))

                Column(modifier = Modifier) {
                    val categoryChips = listOf(
                        ChipState("한식"),
                        ChipState("중식"),
                        ChipState("일식"),
                        ChipState("양식"),
                        ChipState("패스트 푸드"),
                    )
                    ChipGroup(
                        elements = categoryChips,
                        chipStyle = chipStyle,
                        onChipClicked = { content, isSelected, idx ->
                            categoryChips[idx].isSelected.value = !categoryChips[idx].isSelected.value
                        }
                    )

                    val regionChips = listOf(
                        ChipState("디저트"),
                        ChipState("회식"),
                        ChipState("카페"),
                        ChipState("데이트"),
                        ChipState("친구"),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ChipGroup(
                        elements = regionChips,
                        chipStyle = chipStyle,
                        onChipClicked = { content, isSelected, idx ->
                            regionChips[idx].isSelected.value = !regionChips[idx].isSelected.value
                        }
                    )

//                    FlowRow(
//                        Modifier
//                            .fillMaxWidth(1f)
//                            .wrapContentHeight(align = Alignment.Top)
//                    ) {
//                        repeat(10) {
//                            Chip(onClick = { /*TODO*/ }) {
//                                Text(text = "ㅎㅁㅁㅁㅇ")
//                            }
//                        }
//                    }
                }

                Spacer(modifier = Modifier.height(SpaceLarge))
                OrangeButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "눌러서 음식 고르기",
                    onClick = {
                        scope.launch { state.show() }
                    }
                )
            }

        }
    }
}


val chipStyle: ChipStyle = ChipStyle(
    selectedColor = Orange400,
    unselectedColor = Color.White,
    chipTextStyle = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 14.sp,
    ),
    selectedTextColor = Color.White,
    unselectedTextColor = Orange400,
    chipModifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
)