package kr.trueme.composetest.ui.presentation.screen.map.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.theme.Gray300
import kr.trueme.composetest.ui.theme.Orange300
import kr.trueme.composetest.ui.theme.Orange500
import kr.trueme.composetest.ui.theme.PretendardFamily

@Composable
fun FoodGoodPlaceItem(
    modifier: Modifier = Modifier,
    name: String,
    categories: List<String> = listOf(),
    address: String = "위치없음",
    distance: Int = 0,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.clickable {
            onClick()
        },
        elevation = 2.dp,
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = name,
                    style = TextStyle(
                        fontFamily = PretendardFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        letterSpacing = 0.sp
                    ))
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = categories.joinToString(" ") { "#$it" },
                style = TextStyle(
                    fontFamily = PretendardFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.sp
                ),
                color = Orange500)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProvideTextStyle(
                    value = TextStyle(
                        fontFamily = PretendardFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp,
                        lineHeight = 20.sp,
                        letterSpacing = 0.sp
                    )
                ) {
                    Text(
                        text = address,
                        color = Gray300,
                        modifier = Modifier.weight(1f),
                    )
                    Text(text = "${distance}m",
                        color = Gray300,
                        textAlign = TextAlign.Right)
                }
            }
        }

    }
}

@Preview
@Composable
fun `preview_FoodGoodPlaceItem`() {
    FoodGoodPlaceItem(modifier = Modifier.fillMaxWidth(),
        name = "음식점")
}