package kr.trueme.composetest.ui.presentation.navigation.controller

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kr.trueme.composetest.ui.presentation.navigation.NavigationDestination
import kr.trueme.composetest.ui.presentation.screen.InfoScreen

object InfoViewController : NavigationDestination(
    route = mainInfoRoute
) {
    @Composable
    override fun Render(navController: NavHostController) {
        InfoScreen()
    }
}