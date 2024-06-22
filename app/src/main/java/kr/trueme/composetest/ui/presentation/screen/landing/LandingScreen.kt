package kr.trueme.composetest.ui.presentation.screen.landing

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AnimatedRows() {
    val firstRowOffset = remember { Animatable(0f) }
    val secondRowOffset = remember { Animatable(0f) }

    // 애니메이션 설정
    LaunchedEffect(Unit) {
        // 첫 번째 Row 왼쪽으로 이동
        firstRowOffset.animateTo(
            targetValue = -500f,
            animationSpec = tween(
                durationMillis = 5000,
                easing = LinearEasing
            )
        )
    }

    LaunchedEffect(Unit) {
        // 두 번째 Row 오른쪽으로 이동
        delay(1000) // 두 번째 Row 애니메이션을 늦추기 위해 딜레이 추가
        secondRowOffset.animateTo(
            targetValue = 500f,
            animationSpec = tween(
                durationMillis = 5000,
                easing = LinearEasing
            )
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.offset(x = firstRowOffset.value.dp)) {
            Text("ㅎㅇ")
            Text("ㅇㅇ")
            Text("ㅎㅇ")
        }
        Row(modifier = Modifier.offset(x = secondRowOffset.value.dp)) {
            Text("ㅎㅇ")
            Text("ㅇㅇ")
            Text("ㅎㅇ")
        }
    }
}
