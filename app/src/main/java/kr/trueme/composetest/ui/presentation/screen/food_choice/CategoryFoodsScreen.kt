package kr.trueme.composetest.ui.presentation.screen.food_choice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.domain.model.FoodChoice
import kr.trueme.composetest.ui.domain.model.FoodImage
import kr.trueme.composetest.ui.domain.reposiotry.DefaultFoods

@Composable
fun CategoryFoodsScreen(
    navController: NavController,
    viewModel: CategoryFoodsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = {
                Text(text = viewModel.categoryId,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            navigationIcon =
            {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = null)
                }
            },
            actions = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {

                }
            },
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 0.dp
        )

        val foods = state.foods

        LazyColumn(modifier = Modifier.fillMaxWidth()) {

            items(foods) { foodChoice ->
                val foodImage = DefaultFoods.findImageByName(foodChoice.name)?: FoodImage("한식", R.drawable.food_bibimbap)
                CategoryFoodItem(
                    foodChoice = foodChoice,
                    icon = foodImage.imageId,
                    onCheckBoxClick = { isChecked ->
                        viewModel.onCheckedChange(foodChoice.name, category = foodChoice.category, isChecked)
                    }
                )
            }
        }
    }

}


@Composable
fun CategoryFoodItem(
    foodChoice: FoodChoice,
    modifier: Modifier = Modifier,
    icon: Int,
    onCheckBoxClick: (Boolean) -> Unit = {}
) {

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
            Text(
                text = foodChoice.name,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Checkbox(
                checked = foodChoice.isSelected,
                onCheckedChange = onCheckBoxClick
            )
        }
    }

    Divider()

}


@Preview
@Composable
fun `$CategoryFoodItemPreview`() {
//    CategoryFoodItem(
//        foodChoice = FoodChoice("비빔밥", "한식"),
//        icon = R.drawable.food_bibimbap,
//        checked = false,
//    )
}
