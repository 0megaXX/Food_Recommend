package kr.trueme.composetest.ui.presentation.screen.question

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sub_pack.FoodViewModel
import com.example.sub_pack.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kr.trueme.composetest.ui.presentation.compnent.CustomAlertDialog
import kr.trueme.composetest.ui.presentation.compnent.CustomAlertDialogState
import kr.trueme.composetest.ui.presentation.compnent.RestaurantFind

@Composable
fun FoodQuestionScreen(
    navController: NavController,
    viewModel: FoodViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("맞춤 음식 추천", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { /* 뒤로 가기 처리 */ }) {
                        Icon(Icons.Filled.ArrowBack, "Back", tint = Color.White)
                    }
                },
                backgroundColor = Color(0xFFFFA500),
                elevation = 0.dp
            )
        }
    ) {paddingValues->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                BackgroundShapes()  // 배경 도형을 그리는 Canvas 추가
                Column(
                    modifier = Modifier
                        .matchParentSize() // Column을 Box의 크기에 맞추기
                        .background(Color.Transparent) // 배경색을 투명하게 설정하여 도형이 보이도록 함
                        .padding(16.dp)
                ) {
                    PictureAndResult(viewModel)
                    if (viewModel.showButtons.value) {
                        FoodQuestionButtons(viewModel)
                    }
                    ActionButtons(viewModel, navController)
                }
            }
        }

    }
}

@Composable
fun BackgroundShapes() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height


        /* // 반원 그리기
         drawArc(
             color = Color.LightGray,
             startAngle = 0f,
             sweepAngle = 180f,
             useCenter = true,
             size = Size(canvasWidth, canvasHeight / 2),
             topLeft = Offset(0f, -canvasHeight / 4)
         )
 */
        // 다양한 색상의 원 그리기
        drawCircle(
            color = Color(0xFFFFEF6D00),
            radius = size.minDimension / 4,
            center = Offset(x = canvasWidth * (1 / 4), y = canvasHeight * 1)
        )


        /*
                drawCircle(
                    color =  Color(0xFFFFC55C),
                    radius = size.minDimension / 5,
                    center = Offset(x = canvasWidth * 3 / 4, y = canvasHeight / 3)
                )
                */



        //두겹 원 만들기
        drawCircle(
            color = Color(0xFFFFDA97),
            radius = size.minDimension / 2,
            center = Offset(x = canvasWidth * 4 / 4, y = canvasHeight*1)
        )

        drawCircle(
            color = Color.White.copy(alpha = 0.3f),
            radius = size.minDimension / 3 * 1.1F,
            center = Offset(x = canvasWidth * 4 / 4, y = canvasHeight*1)
        )

        drawCircle(
            color = Color(0xFFFFF9ED),
            radius = size.minDimension / 4,
            center = Offset(x = canvasWidth * 4 / 4, y = canvasHeight*1)
        )



        /*
         // 임의의 사각형 그리기
         drawRect(
             color = Color.Green.copy(alpha = 0.3f),
             topLeft = Offset(x = canvasWidth / 2 - 50.dp.toPx(), y = canvasHeight / 2 - 50.dp.toPx()),
             size = Size(100.dp.toPx(), 100.dp.toPx())
         )
         */

    }
}

@Composable
fun AttributeCircles(viewModel: FoodViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(10.dp)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 원의 수만큼 반복하여 CircleIndicator 컴포저블 호출
        viewModel.circleColors.forEachIndexed { index, color ->
            CircleIndicator(color = color, text = viewModel.circleTexts[index])
        }

    }
}

@Composable
fun CircleIndicator(color: Color, text: String) {
    val textColor = if (color == Color(0xFFFFA500)) Color.White else Color(0xFFFFA500)
    Box(
        modifier = Modifier
            .size(60.dp)
            .padding(5.dp)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun FoodQuestionButtons(viewModel: FoodViewModel) {
    if (viewModel.showButtons.value) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp, start = 16.dp, end = 16.dp, bottom = 16.dp), // 위쪽에 50dp의 여백을 추가
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // "Yes" 버튼
            Button(

                onClick = { viewModel.processSelection(true) },
                modifier = Modifier
                    .width(400.dp)  // 최대 폭으로 확장
                    .height(60.dp) , // 버튼의 높이 조절
                //  .border(0.3.dp, Color(0xFFEF6D00), RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),  // 버튼의 둥근 모서리 더 둥글게 정의
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFB84D)),
                elevation = ButtonDefaults.elevation( // 버튼 음영 제거
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                )

            ) {
                Text("네", color = Color.White, fontSize = 20.sp)
            }

            // "I Don't Know" 버튼
            Button(
                onClick = { viewModel.discardTrait() },
                modifier = Modifier
                    .width(400.dp)  // 최대 폭으로 확장
                    .height(60.dp),
                //    .border(0.3.dp, Color(0xFFEF6D00), RoundedCornerShape(20.dp)),  // 버튼의 높이 조절
                shape = RoundedCornerShape(20.dp),  // 버튼의 둥근 모서리 더 둥글게 정의
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFF3DD)),
                elevation = ButtonDefaults.elevation( // 버튼 음영 제거
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                )
            ) {
                Text("잘 모르겠어요", color = Color(0xFFFF9900), fontSize = 20.sp)
            }

            // "No" 버튼
            Button(
                onClick = { viewModel.processSelection(false) },
                modifier = Modifier
                    .width(400.dp)  // 최대 폭으로 확장
                    .height(60.dp),
                //   .border(0.3.dp, Color(0xFFFF9900), RoundedCornerShape(20.dp)),// 버튼의 높이 조절
                shape = RoundedCornerShape(20.dp),  // 버튼의 둥근 모서리 더 둥글게 정의
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF5F5F5)),
                elevation = ButtonDefaults.elevation( // 버튼 음영 제거
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                )
            ) {
                Text("아니요", color = Color(0xFF090909), fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun PictureAndResult(viewModel: FoodViewModel) {
    // 더 둥근 모서리를 위한 모양 정의
    val shape = RoundedCornerShape(90.dp) // 모서리를 더 둥글게

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 10.dp)

    ) {
        /*    Image(
                painter = painterResource(id = R.drawable.roulette_microwave_front),
                contentDescription = "Center Picture",
                modifier = Modifier
                    .size(120.dp) // 이미지 크기 조정

            )*/


        /*     Text(
             text = viewModel.TempFood.value,
             fontSize = 30.sp,
             fontWeight = FontWeight.Bold,
             textAlign = TextAlign.Center,
             modifier = Modifier
                 .background(Color.White, shape)
                 .padding(horizontal = 10.dp, vertical = 8.dp) // 텍스트 내부 패딩 조정
                 .fillMaxWidth()
                 .heightIn(min = 60.dp) // 최소 높이 설정
         )*/
        //AttributeCircles(viewModel = viewModel)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = viewModel.selectedFood.value,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,

            color = Color(0xFFFFEF6D00),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFF5F5F5))
                .padding(15.dp)
                .heightIn(min = 50.dp) // 최소 높이 설정
            ,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp)) // 텍스트 박스 사이 간격 추가

        // 새로운 텍스트 박스 추가
        Text(
            text = viewModel.tag_text.value,  // 새 텍스트 콘텐츠
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF000000), // 새 텍스트 색상
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFFFFFF))  // 새 배경색
                .padding(12.dp)
                .heightIn(min = 40.dp),  // 새 텍스트 박스 높이

        )
    }
}

@Composable
fun ActionButtons(viewModel: FoodViewModel, navController: NavController) {
    if (viewModel.showActionButtons.value) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if(viewModel.start_btn.value) {
                Button(
                    onClick = { viewModel.startQuestions() },
                    modifier = Modifier
                        .width(400.dp)  // 최대 폭으로 확장
                        .height(60.dp),

                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFA500)),
                    shape = RoundedCornerShape(20.dp),  // 버튼의 둥근 모서리 더 둥글게 정의

                    elevation = ButtonDefaults.elevation( // 버튼 음영 제거
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        disabledElevation = 0.dp
                    )
                ) {
                    Text("시작합니다", color = Color(0xFF090909), fontSize = 20.sp)
                }


                // 버튼 사이 간격 추가
                Spacer(modifier = Modifier.height(20.dp))
            }


            if(viewModel.reset_btn.value) {
                //다시하기 버튼
                Button(
                    onClick = { viewModel.reset() },
                    modifier = Modifier
                        .width(400.dp)  // 최대 폭으로 확장
                        .height(60.dp),

                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCCCCCC)),
                    shape = RoundedCornerShape(20.dp),  // 버튼의 둥근 모서리 더 둥글게 정의

                    elevation = ButtonDefaults.elevation( // 버튼 음영 제거
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        disabledElevation = 0.dp
                    )
                ) {
                    Text("다시하기", color = Color(0xFF090909), fontSize = 20.sp)
                }

                val _customAlertDialogState: MutableState<CustomAlertDialogState> = remember { mutableStateOf(
                    CustomAlertDialogState()
                ) }
                val customAlertDialogState = _customAlertDialogState.value

                val chooseItem = viewModel.chooseFood.value
                var showDialog by remember { mutableStateOf(false) }
                var confirm by remember { mutableStateOf(false) }
                if (showDialog) {
                    CustomAlertDialog(
                        "'$chooseItem' 음식점",
                        customAlertDialogState.subTitle,
                        customAlertDialogState.description,
                        {
                            showDialog = false
                        }, {
                            confirm = true
                        })
                }

                if (confirm) {
                    RestaurantFind(chooseItem, navController)
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        showDialog = true
                    },
                    shape = RoundedCornerShape(100.dp)
                ) {
                    Text(text = "근처 음식점 확인하기")
                }

            }

        }
    }
}

private lateinit var fusedLocationClient: FusedLocationProviderClient

@Composable
fun WeatherScreen() {
    val weatherViewModel: WeatherViewModel = viewModel()
    val context = LocalContext.current
    val activity = (context as? Activity)

    LaunchedEffect(key1 = true) {
        val locationPermission = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
            }
        } else {
            LocationServices.getFusedLocationProviderClient(context).lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    weatherViewModel.fetchWeather(location.latitude, location.longitude)
                }
            }
        }
    }

    Text("현재 기온: ${weatherViewModel.currentTemperature.value}°C")
}