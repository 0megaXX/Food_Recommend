package kr.trueme.composetest.ui.presentation.screen.find_category_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import io.woong.compose.grid.SimpleGridCells
import io.woong.compose.grid.VerticalGrid
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.presentation.compnent.CategoryGridItem
import kr.trueme.composetest.ui.presentation.compnent.CategoryItem
import kr.trueme.composetest.ui.presentation.compnent.StandardTopBar
import kr.trueme.composetest.ui.presentation.screen.roulette_edit_create.CreateFoodBundleViewModel
import kr.trueme.composetest.ui.theme.Gray300
import kr.trueme.composetest.ui.theme.Gray400
import kr.trueme.composetest.ui.theme.Orange400
import kr.trueme.composetest.ui.theme.PretendardFamily

@Composable
fun FoodCategoryListScreen(
    navController: NavController,
    viewModel: CreateFoodBundleViewModel,
    customViewModel: CustomFoodViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StandardTopBar(
            onTabLeft = {
                navController.popBackStack()
            }
        )

        Column(modifier = Modifier.padding(16.dp, 8.dp)) {
            Text(
                text = "음식 카테고리",
                style = TextStyle(
                    fontFamily = PretendardFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    letterSpacing = 0.sp
                )
            )
            Spacer(modifier = Modifier.padding(8.dp))
            CategoryList(navController)
            Spacer(modifier = Modifier.padding(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "나만의 음식 목록",
                    style = TextStyle(
                        fontFamily = PretendardFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        letterSpacing = 0.sp
                    )
                )
                Text(
                    text = "전체보기",
                    textAlign = TextAlign.Right,
                    style = TextStyle(
                        fontFamily = PretendardFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        letterSpacing = 0.sp
                    ),
                    color = Gray400
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))

            val customFoodList = customViewModel.customFoodList

            VerticalGrid(
                columns = SimpleGridCells.Fixed(4),
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MineFoodAddBox(navController)
                customFoodList.forEach {
                    CustomFoodItem(name = it.name, imageUri = it.imageUrl)
                }
            }

        }

    }
}

@Composable
fun CustomFoodItem(
    modifier: Modifier = Modifier,
    name: String,
    imageUri: String? = null,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .height(76.dp)
                .width(76.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            border = BorderStroke(1.dp, Gray300)
        ) {
            Box(contentAlignment = Alignment.Center) {
                imageUri?.let {
                    Image(
                        painter = rememberImagePainter(it),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        Text(text = name)
    }
}

@Composable
fun CategoryList(navController: NavController) {
    val categoryItems = listOf(
        CategoryItem("한식", R.drawable.foodcategory_1),
        CategoryItem("중식", R.drawable.foodcategory_2),
        CategoryItem("일식", R.drawable.foodcategory_3),
        CategoryItem("양식", R.drawable.foodcategory_4),
        CategoryItem("패스트푸드", R.drawable.foodcategory_5),
        CategoryItem("아시아", R.drawable.foodcategory_6),
        CategoryItem("디저트", R.drawable.foodcategory_7),
        CategoryItem("카테고리", R.drawable.foodcategory_6),
    )
    VerticalGrid(
        columns = SimpleGridCells.Fixed(4),
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categoryItems.forEach {
            CategoryGridItem(
                categoryItem = it,
                modifier = Modifier,
                onClick = {
                    navController.navigate("detail_food")
                }
            )
        }
    }
}

@Composable
fun MineFoodAddBox(navController: NavController) {


    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .height(76.dp)
                .width(76.dp)
                .clickable {
                    navController.navigate("custom_food_edit")
                },
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            border = BorderStroke(1.dp, Orange400)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_circle_add),
                        contentDescription = "",
                        tint = Orange400
                    )
                    Text(text = "음식 추가")
                }

            }
        }
    }
}


//fun saveImageToExternalStorage(context: Context, imageUri: Uri) {
//    val resolver = context.contentResolver
//    val imageName = "image_${System.currentTimeMillis()}.jpg"
//    val contentValues = ContentValues().apply {
//        put(MediaStore.MediaColumns.DISPLAY_NAME, imageName)
//        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
//        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
//    }
//
//    val imageUriSaved: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
//
//    imageUriSaved?.let { uriSaved ->
//        resolver.openOutputStream(uriSaved).use { outputStream ->
//            resolver.openInputStream(imageUri).use { inputStream ->
//                inputStream?.copyTo(outputStream!!)
//            }
//        }
//        Toast.makeText(context, "Image saved to gallery", Toast.LENGTH_SHORT).show()
//    } ?: run {
//        Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
//    }
//}
//
//fun saveImageUriToDatabase(context: Context, imageDao: ImageDao, uri: String) {
//    val scope = rememberCoroutineScope()
//    scope.launch {
//        withContext(Dispatchers.IO) {
//            imageDao.insert(ImageEntity(uri = uri))
//        }
//        withContext(Dispatchers.Main) {
//            Toast.makeText(context, "Image URI saved to database", Toast.LENGTH_SHORT).show()
//        }
//    }
//}