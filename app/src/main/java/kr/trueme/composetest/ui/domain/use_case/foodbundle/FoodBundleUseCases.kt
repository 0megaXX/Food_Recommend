package kr.trueme.composetest.ui.domain.use_case.foodbundle

data class FoodBundleUseCases(
    val getFoodBundles: GetFoodBundles,
    val deleteFoodBundle: DeleteFoodBundle,
    val addFoodBundle: AddFoodBundle,
    val getFoodBundle: GetFoodBundle,
    val updateFoodBundle: UpdateFoodBundle,
    val updateFoodBundleIsLikeByTitle: UpdateFoodBundleIsLikeByTitle,
)