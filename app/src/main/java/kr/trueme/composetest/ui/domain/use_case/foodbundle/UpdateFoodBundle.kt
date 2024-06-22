package kr.trueme.composetest.ui.domain.use_case.foodbundle

import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle
import kr.trueme.composetest.ui.domain.reposiotry.FoodBundleRepository

class UpdateFoodBundle(
    private val repository: FoodBundleRepository
) {

    suspend operator fun invoke(foodBundle: FoodBundle) {
        repository.updateFoodBundle(foodBundle)
    }
}

class UpdateFoodBundleIsLikeByTitle(
    private val repository: FoodBundleRepository
) {

    suspend operator fun invoke(title: String, isLike: Boolean) {
        repository.updateIsLikeByTitle(title, isLike)
    }
}