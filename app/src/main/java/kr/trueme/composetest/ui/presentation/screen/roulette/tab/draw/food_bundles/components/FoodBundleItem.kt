package kr.trueme.composetest.ui.presentation.screen.roulette.tab.draw.food_bundles.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle
import kr.trueme.composetest.ui.theme.ColorScheme
import kr.trueme.composetest.ui.theme.Gray100
import kr.trueme.composetest.ui.theme.Gray300
import kr.trueme.composetest.ui.theme.Gray500
import kr.trueme.composetest.ui.theme.Orange300
import kr.trueme.composetest.ui.theme.Orange400
import kr.trueme.composetest.ui.theme.PretendardFamily

@Composable
fun FoodBundleItem(
    foodBundle: FoodBundle,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onFavoriteClick: (Boolean) -> Unit = {},
    onListClick: () -> Unit = {},
    onDropBoxClick: (MenuItemType) -> Unit = {},
) {
    Box(
        modifier = modifier
            .height(86.dp)
            .clip(RoundedCornerShape(12.dp, 12.dp, 12.dp, 12.dp))
            .background(ColorScheme.gray5)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp, 64.dp)
                    .background(Orange300, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(id = R.drawable.foodcategory_1), contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(modifier = Modifier.width(34.dp))
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = foodBundle.title,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = foodBundle.tags.joinToString(" ") { "#$it" },
                    style = TextStyle(
                        fontFamily = PretendardFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        letterSpacing = 0.sp
                    ),
                    color = Gray500,
                )
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            IconButton(
                onClick = {
                    onFavoriteClick(foodBundle.isLike)
                },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                if (foodBundle.isLike) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star_fill),
                        contentDescription = "like",
                        tint = Orange400
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star_blank),
                        contentDescription = "like",
                        tint = Gray500
                    )
                }
            }

            var isDropDownMenuExpanded by remember { mutableStateOf(false) }
            IconButton(
                onClick = {
                    isDropDownMenuExpanded = true
                },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ellipsis_vertical),
                    contentDescription = "list",
                    tint = MaterialTheme.colors.onSurface
                )
                DropdownMenu(
                    modifier = Modifier
                        .wrapContentSize(),
                    expanded = isDropDownMenuExpanded,
                    onDismissRequest = { isDropDownMenuExpanded = false },
                    offset = DpOffset(x = (-16).dp, y = 0.dp),
                ) {
                    DropdownMenuItem(onClick = { onDropBoxClick(MenuItemType.EDIT) }) {
                        Text(text = "수정")
                    }
                    DropdownMenuItem(onClick = {

                    }) {
                        Text(text = "공유")
                    }
                    DropdownMenuItem(onClick = { onDropBoxClick(MenuItemType.DELETE) }) {
                        Text(text = "삭제")
                    }
                }
            }
        }

    }
}

enum class MenuItemType {
    EDIT,
    DELETE
}

@Preview
@Composable
fun `$FoodBundleItemPreview`() {
    FoodBundleItem(
        foodBundle = FoodBundle("디저트", tags = listOf("분위기", "데이트"), timestamp = System.currentTimeMillis())
    ) {

    }
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