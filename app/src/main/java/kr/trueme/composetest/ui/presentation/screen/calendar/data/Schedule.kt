package kr.trueme.composetest.ui.presentation.screen.calendar.data

data class Schedule(
    val year: Int,
    val month: Int,
    val date: Int,
    val scheduleCount: Int = 0
)