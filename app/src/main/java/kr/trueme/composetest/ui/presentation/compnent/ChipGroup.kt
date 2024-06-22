package kr.trueme.composetest.ui.presentation.compnent

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun ChipGroup(
    modifier: Modifier = Modifier,
    elements: List<ChipState>,
    chipStyle: ChipStyle,
    onChipClicked: (String, Boolean, Int) -> Unit,
) {
    LazyRow(modifier = modifier) {
        items(elements.size) { idx ->
            Chip(
                text = elements[idx].text,
                selected = elements[idx].isSelected.value,
                selectedColor = chipStyle.selectedColor,
                unselectedColor = chipStyle.unselectedColor,
                chipTextStyle = chipStyle.chipTextStyle,
                selectedTextColor = chipStyle.selectedTextColor,
                unselectedTextColor = chipStyle.unselectedTextColor,
                chipModifier = chipStyle.chipModifier,
                onChipClicked = { content, isSelected ->
                    onChipClicked(content, isSelected, idx)
                }
            )
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipGroupFlow(
    modifier: Modifier = Modifier,
    elements: List<ChipState>,
    chipStyle: ChipStyle,
    onChipClicked: (String, Boolean, Int) -> Unit,
) {
    FlowRow(modifier = modifier.fillMaxWidth(1f)
        .wrapContentHeight(align = Alignment.Top)) {
        repeat(elements.size) {idx ->
            Chip(
                text = elements[idx].text,
                selected = elements[idx].isSelected.value,
                selectedColor = chipStyle.selectedColor,
                unselectedColor = chipStyle.unselectedColor,
                chipTextStyle = chipStyle.chipTextStyle,
                selectedTextColor = chipStyle.selectedTextColor,
                unselectedTextColor = chipStyle.unselectedTextColor,
                chipModifier = chipStyle.chipModifier,
                onChipClicked = { content, isSelected ->
                    onChipClicked(content, isSelected, idx)
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
private fun Chip(
    text: String,
    selected: Boolean,
    selectedColor: Color,
    unselectedColor: Color,
    chipTextStyle: TextStyle,
    selectedTextColor: Color,
    unselectedTextColor: Color,
    @SuppressLint("ModifierParameter")
    chipModifier: Modifier,
    modifier: Modifier = Modifier,
    onChipClicked: (String, Boolean) -> Unit,
) {
    Surface(
        color = when {
            selected -> selectedColor
            else -> unselectedColor
        },
        shape = RoundedCornerShape(100.dp),
        modifier = modifier,
        border = when {
            selected -> null
            else -> BorderStroke(1.dp, selectedColor)
        }
    ) {
        Text(
            text = text,
            color = when {
                selected -> selectedTextColor
                else -> unselectedTextColor
            },
            style = chipTextStyle,
            modifier = chipModifier
                .clickable { onChipClicked(text, selected) }
        )
    }
}

data class ChipState(
    var text: String,
    val isSelected: MutableState<Boolean> = mutableStateOf(false)
)

@Composable
fun IconChipGroup(
    modifier: Modifier = Modifier,
    elements: List<IconChipState>,
    chipStyle: ChipStyle,
    onChipClicked: (String, Boolean, Int) -> Unit,
) {
    LazyRow(modifier = modifier) {
        items(elements.size) { idx ->
            IconChip(
                text = elements[idx].text,
                selected = elements[idx].isSelected.value,
                selectedIcon = elements[idx].selectedIcon,
                selectedColor = chipStyle.selectedColor,
                unselectedIcon = elements[idx].unselectedIcon,
                unselectedColor = chipStyle.unselectedColor,
                chipTextStyle = chipStyle.chipTextStyle,
                selectedTextColor = chipStyle.selectedTextColor,
                unselectedTextColor = chipStyle.unselectedTextColor,
                chipModifier = chipStyle.chipModifier,
                onChipClicked = { content, isSelected ->
                    onChipClicked(content, isSelected, idx)
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
private fun IconChip(
    text: String,
    selected: Boolean,
    @DrawableRes selectedIcon: Int,
    selectedColor: Color,
    @DrawableRes unselectedIcon: Int,
    unselectedColor: Color,
    chipTextStyle: TextStyle,
    selectedTextColor: Color,
    unselectedTextColor: Color,
    @SuppressLint("ModifierParameter")
    chipModifier: Modifier,
    modifier: Modifier = Modifier,
    onChipClicked: (String, Boolean) -> Unit,
) {
    Surface(
        color = when {
            selected -> selectedColor
            else -> unselectedColor
        },
        shape = RoundedCornerShape(100.dp),
        modifier = modifier
            .clickable { onChipClicked(text, selected) }
    ) {
        Row(modifier = Modifier.padding(8.dp, 4.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (selected) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(26.dp)
                        .background(Color.White, CircleShape)
                ){
                    Image(painter = painterResource(id = selectedIcon), contentDescription = "i",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }

            } else {
                Image(painter = painterResource(id = unselectedIcon), contentDescription = "a",
                    modifier = Modifier
                        .size(20.dp))
            }

            Text(
                text = text,
                color = when {
                    selected -> selectedTextColor
                    else -> unselectedTextColor
                },
                style = chipTextStyle,
                modifier = chipModifier
            )
        }
    }
}

data class IconChipState(
    var text: String,
    val category: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val isSelected: MutableState<Boolean>
)

data class ChipStyle(
    val selectedColor: Color,
    val unselectedColor: Color,
    val chipTextStyle: TextStyle,
    val selectedTextColor: Color,
    val unselectedTextColor: Color,
    val chipModifier: Modifier = Modifier,
)