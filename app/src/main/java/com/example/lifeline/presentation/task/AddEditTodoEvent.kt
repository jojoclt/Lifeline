package com.example.lifeline.presentation.task

import com.example.lifeline.domain.model.Priority
import java.util.*

sealed class AddEditTodoEvent {
    object SaveNote: AddEditTodoEvent()
    data class EnteredTitle(val value: String): AddEditTodoEvent()
    data class EnteredDescription(val value: String): AddEditTodoEvent()
    data class EnteredPriority(val value: Priority ): AddEditTodoEvent()
    data class EnteredDate(val value: Date): AddEditTodoEvent()
    data class EnteredDuration(val value: Int): AddEditTodoEvent()
}