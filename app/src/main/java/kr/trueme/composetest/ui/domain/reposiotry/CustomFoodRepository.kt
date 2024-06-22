package kr.trueme.composetest.ui.domain.reposiotry

import kotlinx.coroutines.flow.Flow
import kr.trueme.composetest.ui.domain.model.foodbundle.CustomFood

interface CustomFoodRepository {

    fun getCustomFoods(): Flow<List<CustomFood>>

    suspend fun getCustomFoodById(id: Int): CustomFood?

    suspend fun insertCustomFood(customFood: CustomFood)

    suspend fun deleteCustomFood(customFood: CustomFood)

}