package com.example.lifeline.presentation.task

import com.example.lifeline.domain.model.Priority
import java.util.*

data class AddEditTodoState(
    var taskName: String = "",
    var date: Date = Calendar.getInstance().time,
    var desc: String = "",
    var priority: Priority = Priority.ESPRESSO,
    var duration: Int = 0,
    var id: Int? = null
)
