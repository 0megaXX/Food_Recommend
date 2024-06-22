package kr.trueme.composetest.ui.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_choice")
data class FoodChoice(
    @PrimaryKey val name: String,
    val category: String,
    val isSelected: Boolean = false,
    val isLike: Boolean = false,
)
