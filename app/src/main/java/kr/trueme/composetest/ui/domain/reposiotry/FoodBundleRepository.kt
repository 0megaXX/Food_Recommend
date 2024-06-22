package kr.trueme.composetest.ui.domain.reposiotry

import kotlinx.coroutines.flow.Flow
import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle

interface FoodBundleRepository {

    fun getFoodBundles(): Flow<List<FoodBundle>>

    suspend fun getFoodBundleById(id: Int): FoodBundle?

    suspend fun insertFoodBundle(foodBundle: FoodBundle)

    suspend fun deleteFoodBundle(foodBundle: FoodBundle)

    suspend fun updateFoodBundle(foodBundle: FoodBundle)

    suspend fun updateIsLikeByTitle(title: String, isLike: Boolean)

}