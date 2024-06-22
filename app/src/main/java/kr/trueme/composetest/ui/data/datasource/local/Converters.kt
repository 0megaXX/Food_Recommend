package kr.trueme.composetest.ui.data.datasource.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kr.trueme.composetest.ui.domain.model.foodbundle.CustomFood

class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromCustomFoodList(customFoods: List<CustomFood>?): String {
        return Gson().toJson(customFoods)
    }

    @TypeConverter
    fun toCustomFoodList(customFoodsString: String): List<CustomFood> {
        val listType = object : TypeToken<List<CustomFood>>() {}.type
        return Gson().fromJson(customFoodsString, listType)
    }
}