package kr.trueme.composetest.ui.data.repository

import kotlinx.coroutines.flow.Flow
import kr.trueme.composetest.ui.data.datasource.local.FoodChoiceDAO
import kr.trueme.composetest.ui.domain.model.FoodChoice
import kr.trueme.composetest.ui.domain.reposiotry.FoodChoiceRepository

class FoodChoiceRepositoryImpl(
    private val dao: FoodChoiceDAO
) : FoodChoiceRepository {

    override fun getFoodChoices(): Flow<List<FoodChoice>> {
        return dao.getFoodChoices()
    }

    override fun getFoodChoicesByCategory(category: String): Flow<List<FoodChoice>> {
        return dao.getFoodChoicesByCategory(category)
    }

    override suspend fun getFoodChoiceByName(name: String): FoodChoice? {
        return dao.getFoodChoiceByName(name)
    }

    override suspend fun insertFoodChoice(foodChoice: FoodChoice) {
        dao.insertFoodChoice(foodChoice)
    }

    override suspend fun updateFoodChoice(foodChoice: FoodChoice) {
        dao.updateFoodChoice(foodChoice = foodChoice)
    }

    override suspend fun deleteFoodChoice(note: FoodChoice) {
        TODO("Not yet implemented")
    }
}