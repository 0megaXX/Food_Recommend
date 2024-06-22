package kr.trueme.composetest.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kr.trueme.composetest.ui.presentation.navigation.graph.landingGraph
import kr.trueme.composetest.ui.presentation.navigation.graph.mainGraph

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier,
    ) {
        landingGraph(navController = navController)
        mainGraph(navController = navController)
    }
}