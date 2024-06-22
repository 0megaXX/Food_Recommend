package kr.trueme.composetest.ui.presentation.navigation.controller

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kr.trueme.composetest.ui.presentation.navigation.NavigationDestination
import kr.trueme.composetest.ui.presentation.screen.CalendarScreenRoot

object CalendarController : NavigationDestination(
    route = mainCalendarRoute
) {
    @Composable
    override fun Render(navController: NavHostController) {
        CalendarScreenRoot()
    }

}