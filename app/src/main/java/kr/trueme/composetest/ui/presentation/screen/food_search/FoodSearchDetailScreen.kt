package kr.trueme.composetest.ui.presentation.screen.food_search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.presentation.compnent.OrangeButton
import kr.trueme.composetest.ui.presentation.compnent.StandardTopBar
import kr.trueme.composetest.ui.theme.Orange300

@Composable
fun FoodSearchDetailScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StandardTopBar(
            onTabLeft = {
                navController.navigateUp()
            }
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Surface(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Orange300)
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = null)
                }
            }

            OrangeButton(
                modifier = Modifier.weight(2f),
                text = "다운로드"
            )
        }
    }
}