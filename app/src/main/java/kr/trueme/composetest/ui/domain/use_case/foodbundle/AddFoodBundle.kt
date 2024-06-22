package kr.trueme.composetest.ui.domain.use_case.foodbundle

import android.util.Log
import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle
import kr.trueme.composetest.ui.domain.model.foodbundle.InvalidFoodBundleException
import kr.trueme.composetest.ui.domain.reposiotry.FoodBundleRepository

class AddFoodBundle(
    private val repository: FoodBundleRepository
) {

    @Throws(InvalidFoodBundleException::class)
    suspend operator fun invoke(foodBundle: FoodBundle) {
        Log.d("title", foodBundle.title)
        if(foodBundle.title.isBlank()) {
            throw InvalidFoodBundleException("The title of the note can't be empty.")
        }
        if(foodBundle.customFoods.isEmpty()) {
            throw InvalidFoodBundleException("The content of the note can't be empty.")
        }
        repository.insertFoodBundle(foodBundle)
    }

}