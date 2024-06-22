package kr.trueme.composetest.ui.data.repository

import kotlinx.coroutines.flow.Flow
import kr.trueme.composetest.ui.data.datasource.local.CustomFoodDAO
import kr.trueme.composetest.ui.domain.model.foodbundle.CustomFood
import kr.trueme.composetest.ui.domain.reposiotry.CustomFoodRepository

class CustomFoodRepositoryImpl(
    private val dao: CustomFoodDAO
) : CustomFoodRepository {
    override fun getCustomFoods(): Flow<List<CustomFood>> {
        return dao.getCustomFoods()
    }

    override suspend fun getCustomFoodById(id: Int): CustomFood? {
        TODO("Not yet implemented")
    }

    override suspend fun insertCustomFood(customFood: CustomFood) {
        dao.insertCustomFood(customFood)
    }

    override suspend fun deleteCustomFood(customFood: CustomFood) {
        dao.deleteCustomFood(customFood)
    }
}