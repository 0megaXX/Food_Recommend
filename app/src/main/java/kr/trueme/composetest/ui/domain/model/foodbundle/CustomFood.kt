package kr.trueme.composetest.ui.domain.model.foodbundle

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "custom_food")
data class CustomFood(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val address: String? = null,
    val categories: List<String> = listOf(),
    val imageUrl: String? = null
) {

}