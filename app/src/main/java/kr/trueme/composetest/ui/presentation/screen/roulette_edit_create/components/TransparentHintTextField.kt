package kr.trueme.composetest.ui.presentation.screen.roulette_edit_create.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.theme.Gray400
import kr.trueme.composetest.ui.theme.Orange400

@Composable
fun TransparentHintTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    maxLength: Int = 40,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.onBackground
    ),
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .border(1.dp, Orange400, RoundedCornerShape(8.dp))
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .onFocusChanged {
                    onFocusChange(it)
                }
        )
        if(isHintVisible) {
            Text(text = hint, style = textStyle, color = Gray400)
        }
        if(!isHintVisible) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_circle_close),
                    contentDescription = "back",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}