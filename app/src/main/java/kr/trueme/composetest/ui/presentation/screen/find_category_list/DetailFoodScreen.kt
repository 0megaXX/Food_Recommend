package kr.trueme.composetest.ui.presentation.screen.find_category_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kr.trueme.composetest.ui.presentation.compnent.StandardTopBar
import kr.trueme.composetest.ui.presentation.screen.roulette_edit_create.CreateFoodBundleViewModel

@Composable
fun DetailFoodScreen(
    navController: NavController,
    viewModel: CreateFoodBundleViewModel
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StandardTopBar(
            onTabLeft = {
//                navController.previousBackStackEntry?.savedStateHandle?.set(
//                    "foodId",
//                    "item"
//                )
                viewModel.updateText("한식")
                navController.popBackStack()
            }
        )
    }
}