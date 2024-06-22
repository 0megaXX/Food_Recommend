package kr.trueme.composetest.ui.domain.use_case.chioce

import kr.trueme.composetest.ui.domain.model.FoodChoice
import kr.trueme.composetest.ui.domain.reposiotry.FoodChoiceRepository

class GetFoodChoice(
    private val repository: FoodChoiceRepository
) {

    suspend operator fun invoke(name: String): FoodChoice? {
        return repository.getFoodChoiceByName(name)
    }
}