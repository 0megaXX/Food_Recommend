package kr.trueme.composetest.ui.presentation.screen.food_choice.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.domain.model.FoodChoice

@Composable
fun CategoryFoodItem(
    foodChoice: FoodChoice,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit = {},
) {
//    var checked by rememberSaveable { mutableStateOf(foodChoice.isLike) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(id = icon),
                    contentDescription = "Post image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))

                )
                Spacer(modifier = Modifier.padding(8.dp))

                Row(modifier = Modifier) {
                    Text(
                        text = foodChoice.name,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Checkbox(
                        checked = foodChoice.isLike,
                        onCheckedChange = onCheckedChange
                    )
                }

            }
        }
        Divider()
    }

}

@Preview
@Composable
fun `$preview_CategoryFoodItem`() {
    CategoryFoodItem(
        foodChoice = FoodChoice("비빔밥", "한식"),
        icon = R.drawable.food_bibimbap,
    )
}