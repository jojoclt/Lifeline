package com.example.lifeline.domain.model

import java.util.*

data class Task(
    var id: Int,
    var isChecked: Boolean,
    var taskName: String,
    var taskType: Type,
    var date: Date,
    var duration: String, // Todo
    var time: String, // Deadline
    var priority: Priority, // Todo
    var description: String
)

enum class Type {
    TODO, DEADLINE
}

enum class Priority {
    ESPRESSO, MILK, ICE
}