package kr.trueme.composetest.ui.presentation.compnent

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.woong.compose.grid.SimpleGridCells
import io.woong.compose.grid.VerticalGrid
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.theme.Gray300
import kr.trueme.composetest.ui.theme.Orange200
import kr.trueme.composetest.ui.theme.Orange400

data class CategoryItem(
    val name: String,
    @DrawableRes val icon: Int,
    val isChecked: MutableState<Boolean> = mutableStateOf(false),
)

@Composable
fun CategoryGridItem(
    modifier: Modifier = Modifier,
    categoryItem: CategoryItem,
    onClick: () -> Unit = {},
) {
//    var selectedState by rememberSaveable { mutableStateOf(false) }
//    val onSelectionChange = {
//        selectedState = !selectedState
//    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .height(76.dp)
                .width(76.dp)
                .clickable {
                    onClick()
                },
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            border = BorderStroke(1.dp, Gray300)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painterResource(id = categoryItem.icon),
                    contentDescription = "Post image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(32.dp),
                )
            }
        }
        Text(text = categoryItem.name)
    }
}

@Composable
fun CheckCategoryGridItem(
    categoryItem: CategoryItem,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onListClick: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .height(76.dp)
                .width(76.dp)
                .clickable {
                    if (onCheckedChange != null) {
                        run { onCheckedChange(!checked) }
                    }
                    onClick()
                },
            shape = RoundedCornerShape(12.dp),
            color = if (checked) Orange200 else Color.White,
            border = if (checked) BorderStroke(1.dp, Orange200) else BorderStroke(
                1.dp,
                Gray300
            )
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painterResource(id = categoryItem.icon),
                    contentDescription = "Post image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(32.dp),
                )
                if (checked) {
                    Surface(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .height(24.dp)
                            .clickable {
                                onListClick()
                            },
                        shape = RoundedCornerShape(6.dp),
                        color = Orange400
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "목록",
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        Text(text = categoryItem.name)
    }
}


@Composable
fun CategorySection(
    modifier: Modifier = Modifier,
    items: List<CategoryItem>
) {
    VerticalGrid(
        columns = SimpleGridCells.Fixed(4),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach {
            CategoryGridItem(Modifier, it)
        }
    }
}

@Preview
@Composable
private fun `$CategoryGridItemPreview`() {
    CategoryGridItem(Modifier, CategoryItem("한식", R.drawable.foodcategory_1))
}

@Preview
@Composable
private fun `$CategorySectionPreview`() {
    CategorySection(
        Modifier.fillMaxWidth(), listOf(
            CategoryItem("한식", R.drawable.foodcategory_1),
            CategoryItem("중식", R.drawable.foodcategory_2),
            CategoryItem("일식", R.drawable.foodcategory_3),
            CategoryItem("양식", R.drawable.foodcategory_4),
        )
    )
}