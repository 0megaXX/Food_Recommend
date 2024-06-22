package kr.trueme.composetest.ui.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kr.trueme.composetest.ui.domain.model.FoodChoice
import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle

@Dao
interface FoodBundleDAO {

    @Query("SELECT * FROM foodbundle")
    fun getFoodBundles(): Flow<List<FoodBundle>>

    @Query("SELECT * FROM foodbundle WHERE id = :id")
    suspend fun getFoodBundleById(id: Int): FoodBundle?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodBundle(foodBundle: FoodBundle)

    @Update
    suspend fun updateFoodBundle(foodBundle: FoodBundle)

    @Query("UPDATE FoodBundle SET isLike = :isLike WHERE title = :title")
    suspend fun updateIsLikeByTitle(title: String, isLike: Boolean)

    @Delete
    suspend fun deleteFoodBundle(foodBundle: FoodBundle)

}