package kr.trueme.composetest.ui.domain.use_case.customfood

data class CustomFoodUseCases(
    val getCustomFoods: GetCustomFoods,
    val getCustomFood: GetCustomFood,
    val addCustomFood: AddCustomFood
) {
}