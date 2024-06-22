package kr.trueme.composetest.ui.data.repository

import kotlinx.coroutines.flow.Flow
import kr.trueme.composetest.ui.data.datasource.local.FoodBundleDAO
import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle
import kr.trueme.composetest.ui.domain.reposiotry.FoodBundleRepository

class FoodBundleRepositoryImpl(
    private val dao: FoodBundleDAO
) : FoodBundleRepository {
    override fun getFoodBundles(): Flow<List<FoodBundle>> {
        return dao.getFoodBundles()
    }

    override suspend fun getFoodBundleById(id: Int): FoodBundle? {
        return dao.getFoodBundleById(id)
    }

    override suspend fun insertFoodBundle(foodBundle: FoodBundle) {
        dao.insertFoodBundle(foodBundle)
    }

    override suspend fun deleteFoodBundle(foodBundle: FoodBundle) {
        dao.deleteFoodBundle(foodBundle)
    }

    override suspend fun updateFoodBundle(foodBundle: FoodBundle) {
        dao.updateFoodBundle(foodBundle)
    }

    override suspend fun updateIsLikeByTitle(title: String, isLike: Boolean) {
        dao.updateIsLikeByTitle(title, isLike)
    }
}