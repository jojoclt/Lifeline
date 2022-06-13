package com.example.lifeline.presentation.today

import com.example.lifeline.domain.model.TaskData

sealed class TodayEvent {
    data class ToggleButton(val isChecked: Boolean, val value: TaskData): TodayEvent()
}