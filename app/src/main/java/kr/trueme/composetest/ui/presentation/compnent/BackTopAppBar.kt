package kr.trueme.composetest.ui.presentation.compnent

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BackTopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    onClickBack: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor)
) {
    TopAppBar(
        modifier = modifier,
        contentColor = contentColor,
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                
            }
        },
        title = {
            ProvideTextStyle(value = MaterialTheme.typography.subtitle1) {
                title()
            }
        },
        actions = {
            ProvideTextStyle(value = MaterialTheme.typography.subtitle2) {
                actions()
            }
        }
    )
}