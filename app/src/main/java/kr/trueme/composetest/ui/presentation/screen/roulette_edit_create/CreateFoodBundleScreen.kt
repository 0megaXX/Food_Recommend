package kr.trueme.composetest.ui.presentation.screen.roulette_edit_create

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.domain.model.foodbundle.CustomFood
import kr.trueme.composetest.ui.presentation.compnent.OrangeButton
import kr.trueme.composetest.ui.presentation.compnent.StandardTopBar
import kr.trueme.composetest.ui.presentation.screen.roulette_edit_create.components.FoodBundleEditItem
import kr.trueme.composetest.ui.presentation.screen.roulette_edit_create.components.TransparentHintTextField
import kr.trueme.composetest.ui.theme.Gray300
import kr.trueme.composetest.ui.theme.Orange300
import kr.trueme.composetest.ui.theme.PretendardFamily
import kr.trueme.composetest.ui.theme.SpaceMedium
import kr.trueme.composetest.ui.theme.SpaceSmall

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateFoodBundleScreen(
    navController: NavController,
    viewModel: CreateFoodBundleViewModel
) {

    val titleState = viewModel.title.value
    val contentState = viewModel.noteContent.value

    val snackbarHostState = remember { SnackbarHostState() }
    val text = viewModel.text.value

    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is CreateFoodBundleViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is CreateFoodBundleViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }
    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            Column(modifier = Modifier.padding(16.dp)) {
                var tag by remember { mutableStateOf("") }
                Row {

                }
                OutlinedTextField(
                    value = tag,
                    onValueChange = {
                        tag = it
                    },
                    placeholder = {
                        Text(text = "태그를 입력하세요.")
                    },
                    trailingIcon = {
                        if (tag != "")
                        IconButton(
                            onClick = {
                                if (tag != "") {
                                    viewModel.addTag(tag)
                                    tag = ""
                                }
                            }
                        ) {
                            Icon(painter = painterResource(id = R.drawable.ic_circle_add), contentDescription = null)
                        }
                    }
                )
            }

        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            StandardTopBar(
                onTabLeft = {
                    navController.navigateUp()
                }
            )
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        TransparentHintTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(White),
                            text = titleState.text,
                            hint = titleState.hint,
                            onValueChange = {
                                viewModel.onEvent(CreateFoodBundleEvent.EnteredTitle(it))
                            },
                            onFocusChange = {
                                viewModel.onEvent(CreateFoodBundleEvent.ChangeTitleFocus(it))
                            },
                            isHintVisible = titleState.isHintVisible,
                            singleLine = true,
                            textStyle = MaterialTheme.typography.h5
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {




                            TransparentHintTextField(
                                modifier = Modifier
                                    .background(White)
                                    .weight(1f),
                                text = contentState.text,
                                hint = contentState.hint,
                                onValueChange = {
                                    viewModel.onEvent(CreateFoodBundleEvent.EnteredContent(it))
                                },
                                onFocusChange = {
                                    viewModel.onEvent(CreateFoodBundleEvent.ChangeContentFocus(it))
                                },
                                isHintVisible = contentState.isHintVisible,
                                singleLine = true,
                                textStyle = MaterialTheme.typography.body1,
                            )
                            Spacer(modifier = Modifier.width(SpaceSmall))

                            Button(
                                modifier = Modifier
                                    .size(54.dp, 54.dp),
                                shape = RoundedCornerShape(8.dp),
                                onClick = {
                                    viewModel.addCustomFood(CustomFood(name = contentState.text))
                                },
                                enabled = !contentState.isHintVisible
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_plus),
                                    contentDescription = "back",
                                    tint = White
                                )
                            }
                        }


                        Row(modifier = Modifier.fillMaxWidth()) {
                            Button(
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    navController.navigate("find_food_by_category")
                                }
                            ) {
                                Text(text = "카테고리에서 찾기")
                            }
                            Spacer(modifier = Modifier.width(SpaceSmall))
                            Button(
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    navController.navigate("find_food_by_map")
                                }) {
                                Text(text = "지도에서 찾기")
                            }
                        }

                        Spacer(modifier = Modifier.height(SpaceMedium))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "목록",
                                style = TextStyle(
                                    fontFamily = PretendardFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    lineHeight = 16.sp,
                                )
                            )
                        }

                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(viewModel.customFoodList) {
                                FoodBundleEditItem(
                                    modifier = Modifier.fillMaxWidth(),
                                    customFood = it,
                                    onCloseClick = {
                                        viewModel.deleteCustomFood(it)
                                    }
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }

                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.End
                    ) {
                        LazyRow(
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.Top)
                        ) {
                            items(viewModel.tags) {
                                Surface(
                                    modifier = Modifier
                                        .align(Alignment.Bottom)
                                        .clickable {
                                            viewModel.deleteTag(it)
                                        },
                                    shape = RoundedCornerShape(100.dp),
                                    color = Orange300
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .align(Alignment.Bottom)
                                            .padding(12.dp, 6.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = it,
                                            color = Color.White)
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Icon(painter = painterResource(id = R.drawable.ic_close), contentDescription = null,
                                            tint = Color.White)
                                    }
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }
                        Surface(
                            modifier = Modifier
                                .align(Alignment.Bottom)
                                .clickable {
                                    scope.launch { state.show() }
                                },
                            shape = RoundedCornerShape(100.dp),
                            border = BorderStroke(1.dp, Gray300)
                        ) {
                            Row(
                                modifier = Modifier
                                    .align(Alignment.Bottom)
                                    .padding(12.dp, 6.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "# 태그 추가")
                            }

                        }
                    }



                    OrangeButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 16.dp),
                        text = "저장",
                        onClick = {
                            viewModel.onEvent(CreateFoodBundleEvent.SaveFoodBundle)
                        })
                }

                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@Preview
@Composable
fun `$CreateFoodBundleScreenPreview`() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextField(value = "", onValueChange = {

        })
        TextField(value = "", onValueChange = {

        })
    }
}