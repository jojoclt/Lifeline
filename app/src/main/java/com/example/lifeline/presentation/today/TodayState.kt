package com.example.lifeline.presentation.today

import com.example.lifeline.domain.model.TaskData

data class TodayState(
    val tasks: List<TaskData> = emptyList(),
)