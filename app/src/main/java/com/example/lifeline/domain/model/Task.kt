package com.example.lifeline.domain.model

import androidx.annotation.DrawableRes
import java.util.*

enum class TaskType {
    TODO, DEADLINE
}

enum class Priority {
    ESPRESSO, MILK, ICE
}

data class Link(
    var ptr: Int,
    var taskType: TaskType
)

data class PriorityRes(
    @DrawableRes val img: Int,
    var priority: Priority
)