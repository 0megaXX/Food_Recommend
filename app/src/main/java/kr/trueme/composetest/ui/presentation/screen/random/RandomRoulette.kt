package kr.trueme.composetest.ui.presentation.screen.random

import android.graphics.Path
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.rotate
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.presentation.compnent.CustomAlertDialog
import kr.trueme.composetest.ui.presentation.compnent.CustomAlertDialogState
import kr.trueme.composetest.ui.presentation.compnent.OrangeButton
import kr.trueme.composetest.ui.theme.Orange100
import kr.trueme.composetest.ui.theme.Orange200
import kr.trueme.composetest.ui.theme.Orange300
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

@Composable
fun RouletteWheel() {
    var availableItems by remember {
        mutableStateOf(
            listOf(
                "비빔밥",
                "김치찌개",
                "불고기",
                "갈비탕",
                "된장찌개",
                "떡볶이",
                "설렁탕",
                "돼지국밥",
                "잡채",
                "냉면",
                "짜장면",
                "짬뽕",
                "탕수육",
                "마파두부",
                "마라탕",
                "만두",
                "초밥",
                "라멘",
                "우동",
                "사시미",
                "텐동",
                "규동",
                "스테이크",
                "파스타",
                "피자",
                "햄버거",
                "샐러드",
                "라자냐",
                "리조또"
            )
        )
    }

    val foodEmojis = mapOf(
        "비빔밥" to "🍲",
        "김치찌개" to "🍲",
        "불고기" to "🍖",
        "갈비탕" to "🍲",
        "된장찌개" to "🍲",
        "떡볶이" to "🌶️",
        "설렁탕" to "🍲",
        "돼지국밥" to "🍲",
        "잡채" to "🍜",
        "냉면" to "🍜",
        "짜장면" to "🍜",
        "짬뽕" to "🍜",
        "탕수육" to "🍖",
        "마파두부" to "🍲",
        "마라탕" to "🍲",
        "만두" to "🥟",
        "초밥" to "🍣",
        "라멘" to "🍜",
        "우동" to "🍜",
        "사시미" to "🍣",
        "텐동" to "🍛",
        "규동" to "🍛",
        "스테이크" to "🥩",
        "파스타" to "🍝",
        "피자" to "🍕",
        "햄버거" to "🍔",
        "샐러드" to "🥗",
        "라자냐" to "🍝",
        "리조또" to "🍚"
    )
    val itemColors = listOf(
        Orange100, // Pastel Red
        Color.White, // Pastel Blue
        Orange100, // Pastel Green
        Color.White, // Pastel Purple
        Orange100, // Pastel Yellow
        Color.White  // Pastel Cyan
    )
    var rouletteItems by remember { mutableStateOf(listOf<String>()) }
    var selectedItem by remember { mutableStateOf("") }
    var rotationValue by remember { mutableStateOf(0f) }
    val angle = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    fun getRouletteRotateResult(degrees: Float, items: List<String>): String {
        val moveDegrees = degrees % 360
        val resultAngle = if (moveDegrees > 270) 360 - moveDegrees + 270 else 270 - moveDegrees
        val sliceAngle = 360 / items.size

        for (i in 1..items.size) {
            if (resultAngle < sliceAngle * i) {
                return if (i - 1 >= items.size) "empty" else items[i - 1]
            }
        }
        return ""
    }


    var showDialog by remember { mutableStateOf(false) }
    val _customAlertDialogState: MutableState<CustomAlertDialogState> = remember { mutableStateOf(
        CustomAlertDialogState()
    ) }
    val customAlertDialogState = _customAlertDialogState.value

    LaunchedEffect(selectedItem) {
        if (selectedItem != "") {
            _customAlertDialogState.value = CustomAlertDialogState(
                title = "선택된 메뉴 '${selectedItem}'",
                subTitle = "근처 음식점을 확인 하겠습니까?",
            )
            showDialog = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Box for the image
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp) // Adjust the height as needed
//                .background(Color.Gray)
//        ) {
//            // Display image corresponding to the selected item
//            if (selectedItem.isNotEmpty()) {
//                val imageRes = imageResourceMapping[selectedItem]
//                if (imageRes != null) {
//                    Image(
//                        painter = painterResource(id = imageRes),
//                        contentDescription = selectedItem,
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier.fillMaxSize()
//                    )
//                } else {
//                    Image(
//                        painter = painterResource(id = R.drawable.placeholder_image),
//                        contentDescription = "placeholder",
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier.fillMaxSize()
//                    )
//                }
//            } else {
//                Image(
//                    painter = painterResource(id = R.drawable.placeholder_image),
//                    contentDescription = "placeholder",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text(
//            text = "선택한 음식 개수: ${rouletteItems.size}",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )

        Column(modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = when {
                    selectedItem.isEmpty() -> "룰렛을 돌려주세요!"
                    else -> "선택된 메뉴는 \"$selectedItem\" 입니다!"
                },
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )


            if (showDialog) {
                CustomAlertDialog(
                    customAlertDialogState.title,
                    customAlertDialogState.subTitle,
                    customAlertDialogState.description,
                    {
                        showDialog = false
                    }, {
//                        navController.navigate("restaurant?url=${customAlertDialogState.placeUrl}")
                    })
            }


            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .size(300.dp)
            ) {

                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Orange300, // 파스텔 주황색
                        radius = 160.dp.toPx(),
                        center = Offset(size.width / 2, size.height / 2)
                    )
                }

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val sliceAngle = 360f / rouletteItems.size
                    val radius = size.minDimension / 2
                    val textRadius = radius * 0.7f

                    for (i in rouletteItems.indices) {
                        val startAngle = i * sliceAngle + angle.value
                        val color =
                            itemColors.getOrElse(i % itemColors.size) { Color.Black } // 아이템별로 색상 선택
                        drawArc(
                            color = color,
                            startAngle = startAngle,
                            sweepAngle = sliceAngle,
                            useCenter = true,
                            topLeft = Offset.Zero,
                            size = size
                        )
                        drawIntoCanvas { canvas ->
                            val textPaint = Paint().asFrameworkPaint().apply {
                                isAntiAlias = true
                                textSize = 20.sp.toPx()
                                textAlign = android.graphics.Paint.Align.CENTER
                            }
                            val angleInRad = (startAngle + sliceAngle / 2) * (PI / 180)
                            val x = (textRadius * cos(angleInRad) + radius).toFloat()
                            val y = (textRadius * sin(angleInRad) + radius).toFloat()
                            canvas.save()
                            canvas.rotate(
                                (startAngle + sliceAngle / 2 + 90) % 360,
                                x,
                                y
                            )
                            canvas.nativeCanvas.drawText(
                                "${rouletteItems[i]} ${foodEmojis[rouletteItems[i]]}",
                                x,
                                y - ((textPaint.descent() + textPaint.ascent()) / 2),
                                textPaint
                            )
                            canvas.restore()
                        }
                    }
                }

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val triangleSize = 20.dp.toPx()
                    val triangleHeight = (triangleSize * (3.0.pow(0.5) / 2)).toFloat()
                    val triangleOffsetX = (size.width - triangleSize) / 2
                    drawIntoCanvas { canvas ->
                        val paint = Paint().asFrameworkPaint().apply {
                            color = Orange300.toArgb()
                            isAntiAlias = true
                        }
                        val path = Path().apply {
                            moveTo(triangleOffsetX, 0f)
                            lineTo(triangleOffsetX + triangleSize, 0f)
                            lineTo(triangleOffsetX + triangleSize / 2, triangleHeight)
                            close()
                        }
                        canvas.nativeCanvas.drawPath(path, paint)
                    }
                }

                // 중앙 동그란 도형 추가
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Orange300, // 파스텔 주황색
                        radius = 30.dp.toPx(),
                        center = Offset(size.width / 2, size.height / 2)
                    )
                }

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {
                                    coroutineScope.launch {
                                        angle.snapTo(0f)
                                        val additionalAngle = (360..1440)
                                            .random()
                                            .toFloat()
                                        val targetAngle = angle.value + additionalAngle
                                        angle.animateTo(
                                            targetValue = targetAngle,
                                            animationSpec = tween(
                                                durationMillis = 1000,
                                                easing = FastOutSlowInEasing
                                            )
                                        )
                                        selectedItem =
                                            getRouletteRotateResult(targetAngle, rouletteItems)
                                        rotationValue = targetAngle
                                    }
                                }
                            )
                        }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OrangeButton(
                modifier = Modifier.fillMaxWidth(),
                text = "룰렛을 돌려주세요",
                onClick = {
                    if (rouletteItems.isEmpty()) {
                        return@OrangeButton
                    }
                    coroutineScope.launch {
                        angle.snapTo(0f)
                        val additionalAngle = (360..3600).random().toFloat()
                        val targetAngle = angle.value + additionalAngle
                        angle.animateTo(
                            targetValue = targetAngle,
                            animationSpec = tween(
                                durationMillis = 2000,
                                easing = FastOutSlowInEasing
                            )
                        )
                        selectedItem = getRouletteRotateResult(targetAngle, rouletteItems)
                        rotationValue = targetAngle
                    }
                }
            )


            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (availableItems.isNotEmpty()) {
                            val newItem = availableItems.random()  // 랜덤으로 아이템 선택
                            availableItems = availableItems - newItem
                            rouletteItems = rouletteItems + newItem
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFB347)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("메뉴 추가", fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (rouletteItems.isNotEmpty()) {
                            val removedItem = rouletteItems.last()
                            rouletteItems = rouletteItems.dropLast(1)
                            availableItems = availableItems + removedItem
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFB347)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("메뉴 삭제", fontSize = 20.sp)
                }
            }
        }
    }
}