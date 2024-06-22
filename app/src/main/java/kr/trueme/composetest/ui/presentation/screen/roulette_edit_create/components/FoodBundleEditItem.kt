package kr.trueme.composetest.ui.presentation.screen.roulette_edit_create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.domain.model.foodbundle.CustomFood
import kr.trueme.composetest.ui.theme.NanumsquareFamily
import kr.trueme.composetest.ui.theme.Orange50
import kr.trueme.composetest.ui.theme.Orange600

@Composable
fun FoodBundleEditItem(
    customFood: CustomFood,
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit = {}
) {
    Box(modifier = modifier
        .defaultMinSize(minHeight = 90.dp)
        .background(Orange50, RoundedCornerShape(8.dp))
        .padding(10.dp)
    ) {
        Text(text = customFood.name,
            style = TextStyle(
                fontFamily = NanumsquareFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.sp
            )
        )
        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = onCloseClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "close",
            )
        }
        Row(
            modifier = Modifier.align(Alignment.BottomStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location_marker),
                contentDescription = null,
                tint = Orange600
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "위치 없음")
        }
    }
}

@Preview
@Composable
fun `Preview_FoodBundleEditItem`() {
    FoodBundleEditItem(CustomFood(name = "aa"))
}