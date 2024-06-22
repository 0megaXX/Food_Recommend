package kr.trueme.composetest.ui.presentation.screen.roulette_edit_create

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
    val isTextEmpty: Boolean = false
)
