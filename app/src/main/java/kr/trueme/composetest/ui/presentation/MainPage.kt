package kr.trueme.composetest.ui.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.serialization.json.Json
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.presentation.navigation.BottomNavBar
import kr.trueme.composetest.ui.presentation.screen.CalendarScreenRoot
import kr.trueme.composetest.ui.presentation.screen.HomeScreen
import kr.trueme.composetest.ui.presentation.screen.InfoScreen
import kr.trueme.composetest.ui.presentation.screen.find_category_list.CustomFoodEditScreen
import kr.trueme.composetest.ui.presentation.screen.find_category_list.DetailFoodScreen
import kr.trueme.composetest.ui.presentation.screen.find_category_list.FoodCategoryListScreen
import kr.trueme.composetest.ui.presentation.screen.find_map.FindFoodByMapScreen
import kr.trueme.composetest.ui.presentation.screen.food_choice.CategoryFoodsScreen
import kr.trueme.composetest.ui.presentation.screen.map.MapScreen
import kr.trueme.composetest.ui.presentation.screen.question.FoodQuestionScreen
import kr.trueme.composetest.ui.presentation.screen.random.CounterScreen
import kr.trueme.composetest.ui.presentation.screen.random.MainViewModel
import kr.trueme.composetest.ui.presentation.screen.random.RandomRouletteScreen
import kr.trueme.composetest.ui.presentation.screen.random.RandomScreen
import kr.trueme.composetest.ui.presentation.screen.random.RandomScroll
import kr.trueme.composetest.ui.presentation.screen.random.RandomScrollRoot
import kr.trueme.composetest.ui.presentation.screen.random.RouletteWheel
import kr.trueme.composetest.ui.presentation.screen.restaurant.RestaurantScreen
import kr.trueme.composetest.ui.presentation.screen.roulette.RouletteScreen
import kr.trueme.composetest.ui.presentation.screen.roulette.tab.draw.food_bundles.FoodBundleScreen
import kr.trueme.composetest.ui.presentation.screen.roulette_draw.RouletteCategoryDrawScreen
import kr.trueme.composetest.ui.presentation.screen.roulette_draw.RouletteDrawResultScreen
import kr.trueme.composetest.ui.presentation.screen.roulette_draw.RouletteMapDrawScreen
import kr.trueme.composetest.ui.presentation.screen.roulette_edit_create.CreateFoodBundleScreen
import kr.trueme.composetest.ui.presentation.screen.roulette_edit_create.CreateFoodBundleViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainPage(
    navController: NavHostController,
    showBottomBar: Boolean = true
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(navController)
            }
        },
        floatingActionButton = {
            if (showBottomBar) {
                FloatingActionButton(
                    modifier = Modifier
//                        .background(
//                            brush = Brush.linearGradient(colors = listOf(Color.Yellow, Color.White))
//                        )
                        .height(60.dp)
                        .width(60.dp),
                    onClick = {
                        navController.navigate("roulette")
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_roulette),
                        contentDescription = "fab",
                        tint = Color.White
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {


            val viewModel: CreateFoodBundleViewModel = hiltViewModel()
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable(route = "home") {
                    HomeScreen(navController = navController)
                }

                composable(route = "question") {
                    FoodQuestionScreen(navController)
                }
                composable(route = "map") {
                    MapScreen(navController = navController)
                }
                composable(route = "calendar") {
                    CalendarScreenRoot()
                }
                composable(route = "info") {
                    InfoScreen()
                }
                composable(route = "roulette") {
                    RouletteScreen(navController = navController)
                }
                composable(route = "roulette_category_draw") {
                    RouletteCategoryDrawScreen(navController = navController)
                }
                composable(route = "roulette_map_draw") {
                    RouletteMapDrawScreen(navController = navController)
                }
                composable(route = "foodbundles") {
                    FoodBundleScreen(navController = navController)
                }
                composable(
                    route = "create_foodbundle" + "?foodBundleId={foodBundleId}",
                    arguments = listOf(
                        navArgument(
                            name = "foodBundleId"
                        ) {
                            type = NavType.IntType
                            defaultValue = -1
                        },
                    )
                ) { backStackEntry ->
                    CreateFoodBundleScreen(
                        navController = navController,
                        hiltViewModel(backStackEntry)
                    )
                }
                composable(route = "detail_food") {
                    DetailFoodScreen(navController = navController, viewModel)
                }
                composable(route = "roulette_category_food?categoryId={categoryId}",
                    arguments = listOf(
                        navArgument(name = "categoryId") {
                            type = NavType.StringType
                            defaultValue = "한식"
                        }
                    )
                ) {
                    CategoryFoodsScreen(
                        navController = navController
                    )
                }

                composable(route = "find_food_by_category") { backStackEntry ->
                    val viewModel: CreateFoodBundleViewModel =
                        hiltViewModel(navController.previousBackStackEntry!!)
                    FoodCategoryListScreen(navController = navController, viewModel)
                }
                composable(route = "find_food_by_map") {
                    FindFoodByMapScreen(navController = navController)
                }
                composable(route = "custom_food_edit") {
                    CustomFoodEditScreen(navController = navController)
                }

                composable(route = "random") {
                    RandomScreen(MainViewModel())
                }
                composable(route = "random2") {
                    RandomRouletteScreen(navController = navController)
                }
                composable(route = "random3") {
                    CounterScreen()
                }
                composable(
                    route = "roulette/draw_result?data={data}&chooseItem={chooseItem}",
                    arguments = listOf(
                        navArgument("data") {
                            type = NavType.StringType
                        },
                        navArgument(
                            name = "chooseItem"
                        ) {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                        )
                ) { backStackEntry ->
                    val json = backStackEntry.arguments?.getString("data")
                    val data = json?.let { Json.decodeFromString<List<String>>(it) }

                    val chooseItem = backStackEntry.arguments?.getString("chooseItem")
                    data?.let {
                        if (chooseItem != null) {
                            RouletteDrawResultScreen(navController = navController, it, chooseItem)
                        }
                    }
                }

                composable(
                    route = "random_scroll/{data}",
                    arguments = listOf(navArgument("data") { type = NavType.StringType })
                ) { backStackEntry ->
                    val json = backStackEntry.arguments?.getString("data")
                    val data = json?.let { Json.decodeFromString<List<String>>(it) }
                    data?.let { RandomScrollRoot(navController = navController, it) }
                }

                composable(
                    route = "random_roulette/{data}",
                    arguments = listOf(navArgument("data") { type = NavType.StringType })
                ) { backStackEntry ->
                    val json = backStackEntry.arguments?.getString("data")
                    val data = json?.let { Json.decodeFromString<List<String>>(it) }
                    RouletteWheel()
                }

                composable(route = "restaurant?url={url}",
                    arguments = listOf(
                        navArgument(name = "url") {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    )) {
                    val url = it.arguments?.getString("url") ?: ""
                    RestaurantScreen(navController, url)
                }
            }
        }
    }
}

