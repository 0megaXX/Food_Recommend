package kr.trueme.composetest.ui.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.theme.ColorScheme

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Map,
        BottomNavItem.Roulette,
        BottomNavItem.Calendar,
        BottomNavItem.Info,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .clip(
                RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)
            ),
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = Color.Black
    ) {
        items.forEachIndexed { index, item ->
            if (item.empty) {
                BottomNavigationItem(
                    selected = false,
                    enabled = false,
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = stringResource(id = item.title),
                            modifier = Modifier.width(60.dp),
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(stringResource(id = item.title))
                    },
                    alwaysShowLabel = false,
                    onClick = {

                    })
            } else {
                BottomNavigationItem(
                    selected = item.route == currentDestination?.route,
                    selectedContentColor = ColorScheme.selectIcon,
                    unselectedContentColor = Color.Black,
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = stringResource(id = item.title)
                        )
                    },
                    label = {
                        Text(stringResource(id = item.title))
                    },
                    alwaysShowLabel = true,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }

    }

}

const val HOME = "home"
const val MAP = "map"
const val ROULETTE = "roulette"
const val CALENDAR = "calendar"
const val INFO = "info"

sealed class BottomNavItem(
    @StringRes val title: Int, @DrawableRes val icon: Int, val route: String, val empty: Boolean
) {
    object Home : BottomNavItem(R.string.menu_home, R.drawable.ic_home, HOME, false)
    object Map : BottomNavItem(R.string.menu_map, R.drawable.ic_map, MAP, false)
    object Roulette : BottomNavItem(R.string.menu_calendar, R.drawable.ic_calendar, ROULETTE, true)
    object Calendar : BottomNavItem(R.string.menu_calendar, R.drawable.ic_calendar, CALENDAR, false)
    object Info : BottomNavItem(R.string.menu_info, R.drawable.ic_person, INFO, false)

}