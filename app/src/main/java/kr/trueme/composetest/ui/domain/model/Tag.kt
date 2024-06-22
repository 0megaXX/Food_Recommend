package kr.trueme.composetest.ui.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag")
data class Tag(
    @PrimaryKey val name: String,
) {
}