package kr.trueme.composetest.ui.presentation.screen.map.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.trueme.composetest.ui.theme.Gray300
import kr.trueme.composetest.ui.theme.Gray500
import kotlin.math.roundToInt

@Composable
fun RadiusSettingChip(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Surface(
        modifier = Modifier
        .clickable {
            onClick()
                   },
        shape = RoundedCornerShape(100.dp),
        border = BorderStroke(1.dp, Gray300)
    ) {
        Row(modifier = Modifier.padding(8.dp, 8.dp),
            horizontalArrangement = Arrangement.Start)
        {
            Text(text = "거리설정", color = Gray500)
        }
    }
}
