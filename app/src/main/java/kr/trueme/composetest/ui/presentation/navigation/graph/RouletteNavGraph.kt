package kr.trueme.composetest.ui.presentation.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import kr.trueme.composetest.ui.presentation.navigation.NavigationDestination.Companion.roulettePageRoute

fun NavGraphBuilder.rouletteGraph(
    navController: NavController
) {
    navigation(startDestination = "select", route = roulettePageRoute) {

    }
}