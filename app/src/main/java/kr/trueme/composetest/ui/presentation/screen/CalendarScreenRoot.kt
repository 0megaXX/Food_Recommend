package kr.trueme.composetest.ui.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import java.time.YearMonth

@Composable
fun CalendarScreenRoot() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "음식 기록장",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            navigationIcon =
            {
                IconButton(onClick = {

                }) {

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

        SelectableCalendar(
            monthHeader = {}
        )
    }

}


@Composable
fun CalendarScreen(
    initialYearMonth: YearMonth = remember { YearMonth.now() },
) {

}

@Composable
fun CalendarHeader(
    yearMonthState: YearMonth,
) {

}


