package kr.trueme.composetest.ui.presentation.navigation.controller

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kr.trueme.composetest.ui.presentation.navigation.NavigationDestination

object HomeViewController : NavigationDestination(
    route = mainHomeRoute
) {
    @Composable
    override fun Render(navController: NavHostController) {
        TODO("Not yet implemented")
    }
}