package kr.trueme.composetest.ui.presentation.compnent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.trueme.composetest.ui.theme.ComposeTestTheme
import kr.trueme.composetest.ui.theme.PretendardFamily

@Composable
fun CTAButton() {

}


@Composable
fun OrangeButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    shape: Shape? = null,
    onClick: () -> Unit = {}
) {
    Button(
        shape = shape ?: RoundedCornerShape(8.dp),
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = PaddingValues(32.dp, 16.dp, 32.dp, 16.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
    ) {
        ProvideTextStyle(
            value = TextStyle(
                fontFamily = PretendardFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 16.sp,
            )
        ) {
            Text(text = text)
        }
    }
}

@Composable
fun GreyButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape? = null,
    onClick: () -> Unit
) {
    Button(
        shape = shape ?: MaterialTheme.shapes.small,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
@Preview
fun `$Preview_Button`() {
    Column(modifier = Modifier.fillMaxSize()) {
        val (isButtonEnabled, onButtonEnableStateChanged) = remember { mutableStateOf(true) }
        ComposeTestTheme {
            OrangeButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), text = "확인", enabled = isButtonEnabled,
            ) {}
        }

        Spacer(modifier = Modifier.height(30.dp))
        Switch(checked = isButtonEnabled, onCheckedChange = onButtonEnableStateChanged)
    }
}