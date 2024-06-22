package kr.trueme.composetest.ui.domain.use_case.foodbundle

import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle
import kr.trueme.composetest.ui.domain.reposiotry.FoodBundleRepository

class DeleteFoodBundle(
    private val repository: FoodBundleRepository
) {

    suspend operator fun invoke(foodBundle: FoodBundle) {
        repository.deleteFoodBundle(foodBundle)
    }


}