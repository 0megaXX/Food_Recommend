package kr.trueme.composetest.ui.domain.reposiotry

import kotlinx.coroutines.flow.Flow
import kr.trueme.composetest.ui.domain.model.FoodChoice

interface FoodChoiceRepository {

    fun getFoodChoices(): Flow<List<FoodChoice>>

    fun getFoodChoicesByCategory(category: String): Flow<List<FoodChoice>>

    suspend fun getFoodChoiceByName(name: String): FoodChoice?

    suspend fun insertFoodChoice(foodChoice: FoodChoice)

    suspend fun updateFoodChoice(foodChoice: FoodChoice)

    suspend fun deleteFoodChoice(note: FoodChoice)

}