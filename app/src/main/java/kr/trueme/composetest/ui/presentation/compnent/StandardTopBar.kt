package kr.trueme.composetest.ui.presentation.compnent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kr.trueme.composetest.R

@Composable
fun StandardTopBar(
    onTabLeft: () -> Unit = {},
    title: String = ""
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon =
        {
            IconButton(onClick = {
                onTabLeft()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "back",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        },
        actions = {
            IconButton(onClick = {

            }) {

            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}