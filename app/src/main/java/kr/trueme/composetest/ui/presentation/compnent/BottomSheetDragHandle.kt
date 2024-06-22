package kr.trueme.composetest.ui.presentation.compnent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.trueme.composetest.ui.theme.Gray300

@Composable
fun BottomSheetDragHandle(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .padding(vertical = 22.dp),
        color = Gray300,
        shape = RoundedCornerShape(28.0.dp)
    ) {
        Box(
            Modifier
                .size(
                    width = 60.dp,
                    height = 4.dp
                )
        )
    }

}

@Preview
@Composable
fun prefive() {
    BottomSheetDragHandle()

}