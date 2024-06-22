package kr.trueme.composetest.ui.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kr.trueme.composetest.ui.domain.model.foodbundle.CustomFood

@Dao
interface CustomFoodDAO {

    @Query("SELECT * FROM custom_food")
    fun getCustomFoods(): Flow<List<CustomFood>>

    @Query("SELECT * FROM custom_food WHERE id = :id")
    suspend fun getCustomFoodById(id: Int): CustomFood?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomFood(customFood: CustomFood)

    @Delete
    suspend fun deleteCustomFood(customFood: CustomFood)


}