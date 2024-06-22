package kr.trueme.composetest.ui.presentation.screen.calendar.entity

import com.google.gson.annotations.SerializedName

data class BriefSchedule(
    @SerializedName("scheduleId")
    var scheduleId: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("appointmentTime")
    val appointmentTime: String
)