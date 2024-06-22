package kr.trueme.composetest.ui.presentation.compnent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kr.trueme.composetest.ui.theme.Gray300
import kr.trueme.composetest.ui.theme.Gray500
import kr.trueme.composetest.ui.theme.Orange400
import kr.trueme.composetest.ui.theme.PretendardFamily

@Composable
fun CustomAlertDialog(
    title: String = "'ㅁㅁ' 음식점",
    subTitle: String = "정보를 확인하겠습니까?",
    description: String,
    onClickCancel: () -> Unit,
    onClickConfirm: () -> Unit
) {
    Dialog(onDismissRequest = onClickCancel) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = title, style = TextStyle(
                    fontFamily = PretendardFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.sp
                ))
                Text(text = subTitle, style = TextStyle(
                    fontFamily = PretendardFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.sp
                ))
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = description,
                    style = TextStyle(
                        fontFamily = PretendardFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.sp
                    ),
                    color = Gray300
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .defaultMinSize(
                                minWidth = ButtonDefaults.MinWidth,
                                minHeight = ButtonDefaults.MinHeight
                            )
                            .padding(16.dp)
                            .clickable {
                                onClickCancel()
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ){
                        Text("취소",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontFamily = PretendardFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                lineHeight = 16.sp,
                                letterSpacing = 0.sp
                            ),
                            color = Gray500
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .defaultMinSize(
                                minWidth = ButtonDefaults.MinWidth,
                                minHeight = ButtonDefaults.MinHeight
                            )
                            .padding(16.dp)
                            .clickable {
                                onClickConfirm()
                            }
                        ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text("확인하기",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontFamily = PretendardFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                lineHeight = 16.sp,
                                letterSpacing = 0.sp
                            ),
                            color = Orange400
                        )
                    }
                }
            }
        }
    }
}

data class CustomAlertDialogState(
    val title: String = "",
    val subTitle: String = "정보를 확인하겠습니까?",
    val description: String = "",
    val onClickConfirm: () -> Unit = {},
    val onClickCancel: () -> Unit = {},
    val placeUrl: String = ""
)
