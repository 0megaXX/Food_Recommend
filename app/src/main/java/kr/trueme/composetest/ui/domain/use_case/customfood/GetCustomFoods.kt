package kr.trueme.composetest.ui.domain.use_case.customfood

import kotlinx.coroutines.flow.Flow
import kr.trueme.composetest.ui.domain.model.foodbundle.CustomFood
import kr.trueme.composetest.ui.domain.reposiotry.CustomFoodRepository

class GetCustomFoods(
    private val repository: CustomFoodRepository
) {

    operator fun invoke(): Flow<List<CustomFood>> {
        return repository.getCustomFoods()
    }
}