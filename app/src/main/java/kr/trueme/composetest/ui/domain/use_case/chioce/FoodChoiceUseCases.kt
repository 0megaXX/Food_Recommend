package kr.trueme.composetest.ui.domain.use_case.chioce

data class FoodChoiceUseCases(
    val getFoodChoices: GetFoodChoices,
    val getFoodChoice: GetFoodChoice,
    val getFoodChoicesByCategory: GetFoodChoicesByCategory,
    val selectFoodChoice: SelectFoodChoice,
    val selectCategory: SelectFoodChoice,
    val addFoodChoice: AddFoodChoice,
    val updateFoodChoice: UpdateFoodChoice,
) {
}