package kr.trueme.composetest.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

abstract class NavigationDestination(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val pathVariable: NamedNavArgument? = null,
) {

    val combinedArguments =
        arguments + if (pathVariable != null) listOf(pathVariable) else emptyList()

    private val routeWithPath =
        "${route}${if (pathVariable != null) "/{${pathVariable.name}}" else ""}"
    val routeWithQuery = if (arguments.isEmpty()) routeWithPath
    else "$routeWithPath?${arguments.joinToString("&") { "${it.name}={${it.name}}" }}"

    @Stable
    @Composable
    abstract fun Render(navController: NavHostController)

    companion object {
        internal const val mainRoute = "main"
        internal const val mainHomeRoute = "main/home"
        internal const val mainMapRoute = "main/map"
        internal const val mainCalendarRoute = "main/calendar"
        internal const val mainInfoRoute = "main/info"


        internal const val roulettePageRoute = "roulette"
        internal const val rouletteListRoute = "roulette/list"

        public fun NavGraphBuilder.composable(
            controller: NavHostController,
            destination: NavigationDestination
        ) = composable(
            route = destination.routeWithQuery,
            arguments = destination.combinedArguments,
        ) {
            destination.Render(controller)
        }

    }


}
