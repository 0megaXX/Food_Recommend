package kr.trueme.composetest.ui.presentation.screen.roulette_edit_create

import androidx.compose.ui.focus.FocusState

sealed class CreateFoodBundleEvent {
    data class EnteredTitle(val value: String): CreateFoodBundleEvent()
    data class ChangeTitleFocus(val focusState: FocusState): CreateFoodBundleEvent()
    data class EnteredContent(val value: String): CreateFoodBundleEvent()
    data class ChangeContentFocus(val focusState: FocusState): CreateFoodBundleEvent()

    object SaveFoodBundle: CreateFoodBundleEvent()
}
