package kr.trueme.composetest.ui.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.trueme.composetest.ui.data.datasource.local.WhatEatDatabase
import kr.trueme.composetest.ui.data.repository.CustomFoodRepositoryImpl
import kr.trueme.composetest.ui.data.repository.FoodBundleRepositoryImpl
import kr.trueme.composetest.ui.data.repository.FoodChoiceRepositoryImpl
import kr.trueme.composetest.ui.domain.reposiotry.CustomFoodRepository
import kr.trueme.composetest.ui.domain.reposiotry.FoodBundleRepository
import kr.trueme.composetest.ui.domain.reposiotry.FoodChoiceRepository
import kr.trueme.composetest.ui.domain.use_case.chioce.AddFoodChoice
import kr.trueme.composetest.ui.domain.use_case.chioce.FoodChoiceUseCases
import kr.trueme.composetest.ui.domain.use_case.chioce.GetFoodChoice
import kr.trueme.composetest.ui.domain.use_case.chioce.GetFoodChoices
import kr.trueme.composetest.ui.domain.use_case.chioce.GetFoodChoicesByCategory
import kr.trueme.composetest.ui.domain.use_case.chioce.SelectFoodChoice
import kr.trueme.composetest.ui.domain.use_case.chioce.UpdateFoodChoice
import kr.trueme.composetest.ui.domain.use_case.customfood.AddCustomFood
import kr.trueme.composetest.ui.domain.use_case.customfood.CustomFoodUseCases
import kr.trueme.composetest.ui.domain.use_case.customfood.GetCustomFood
import kr.trueme.composetest.ui.domain.use_case.customfood.GetCustomFoods
import kr.trueme.composetest.ui.domain.use_case.foodbundle.AddFoodBundle
import kr.trueme.composetest.ui.domain.use_case.foodbundle.DeleteFoodBundle
import kr.trueme.composetest.ui.domain.use_case.foodbundle.FoodBundleUseCases
import kr.trueme.composetest.ui.domain.use_case.foodbundle.GetFoodBundle
import kr.trueme.composetest.ui.domain.use_case.foodbundle.GetFoodBundles
import kr.trueme.composetest.ui.domain.use_case.foodbundle.UpdateFoodBundle
import kr.trueme.composetest.ui.domain.use_case.foodbundle.UpdateFoodBundleIsLikeByTitle
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideWhatEatDatabase(app: Application): WhatEatDatabase {
        return Room.databaseBuilder(
            app,
            WhatEatDatabase::class.java,
            WhatEatDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFoodBundleRepository(db: WhatEatDatabase): FoodBundleRepository {
        return FoodBundleRepositoryImpl(db.foodBundleDAO)
    }

    @Provides
    @Singleton
    fun provideFoodBundleUseCases(repository: FoodBundleRepository): FoodBundleUseCases {
        return FoodBundleUseCases(
            getFoodBundles = GetFoodBundles(repository),
            deleteFoodBundle = DeleteFoodBundle(repository),
            addFoodBundle = AddFoodBundle(repository),
            getFoodBundle = GetFoodBundle(repository),
            updateFoodBundle = UpdateFoodBundle(repository),
            updateFoodBundleIsLikeByTitle = UpdateFoodBundleIsLikeByTitle(repository)
        )
    }

    @Provides
    @Singleton
    fun provideFoodChoiceRepository(db: WhatEatDatabase): FoodChoiceRepository {
        return FoodChoiceRepositoryImpl(db.foodChoiceDAO)
    }

    @Provides
    @Singleton
    fun provideFoodChoiceUseCases(repository: FoodChoiceRepository): FoodChoiceUseCases {
        return FoodChoiceUseCases(
            getFoodChoices = GetFoodChoices(repository),
            getFoodChoice = GetFoodChoice(repository),
            getFoodChoicesByCategory = GetFoodChoicesByCategory(repository),
            selectFoodChoice = SelectFoodChoice(repository),
            selectCategory = SelectFoodChoice(repository),
            addFoodChoice = AddFoodChoice(repository),
            updateFoodChoice = UpdateFoodChoice(repository)
        )
    }

    @Provides
    @Singleton
    fun provideCustomFoodRepository(db: WhatEatDatabase): CustomFoodRepository {
        return CustomFoodRepositoryImpl(db.customFoodDAO)
    }

    @Provides
    @Singleton
    fun provideCustomFoodUseCases(repository: CustomFoodRepository): CustomFoodUseCases {
        return CustomFoodUseCases(
            getCustomFoods = GetCustomFoods(repository),
            getCustomFood = GetCustomFood(),
            addCustomFood = AddCustomFood(repository),
        )
    }

}