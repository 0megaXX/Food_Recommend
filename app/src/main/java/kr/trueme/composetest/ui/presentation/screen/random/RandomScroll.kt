package kr.trueme.composetest.ui.presentation.screen.random

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kr.trueme.composetest.ui.presentation.compnent.OrangeButton
import kr.trueme.composetest.ui.presentation.compnent.StandardTopBar

@Preview
@Composable
fun `$preview_RandomScroll`() {

}
@Composable
fun RandomScrollRoot(
    navController: NavController,
    items: List<String>
) {
    var chooseItem = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StandardTopBar(
            onTabLeft = {
                navController.navigateUp()
            }
        )
        RandomScroll(
            items = items,
            onClick = {
                val json = Json.encodeToString(items)
                navController.navigate("roulette/draw_result?data=${json}&chooseItem=${chooseItem.value}") {
                    popUpTo(navController.graph.findStartDestination().id) {
                    }
                }
            },
            onSelect = {
                chooseItem = mutableStateOf(it)
            }
        )
    }
}

@Composable
fun RandomScroll(
    items: List<String> = listOf(""),
    onClick: () -> Unit = {},
    onSelect: (String) -> Unit,
) {
    var item by remember {
        mutableStateOf(items.first())
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfiniteCircularList(
                width = 200.dp,
                itemHeight = 70.dp,
                items = items.toMutableList(),
                initialItem = item,
                textStyle = TextStyle(fontSize = 23.sp),
                textColor = Color.LightGray,
                selectedTextColor = Color.Black,
                onItemSelected = { i, selectItem ->
                    item = selectItem
                    onSelect(selectItem)
                },
                onFinish = onClick
            )
        }
        OrangeButton(text = "음식 고르기!",
            onClick = onClick
        )
    }

}


@Composable
fun <T> InfiniteCircularList(
    width: Dp,
    itemHeight: Dp,
    numberOfDisplayedItems: Int = 3,
    items: List<T>,
    initialItem: T,
    itemScaleFact: Float = 1.5f,
    textStyle: TextStyle,
    textColor: Color,
    selectedTextColor: Color,
    onFinish: () -> Unit = {},
    onItemSelected: (index: Int, item: T) -> Unit = { _, _ -> }
) {
    val itemHalfHeight = LocalDensity.current.run { itemHeight.toPx() / 2f }
    val scrollState = rememberLazyListState(0)
    var lastSelectedIndex by remember {
        mutableStateOf(0)
    }
    var itemsState by remember {
        mutableStateOf(items)
    }
    LaunchedEffect(items) {
        var targetIndex = items.indexOf(initialItem) - 1
        targetIndex += ((Int.MAX_VALUE / 2) / items.size) * items.size
        itemsState = items
        lastSelectedIndex = targetIndex
        scrollState.scrollToItem(targetIndex)

    }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(scrollState) {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < 5000) { // 5 seconds
            delay(30)
            coroutineScope.launch {
                val nextIndex = (lastSelectedIndex + 1) % itemsState.size
                scrollState.animateScrollToItem(nextIndex)
                lastSelectedIndex = nextIndex
                onItemSelected(nextIndex, itemsState[nextIndex % itemsState.size])
            }
        }
        delay(2000)
        onFinish()
    }

    LazyColumn(
        modifier = Modifier
            .width(width)
            .height(itemHeight * numberOfDisplayedItems),
        state = scrollState,
        flingBehavior = rememberSnapFlingBehavior(
            lazyListState = scrollState
        ),
        userScrollEnabled = false
    ) {
        items(
            count = Int.MAX_VALUE,
            itemContent = { i ->
                val item = itemsState[i % itemsState.size]
                Box(
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            val y = coordinates.positionInParent().y - itemHalfHeight
                            val parentHalfHeight =
                                (coordinates.parentCoordinates?.size?.height ?: 0) / 2f
                            val isSelected =
                                (y > parentHalfHeight - itemHalfHeight && y < parentHalfHeight + itemHalfHeight)
                            if (isSelected && lastSelectedIndex != i) {
                                onItemSelected(i % itemsState.size, item)
                                lastSelectedIndex = i
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.toString(),
                        style = textStyle,
                        color = if (lastSelectedIndex == i) {
                            selectedTextColor
                        } else {
                            textColor
                        },
                        fontSize = if (lastSelectedIndex == i) {
                            textStyle.fontSize * itemScaleFact
                        } else {
                            textStyle.fontSize
                        }
                    )
                }
            }
        )
    }
}