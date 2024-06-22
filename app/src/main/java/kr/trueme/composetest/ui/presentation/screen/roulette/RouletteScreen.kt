package kr.trueme.composetest.ui.presentation.screen.roulette

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle
import kr.trueme.composetest.ui.domain.utils.FoodBundleOrder
import kr.trueme.composetest.ui.domain.utils.FoodBundleRandom
import kr.trueme.composetest.ui.presentation.compnent.BottomSheetDragHandle
import kr.trueme.composetest.ui.presentation.compnent.CircleButton
import kr.trueme.composetest.ui.presentation.compnent.StandardTabRow
import kr.trueme.composetest.ui.presentation.screen.roulette.tab.draw.food_bundles.FoodBundlesViewModel
import kr.trueme.composetest.ui.presentation.screen.roulette.components.FoodGuide
import kr.trueme.composetest.ui.presentation.screen.roulette.components.MenuSelectItem
import kr.trueme.composetest.ui.presentation.screen.roulette.tab.draw.food_bundles.FoodBundlesEvent
import kr.trueme.composetest.ui.presentation.screen.roulette.tab.draw.food_bundles.components.FoodBundleItem
import kr.trueme.composetest.ui.presentation.screen.roulette.tab.draw.food_bundles.components.MenuItemType
import kr.trueme.composetest.ui.presentation.screen.roulette.tab.search.SearchTab
import kr.trueme.composetest.ui.theme.Gray500
import kr.trueme.composetest.ui.theme.Orange300
import kr.trueme.composetest.ui.theme.Orange700
import kr.trueme.composetest.ui.theme.PretendardFamily
import kr.trueme.composetest.ui.theme.SpaceSmall

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RouletteScreen(
    navController: NavController,
    viewModel: FoodBundlesViewModel = hiltViewModel()
) {

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var selectFoodBundle by remember {
        mutableStateOf<FoodBundle?>(null)
    }
    ModalBottomSheetLayout(
        sheetState = sheetState,
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
                        if (selectFoodBundle != null) {
                            val json = FoodBundleRandom.getJsonFromFoodBundleRandomItem(selectFoodBundle)
                            navController.navigate("random_scroll/$json")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                CircleButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "룰렛",
                    onClick = {
                        val json = FoodBundleRandom.getJsonFromFoodBundleRandomItem(selectFoodBundle)
                        navController.navigate("random_roulette/$json")
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

            StandardTabRow(modifier = Modifier, titles = listOf("메뉴 뽑기", "탐색", "투표")) { index ->
                when (index) {
                    0 -> Column(modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 16.dp)) {
                        MenuSelectLayout(navController, sheetState = sheetState,
                            onFoodBundleClick = { foodBundle ->
                                selectFoodBundle = foodBundle
                            },
                            viewModel
                        )
                    }

                    1 -> {
                        SearchTab(navController = navController, sheetState = sheetState)
                    }

                    2 -> {

                    }
                }

            }

        }
    }

}

@Composable
private fun FoodListHeader(
    modifier: Modifier,
    viewModel: FoodBundlesViewModel
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 8.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "목록",
            color = Color.Black,
            style = TextStyle(
                fontFamily = PretendardFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 16.sp,
            )
        )

        var isDropDownMenuExpanded by remember { mutableStateOf(false) }
        val foodBundleOrder = viewModel.state.value.foodBundleOrder
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.TopEnd
        ) {
            Row(
                modifier.clickable {
                    isDropDownMenuExpanded = true
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (foodBundleOrder is FoodBundleOrder.Date) {
                    Text(
                        text = "최신순",
                        color = Color.Black,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        textAlign = TextAlign.Right,
                    )
                } else {
                    Text(
                        text = "즐겨찾기",
                        color = Color.Black,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        textAlign = TextAlign.Right,
                    )
                }
                IconButton(
                    onClick = {
                        isDropDownMenuExpanded = true
                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_down),
                        contentDescription = "down",
                        tint = MaterialTheme.colors.onSurface
                    )
                }

            }
            OrderDropBox(
                modifier = Modifier
                    .wrapContentSize(),
                isDropDownMenuExpanded = isDropDownMenuExpanded,
                onDropDown = {
                    isDropDownMenuExpanded = false
                },
                foodBundleOrder = foodBundleOrder,
                onOrderChange = {
                    viewModel.onEvent(FoodBundlesEvent.Order(it))
                }
            )
        }

    }
}

@Composable
fun OrderDropBox(
    modifier: Modifier = Modifier,
    isDropDownMenuExpanded: Boolean,
    onDropDown: () -> Unit = {},
    foodBundleOrder: FoodBundleOrder = FoodBundleOrder.Date(OrderType.Descending),
    onOrderChange: (FoodBundleOrder) -> Unit
) {
    DropdownMenu(
        modifier = Modifier
            .wrapContentSize(),
        expanded = isDropDownMenuExpanded,
        onDismissRequest = onDropDown,
        offset = DpOffset(x = (-6).dp, y = 0.dp),
    ) {
        DropdownMenuItem(onClick = {
            onOrderChange(FoodBundleOrder.Date(foodBundleOrder.orderType))
        }) {
            Text(
                text = "최신순",
                style = if (foodBundleOrder is FoodBundleOrder.Date) TextStyle(
                    fontFamily = PretendardFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                ) else TextStyle(
                    fontFamily = PretendardFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                ),
                color = if (foodBundleOrder is FoodBundleOrder.Date) Color.Black else Gray500
            )
        }
        DropdownMenuItem(onClick = {
            onOrderChange(FoodBundleOrder.Like(foodBundleOrder.orderType))
        }) {
            Text(
                text = "즐겨찾기",
                style = if (foodBundleOrder is FoodBundleOrder.Like) TextStyle(
                    fontFamily = PretendardFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                ) else TextStyle(
                    fontFamily = PretendardFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                ),
                color = if (foodBundleOrder is FoodBundleOrder.Like) Color.Black else Gray500
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun MenuSelectLayout(
    navController: NavController,
    sheetState: ModalBottomSheetState,
    onFoodBundleClick: (FoodBundle) -> Unit = {},
    viewModel: FoodBundlesViewModel
) {

    val scope = rememberCoroutineScope()
    val foodBundlesState = viewModel.state.value
    foodBundlesState.foodBundleOrder

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    navController.navigate("create_foodbundle")
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(contentPadding = PaddingValues(all = 16.dp)) {
                item {
                    MenuSelectItem(modifier = Modifier,
                        title = "카테고리별 뽑기",
                        background = Orange300,
                        onClick = {
                            navController.navigate("roulette_category_draw")
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.foodcategory_1),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.foodcategory_2),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.foodcategory_3),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.height(SpaceSmall))
                    MenuSelectItem(
                        modifier = Modifier,
                        title = "주변 식당 뽑기",
                        background = Orange700,
                        onClick = {
                            navController.navigate("roulette_map_draw")
                        }
                    ) {

                    }
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                stickyHeader {
                    FoodListHeader(Modifier, viewModel)
                    Divider(modifier = Modifier.padding(10.dp))
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                items(foodBundlesState.foodBundles) { foodBundle ->
                    FoodBundleItem(
                        foodBundle,
                        onClick = {
                            scope.launch {
                                onFoodBundleClick(foodBundle)
                                sheetState.show()
                            }
                        },
                        onFavoriteClick = { isChecked ->
                            viewModel.onCheckedChange(foodBundle.title, !isChecked)
                        },
                        onListClick = {

                        },
                        onDropBoxClick = { type ->
                            if (type == MenuItemType.DELETE) {
                                viewModel.onEvent(FoodBundlesEvent.DeleteFoodBundle(foodBundle))
                            } else if (type == MenuItemType.EDIT) {
                                navController.navigate("create_foodbundle" +
                                        "?foodBundleId=${foodBundle.id}")
                                Log.d("aaa", foodBundle.id.toString())
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item { FoodGuide(onClick = {}) }
            }
        }
    }


}