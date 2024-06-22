package kr.trueme.composetest.ui.presentation.screen.find_map

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.data.datasource.network.searchAddress
import kr.trueme.composetest.ui.data.model.AddressDocument
import kr.trueme.composetest.ui.data.model.Document
import kr.trueme.composetest.ui.presentation.compnent.StandardTopBar
import kr.trueme.composetest.ui.theme.Gray200
import kr.trueme.composetest.ui.theme.Gray300
import kr.trueme.composetest.ui.theme.SpaceSmall

@Composable
fun FindFoodByMapScreen(
    navController: NavController,
) {
    var text by remember { mutableStateOf("") }
    val list = rememberSaveable { mutableStateOf(listOf<AddressDocument>()) }


    Column(modifier = Modifier.fillMaxSize()) {
        StandardTopBar(
            onTabLeft = {
                navController.navigateUp()
            },
            title = "주소 검색"
        )
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                value = text,
                onValueChange = {
                    text = it
                },
                placeholder = {
                    Text(text = "지번, 도로명, 건물명으로 검색")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            searchAddress(query = text, documents = list)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "search",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                })

            Spacer(modifier = Modifier.height(SpaceSmall))
            Surface(
                modifier = Modifier,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Gray200)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(
                            minWidth = ButtonDefaults.MinWidth,
                            minHeight = ButtonDefaults.MinHeight
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "location",
                        tint = MaterialTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "현재 위치로 찾기",
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(SpaceSmall))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(list.value) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                    ) {
                        Text(text = it.addressName)
                        Divider()
                    }
                }
            }
        }


    }
}