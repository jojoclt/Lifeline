package com.example.lifeline.domain.model

import androidx.annotation.DrawableRes
import java.util.*

data class Task(
    var id: Int,
    var isChecked: Boolean,
    var taskName: String,
    var taskType: TaskType,
    var date: Date,
    var duration: String, // Todo
    var time: String, // Deadline
    var priority: Priority, // Todo
    var description: String
)

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