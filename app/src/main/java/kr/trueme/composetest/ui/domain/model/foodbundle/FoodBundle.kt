package kr.trueme.composetest.ui.domain.model.foodbundle

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FoodBundle(
    val title: String,
    val customFoods: List<CustomFood> = listOf(),
    val tags: List<String> = listOf(),
    val isLike: Boolean = false,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null
) {

}

class InvalidFoodBundleException(message: String): Exception(message)