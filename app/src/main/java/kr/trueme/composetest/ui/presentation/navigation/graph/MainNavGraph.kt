package kr.trueme.composetest.ui.presentation.navigation.graph

import androidx.compose.runtime.Stable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kr.trueme.composetest.ui.presentation.navigation.NavigationDestination.Companion.composable
import kr.trueme.composetest.ui.presentation.navigation.NavigationDestination.Companion.mainRoute
import kr.trueme.composetest.ui.presentation.navigation.controller.HomeViewController
import kr.trueme.composetest.ui.presentation.navigation.controller.InfoViewController

@Stable
fun NavGraphBuilder.mainGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = HomeViewController.route,
        route = mainRoute,
    ) {
        composable(
            controller = navController,
            destination = InfoViewController
        )
    }
}

