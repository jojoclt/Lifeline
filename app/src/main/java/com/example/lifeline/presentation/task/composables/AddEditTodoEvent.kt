package com.example.lifeline.presentation.task.composables

sealed class AddEditTodoEvent {
    object SaveNote: AddEditTodoEvent()
    data class EnteredTitle(val value: String): AddEditTodoEvent()
    data class EnteredDescription(val value: String): AddEditTodoEvent()

}