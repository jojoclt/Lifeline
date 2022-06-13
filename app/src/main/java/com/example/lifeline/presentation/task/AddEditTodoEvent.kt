package com.example.lifeline.presentation.task

import com.example.lifeline.domain.model.Priority
import com.example.lifeline.domain.model.TaskType
import java.util.*

sealed class AddEditTodoEvent {
    data class SaveNote(val value: TaskType) : AddEditTodoEvent()
    data class EnteredTitle(val value: String): AddEditTodoEvent()
    data class EnteredDescription(val value: String): AddEditTodoEvent()
    data class EnteredPriority(val value: Priority ): AddEditTodoEvent()
    data class EnteredDate(val value: Date): AddEditTodoEvent()
    data class EnteredDuration(val value: Int): AddEditTodoEvent()
    data class EnteredTime(val value: String): AddEditTodoEvent()
    object DeleteNote: AddEditTodoEvent()
}