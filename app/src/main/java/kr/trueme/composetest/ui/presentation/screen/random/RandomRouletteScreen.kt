package kr.trueme.composetest.ui.presentation.screen.random

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kr.trueme.composetest.ui.presentation.compnent.OrangeButton
import java.lang.Math.abs

@Composable
fun RandomRouletteScreen(navController: NavController) {
    val items = remember {
        ('A'..'Z').map { it.toString() }
    }

    val listState = rememberLazyListState()

    val horizontalContentPadding = 16.dp
    val boxSize = 50.dp

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val halfRowWidth = constraints.maxWidth / 2
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                LazyColumn(
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = horizontalContentPadding, vertical = 8.dp),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    itemsIndexed(items) { i, item ->
                        val opacity by remember {
                            derivedStateOf {
                                val currentItemInfo = listState.layoutInfo.visibleItemsInfo
                                    .firstOrNull { it.index == i }
                                    ?: return@derivedStateOf 0.5f
                                val itemHalfSize = currentItemInfo.size / 2
                                (1f - minOf(1f, abs(currentItemInfo.offset + itemHalfSize - halfRowWidth).toFloat() / halfRowWidth) * 0.5f)
                            }
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .scale(opacity)
                                .alpha(opacity)
                                .size(boxSize)
                        ) {
                            Text(item, color = Color.Black)
                        }
                    }
                }
                OrangeButton(text = "결정!",
                    onClick = {

                    })
            }
        }
    }

}