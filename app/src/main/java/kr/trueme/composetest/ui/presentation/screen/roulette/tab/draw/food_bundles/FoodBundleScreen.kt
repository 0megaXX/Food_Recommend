package kr.trueme.composetest.ui.presentation.screen.roulette.tab.draw.food_bundles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.trueme.composetest.ui.presentation.screen.roulette.tab.draw.food_bundles.components.FoodBundleItem
import kr.trueme.composetest.ui.presentation.screen.roulette.components.FoodGuide

@Composable
fun FoodBundleScreen(
    navController: NavController,
    viewModel: FoodBundlesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

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
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.foodBundles) {foodBundle ->
                    FoodBundleItem(
                        foodBundle,
                        onFavoriteClick = {

                        },
                        onListClick = {

                        }
                    )
                }
                item { FoodGuide(onClick = {}) }
            }
        }
    }

}