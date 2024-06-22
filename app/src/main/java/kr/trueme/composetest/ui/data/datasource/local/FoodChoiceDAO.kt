package kr.trueme.composetest.ui.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kr.trueme.composetest.ui.domain.model.FoodChoice

@Dao
interface FoodChoiceDAO {

    @Query("SELECT * FROM food_choice")
    fun getFoodChoices(): Flow<List<FoodChoice>>

    @Query("SELECT * FROM food_choice WHERE category = :category")
    fun getFoodChoicesByCategory(category: String): Flow<List<FoodChoice>>

    @Query("SELECT * FROM food_choice WHERE name = :name")
    suspend fun getFoodChoiceByName(name: String): FoodChoice?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodChoice(foodChoice: FoodChoice)

    @Update
    suspend fun updateFoodChoice(foodChoice: FoodChoice)


}