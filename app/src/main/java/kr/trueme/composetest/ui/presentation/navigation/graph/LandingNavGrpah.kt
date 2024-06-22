package kr.trueme.composetest.ui.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import kr.trueme.composetest.ui.presentation.navigation.NavigationDestination
import kr.trueme.composetest.ui.presentation.navigation.controller.HomeViewController


fun NavGraphBuilder.landingGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = HomeViewController.route,
        route = NavigationDestination.mainRoute,
    ) {

    }
}