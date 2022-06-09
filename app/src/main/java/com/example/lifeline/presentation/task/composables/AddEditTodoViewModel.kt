package com.example.lifeline.presentation.task.composables


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifeline.domain.model.TaskData
import com.example.lifeline.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _taskEntry = mutableStateOf(AddEditTodoState())
    val taskEntry: State<AddEditTodoState> = _taskEntry

    private var currentTaskId: Int? = null

    init {

    }

    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        useCases.editTask(
                            TaskData(
                                taskName = taskEntry.value.taskName
                            )
                        )
                    }
                    catch (e: Exception) {
                        Log.e("ERROR", "ERROR IN CODE: $e")
                        // this is the line that prints out the location in
                        // the code where the error occurred.
                        e.printStackTrace();
                    }
                    finally {
                        Log.d("AddEditTodoViewModel", "task added!")
                    }
                }
            }

            is AddEditTodoEvent.EnteredTitle -> {
                _taskEntry.value = taskEntry.value.copy(
                    taskName = event.value
                )
            }

        }

    }
}