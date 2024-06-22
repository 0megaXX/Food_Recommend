package kr.trueme.composetest.ui.presentation.screen.find_category_list

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kr.trueme.composetest.ui.domain.model.foodbundle.CustomFood
import kr.trueme.composetest.ui.presentation.compnent.OrangeButton
import kr.trueme.composetest.ui.presentation.compnent.StandardTopBar
import kr.trueme.composetest.ui.theme.Gray50

@Composable
fun CustomFoodEditScreen(
    navController: NavController,
    viewModel: CustomFoodViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var url by remember { mutableStateOf<Uri?>(null) }

    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            url = uri
            uri?.let {

            }
        }

    Column(modifier = Modifier.fillMaxSize()) {
        StandardTopBar(
            onTabLeft = {
                navController.navigateUp()
            },
            title = "나만의 음식 추가"
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Gray50)
                    .size(100.dp)
                    .clickable {
                        singlePhotoPickerLauncher.launch("image/*")
                    }
            ) {
                if (url == null) {
                    Text(text = "음식사진 추가")
                } else {
                    AsyncImage(
                        model = url,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }


            Text(text = "음식 이름")
            OutlinedTextField(
                value = name,
                onValueChange = {
                name = it
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "카테고리")

            OrangeButton(
                modifier = Modifier.fillMaxWidth(),
                text = "음식 생성",
                onClick = {
                    viewModel.addCustomFood(CustomFood(name = name, imageUrl = url?.toString()))
                }
            )
        }
    }
}

@Composable
fun GalleryPicker() {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
            uri?.let {

            }
        }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            singlePhotoPickerLauncher.launch("image/*")
        }) {
            Text(text = "Pick Image from Gallery")
        }

        Spacer(modifier = Modifier.height(16.dp))

        imageUri?.let {
            Text(text = "Selected Image URI: $it", fontSize = 16.sp)
        }
        AsyncImage(
            model = imageUri,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}