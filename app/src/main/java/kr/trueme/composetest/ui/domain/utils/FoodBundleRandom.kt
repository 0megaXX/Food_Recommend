package kr.trueme.composetest.ui.domain.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle

object FoodBundleRandom {

    fun getJsonFromFoodBundleRandomItem(foodBundle: FoodBundle?): String {
        var foodList = foodBundle?.customFoods?.map { it.name } ?: emptyList()
        if (foodList.size < 5) {
            val additionalFoods = foodList
            val random = java.util.Random()
            while (foodList.size < 5) {
                foodList = foodList + additionalFoods[random.nextInt(additionalFoods.size)]
            }
        }
        val data = foodList.shuffled()
        val json = Json.encodeToString(data)
        return json
    }
}