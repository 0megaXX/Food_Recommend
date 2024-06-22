package com.example.sub_pack

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import kr.trueme.composetest.ui.presentation.screen.question.FoodData

class FoodViewModel : ViewModel() {
    var foodMap = mutableStateOf<Map<String, Map<String, Any>>>(mapOf())
    var currentTrait = mutableStateOf("")
    var TempFood = mutableStateOf("무작위 음식 추천")
    var selectedFood = mutableStateOf("안녕하세요")
    var chooseFood = mutableStateOf("")
    var showButtons = mutableStateOf(false)
    var showActionButtons = mutableStateOf(true)
    var count_question = mutableStateOf(5)
    var circleColors = mutableStateListOf(Color.White, Color.White, Color.White, Color.White, Color.White)
    var circleTexts = mutableStateListOf("", "", "", "", "")  // 각 원에 표시할 텍스트
    var index = -1
    var tag_text = mutableStateOf("")

    var start_btn= mutableStateOf(true)
    var reset_btn= mutableStateOf(false)

    init {
        loadFoodData()
    }

    private fun loadFoodData() {
        foodMap.value = FoodData.getFoodMap()
    }

    fun startQuestions() {
        showButtons.value = true
        showActionButtons.value = false
        askQuestion()
    }

    fun processSelection(select: Boolean) {
        start_btn.value=false
        reset_btn.value=true

        index += 1
        if (count_question.value > 0) {
            val tempMap = mutableMapOf<String, Map<String, Any>>()
            foodMap.value.forEach { (key, value) ->
                val traits = value["속성"] as List<String>
                if (select && traits.contains(currentTrait.value)) {
                    val updatedTraits = traits.toMutableList().apply { remove(currentTrait.value) }
                    tempMap[key] = value.toMutableMap().apply { put("속성", updatedTraits) }
                    circleColors[index] = Color(0xFFFFA500) // 주황색
                    circleTexts[index] = currentTrait.value  // 속성 텍스트 저장

                }

                else if (!select && !traits.contains(currentTrait.value)) {
                    tempMap[key] = value
                    circleColors[index] = Color(0xFFF5F5F5) // 흰색

                }
            }


            foodMap.value = tempMap
            count_question.value -= 1
            /*
                        if(select)
                        {
                            tag_text.value += '#' + currentTrait.value + "(O)"

                            if(count_question.value>0)
                                tag_text.value +=", "
                            else
                                tag_text.value +=" "
                        }
                        else
                        {
                            tag_text.value += '#' + currentTrait.value + "(X)"
                            if(count_question.value>0)
                                tag_text.value +=", "
                            else
                                tag_text.value +=" "
                        }
            */



            if (tempMap.isNotEmpty() && count_question.value > 0)
            {
                if(select)
                    tag_text.value += '#' + currentTrait.value + "(O), "
                else
                    tag_text.value += '#' + currentTrait.value + "(X), "

                askQuestion()

            }
            else
            {
                if(select)
                    tag_text.value += '#' + currentTrait.value + "(O)"
                else
                    tag_text.value += '#' + currentTrait.value + "(X)"

                finalSelection()
            }
        }
    }

    fun finalSelection() {
        if (foodMap.value.isNotEmpty()) {
            val randomFood = foodMap.value.keys.random()
            TempFood.value = randomFood
            selectedFood.value = "이 음식은 어떤가요? : $randomFood"
            chooseFood.value = randomFood
            showButtons.value = false
            showActionButtons.value = false
        } else {
            selectedFood.value = "만족하시는 음식을 찾을 수 없어요"
            showButtons.value = false
            showActionButtons.value = false
        }
        showActionButtons.value = true

    }

    fun discardTrait() {
        foodMap.value = foodMap.value.mapValues { (key, value) ->
            value.toMutableMap().apply {
                val traits = (get("속성") as List<String>).filter { it != currentTrait.value }
                put("속성", traits)
            }
        }
        askQuestion()
    }

    private fun askQuestion() {
        if (count_question.value > 0) {
            val allTraits = foodMap.value.values.flatMap { it["속성"] as List<String> }.distinct()
            if (allTraits.isNotEmpty()) {
                currentTrait.value = allTraits.random()
                selectedFood.value = "'${currentTrait.value}' 음식을\n 원하시나요?"
            } else {
                finalSelection()
            }
        } else {
            finalSelection()
        }
    }

    fun reset() {
        loadFoodData()
        selectedFood.value = "안녕하세요"
        TempFood.value = "무작위 음식 추천"
        showButtons.value = false
        currentTrait.value = ""
        count_question.value = 5
        showActionButtons.value = true
        index = -1
        circleColors.fill(Color.White)
        circleTexts.fill("")
        tag_text.value =""
        start_btn.value=true
        reset_btn.value=false
    }

    fun updateCircleText(index: Int, text: String) {
        if (index in circleTexts.indices) {
            circleTexts[index] = text
        }
    }
}