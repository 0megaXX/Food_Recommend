package kr.trueme.composetest.ui.presentation.screen.roulette_edit_create

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kr.trueme.composetest.ui.domain.model.foodbundle.CustomFood
import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle
import kr.trueme.composetest.ui.domain.model.foodbundle.InvalidFoodBundleException
import kr.trueme.composetest.ui.domain.use_case.foodbundle.FoodBundleUseCases
import javax.inject.Inject

@HiltViewModel
class CreateFoodBundleViewModel @Inject constructor(
    private val foodBundleUseCases: FoodBundleUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _title = mutableStateOf(NoteTextFieldState(
        hint = "이름을 입력 해주세요.."
    ))

    val title: State<NoteTextFieldState> = _title

    private val _noteContent = mutableStateOf(NoteTextFieldState(
        hint = "음식을 입력해주세요"
    ))
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentFoodBundleId: Int? = null
    private var currentFoodBundle: FoodBundle? = null

    private val _text = mutableStateOf("")
    val text: State<String> = _text

    private var _customFoodList = mutableStateListOf<CustomFood>()
    val customFoodList: List<CustomFood> get() = _customFoodList

//    private val _tags = mutableListOf("맛집", "추천")
//    val tags: MutableList<String> = _tags
    private var _tags = mutableStateListOf<String>()
    val tags: List<String> get() = _tags

    fun updateText(newText: String) {
        _text.value = newText
    }

    fun addTag(tag: String) {
        _tags.add(tag)
    }

    fun deleteTag(tag: String) {
        _tags.remove(tag)
    }

    init {
        savedStateHandle.get<Int>("foodBundleId")?.let { foodBundleId ->
            if(foodBundleId != -1) {
                viewModelScope.launch {
                    foodBundleUseCases.getFoodBundle(foodBundleId)?.also { foodBundle ->
                        currentFoodBundleId = foodBundle.id
                        currentFoodBundle = foodBundle
                        _title.value = title.value.copy(
                            text = foodBundle.title,
                            isHintVisible = false
                        )
                        _customFoodList = foodBundle.customFoods.toMutableStateList()
                        _tags = foodBundle.tags.toMutableStateList()
                    }
                }
            }
        }
    }

    fun addCustomFood(customFood: CustomFood) {
        _customFoodList.add(customFood)
        _noteContent.value = _noteContent.value.copy(
            text = ""
        )
    }

    fun deleteCustomFood(customFood: CustomFood) {
        _customFoodList.remove(customFood)
    }

    fun onEvent(event: CreateFoodBundleEvent) {
        when(event) {
            is CreateFoodBundleEvent.EnteredTitle -> {
                _title.value = title.value.copy(
                    text = event.value
                )
            }

            is CreateFoodBundleEvent.ChangeTitleFocus -> {
                _title.value = title.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            title.value.text.isBlank()
                )
            }

            is CreateFoodBundleEvent.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(
                    text = event.value
                )
            }

            is CreateFoodBundleEvent.ChangeContentFocus -> {
                _noteContent.value = _noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _noteContent.value.text.isBlank()
                )
            }

            is CreateFoodBundleEvent.SaveFoodBundle -> {
                viewModelScope.launch {
                    Log.d("save", "saveFood")
                    try {
                        foodBundleUseCases.addFoodBundle(
                            FoodBundle(
                                title = title.value.text,
                                tags = tags,
                                customFoods = customFoodList,
                                isLike = currentFoodBundle?.isLike ?: false,
                                timestamp = System.currentTimeMillis(),
                                id = currentFoodBundleId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch(e: InvalidFoodBundleException) {
                        Log.d("save", e.message + "")
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }
}