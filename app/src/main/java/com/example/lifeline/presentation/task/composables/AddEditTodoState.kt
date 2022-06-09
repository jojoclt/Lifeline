package com.example.lifeline.presentation.task.composables

data class AddEditTodoState(
    var taskName: String = "",
    val date: String = "",
    var desc: String = ""
)
