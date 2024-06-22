package kr.trueme.composetest.ui.domain.use_case.foodbundle

import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle
import kr.trueme.composetest.ui.domain.reposiotry.FoodBundleRepository

class GetFoodBundle(
    private val repository: FoodBundleRepository
) {

    suspend operator fun invoke(id: Int): FoodBundle? {
        return repository.getFoodBundleById(id)
    }
}