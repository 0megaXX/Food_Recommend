package kr.trueme.composetest.ui.presentation.screen.roulette_draw

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.woong.compose.grid.SimpleGridCells
import io.woong.compose.grid.VerticalGrid
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.domain.reposiotry.DefaultFoods
import kr.trueme.composetest.ui.domain.utils.FoodBundleRandom
import kr.trueme.composetest.ui.presentation.compnent.BottomSheetDragHandle
import kr.trueme.composetest.ui.presentation.compnent.OrangeButton
import kr.trueme.composetest.ui.presentation.compnent.CategoryItem
import kr.trueme.composetest.ui.presentation.compnent.CheckCategoryGridItem
import kr.trueme.composetest.ui.presentation.compnent.CircleButton
import kr.trueme.composetest.ui.theme.Gray400
import kr.trueme.composetest.ui.theme.PretendardFamily
import kr.trueme.composetest.ui.theme.SpaceLarge

@Composable
fun RouletteCategoryDrawScreen(navController: NavController) {
    ModalBottomSheetSample(navController = navController)
}


@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ModalBottomSheetSample(navController: NavController) {
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val categoryItems = rememberSaveable {
        listOf(
            CategoryItem("한식", R.drawable.foodcategory_1, mutableStateOf(false)),
            CategoryItem("중식", R.drawable.foodcategory_2, mutableStateOf(false)),
            CategoryItem("일식", R.drawable.foodcategory_3, mutableStateOf(false)),
            CategoryItem("양식", R.drawable.foodcategory_4, mutableStateOf(false)),
            CategoryItem("패스트푸드", R.drawable.foodcategory_5, mutableStateOf(false)),
            CategoryItem("아시아", R.drawable.foodcategory_6, mutableStateOf(false)),
            CategoryItem("디저트", R.drawable.foodcategory_7, mutableStateOf(false)),
            CategoryItem("카테고리", R.drawable.foodcategory_6, mutableStateOf(false)),
        )
    }



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
                        val selectCategories = categoryItems.filter {
                            it.isChecked.value
                        }.flatMap {
                            DefaultFoods.getFoodsByCategory(it.name)
                        }.map {
                            it.name
                        }.toList()
                        val json = Json.encodeToString(selectCategories)
                        navController.navigate("random_scroll/$json")
                        //하나가 밀림
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                CircleButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "룰렛",
                    onClick = {
//                        val json = FoodBundleRandom.getJsonFromFoodBundleRandomItem(selectFoodBundle)
//                        navController.navigate("random_roulette/$json")
                    }
                )
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
                actions = {},
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp
            )
            Column(
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp),
            ) {
                Column(
                    modifier = Modifier.padding(0.dp, 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "음식 종류를 선택 해주세요",
                        style = TextStyle(
                            fontFamily = PretendardFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            lineHeight = 24.sp,
                        )
                    )
                    Text(
                        text = "선택된 음식 종류 한에서 랜덤으로 골라줘요.",
                        color = Gray400,
                        style = TextStyle(
                            fontFamily = PretendardFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                        )
                    )
                }
                Spacer(modifier = Modifier.height(36.dp))

                VerticalGrid(
                    columns = SimpleGridCells.Fixed(4),
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    categoryItems.forEach {
                        CheckCategoryGridItem(
                            categoryItem = it,
                            checked = it.isChecked.value,
                            onCheckedChange = { isChecked ->
                                it.isChecked.value = isChecked
                            },
                            modifier = Modifier,
                            onListClick = {
                                navController.navigate("roulette_category_food?categoryId=" + it.name)
                            }
                        )
                    }
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
