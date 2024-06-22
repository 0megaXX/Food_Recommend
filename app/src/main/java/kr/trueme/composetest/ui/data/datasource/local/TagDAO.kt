package kr.trueme.composetest.ui.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kr.trueme.composetest.ui.domain.model.Tag

@Dao
interface TagDAO {

    @Query("SELECT * FROM tag")
    fun getTags(): Flow<List<Tag>>

    @Query("SELECT * FROM tag WHERE name = :name")
    suspend fun getTagById(name: String): Tag?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag: Tag)

    @Delete
    suspend fun deleteTag(tag: Tag)
}