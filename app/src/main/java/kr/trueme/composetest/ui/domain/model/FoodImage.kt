package kr.trueme.composetest.ui.domain.model

import androidx.annotation.DrawableRes

data class FoodImage(
    val name: String,
    @DrawableRes val imageId: Int,
    val categories: List<Category> = emptyList()
) {
}