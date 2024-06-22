package kr.trueme.composetest.ui.presentation.screen.roulette.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kr.trueme.composetest.ui.theme.ColorScheme
import kr.trueme.composetest.ui.theme.Gray100
import kr.trueme.composetest.ui.theme.Gray300

@Composable
fun Food(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp)
            .clip(RoundedCornerShape(12.dp, 12.dp, 12.dp, 12.dp))
            .background(ColorScheme.gray5)
            .clickable {
                onClick()
            }
    )

}

@Composable
fun FoodGuide(modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .aspectRatio(3.0f)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Gray100,
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "addFood",
                tint = Gray300
            )
            Text(text = "클릭하여 음식 목록을 추가 하세요.",
                color = Gray300
            )
        }

    }
}