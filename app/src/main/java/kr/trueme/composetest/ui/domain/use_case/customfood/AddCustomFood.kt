package kr.trueme.composetest.ui.domain.use_case.customfood

import kr.trueme.composetest.ui.domain.model.foodbundle.CustomFood
import kr.trueme.composetest.ui.domain.reposiotry.CustomFoodRepository

class AddCustomFood(
    private val repository: CustomFoodRepository
) {

    suspend operator fun invoke(customFood: CustomFood) {
        repository.insertCustomFood(customFood)
    }
}