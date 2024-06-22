package kr.trueme.composetest.ui.presentation.screen.roulette.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.theme.Orange300
import kr.trueme.composetest.ui.theme.Orange800
import kr.trueme.composetest.ui.theme.PretendardFamily

@Composable
fun MenuSelectItem(
    modifier: Modifier,
    title: String,
    background: Color,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(75.dp)
            .background(background)
            .padding(8.dp, 4.dp, 8.dp, 4.dp)
            .clickable {
                onClick()
            }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = title,
                color = Color.White,
                style = TextStyle(
                    fontFamily = PretendardFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    letterSpacing = 0.sp,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                content()
            }
        }
        Icon(
            modifier = Modifier.align(Alignment.CenterEnd),
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = "next",
            tint = Color.White
        )
    }
}

@Composable
fun OutlineSelectItem() {

}

@Preview
@Composable
private fun MenuSelectItemPreview() {
    MenuSelectItem(
        modifier = Modifier,
        title = "카테고리별 뽑기",
        background = Orange300
    ) {

    }
}