package kr.trueme.composetest.ui.presentation.screen.food_choice

sealed class CategoryFoodsEvent {
    data class SelectFood(val food: String): CategoryFoodsEvent()
    data class SelectCategory(val category: String): CategoryFoodsEvent()
    object ToggleOrderSection: CategoryFoodsEvent()



}
