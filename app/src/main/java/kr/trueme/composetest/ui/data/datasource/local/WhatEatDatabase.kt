package kr.trueme.composetest.ui.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle
import kr.trueme.composetest.ui.domain.model.FoodChoice
import kr.trueme.composetest.ui.domain.model.Tag
import kr.trueme.composetest.ui.domain.model.foodbundle.CustomFood

@Database(
    entities = [FoodBundle::class, FoodChoice::class, CustomFood::class, Tag::class],
    version = 6
)
@TypeConverters(Converters::class)
abstract class WhatEatDatabase: RoomDatabase() {

    abstract val foodBundleDAO: FoodBundleDAO

    abstract val foodChoiceDAO: FoodChoiceDAO

    abstract val customFoodDAO: CustomFoodDAO

    abstract val tagDAO: TagDAO

    companion object {
        const val DATABASE_NAME = "whateat_db"
    }

}