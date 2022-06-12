package com.example.lifeline.presentation.task


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifeline.domain.model.TaskData
import com.example.lifeline.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "AddEditTodoViewModel"

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _taskEntry = mutableStateOf(AddEditTodoState())
    val taskEntry: State<AddEditTodoState> = _taskEntry

    private val _bool = mutableStateOf(false)
    val bool: State<Boolean> = _bool

    private var getTaskJob: Job? = null

    init {
        // have to switch thread to Main for some reason to work

        savedStateHandle.get<Int>("taskId")?.let { taskId ->
            Log.d(TAG, "VALUE IS $taskId")
            if (taskId != -1) {

                _bool.value = true
                viewModelScope.launch(Dispatchers.IO) {
                    val childList = mutableListOf<AddEditTodoState>()

                    useCases.getTaskById(taskId).also { task ->

                        childList.add(
                            AddEditTodoState(
                                taskName = task.taskName,
                                date = task.date,
                                desc = task.description,
                                priority = task.priority,
                                duration = task.duration,
                                id = task.id
                            )
                        )
                    }

                    viewModelScope.launch(Dispatchers.Main) {
//                        with(childList[0]) {
//                            _taskEntry.value.taskName = taskName
//                            _taskEntry.value.date = date
//                            _taskEntry.value.desc = desc
//                            _taskEntry.value.priority = priority
//                            _taskEntry.value.duration = duration
//                            _taskEntry.value.id = id
//                        }
                        _taskEntry.value = childList[0]
                    }
                }
            }
        }
    }


    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.SaveNote -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        useCases.editTask(
                            TaskData(
                                id = taskEntry.value.id,
                                taskName = taskEntry.value.taskName,
                                date = taskEntry.value.date,
                                duration = taskEntry.value.duration,
                                priority = taskEntry.value.priority,
                                description = taskEntry.value.desc,
                                taskType = event.value
                            )

                        )
                        Log.v(TAG, "finish adding to database")
                    } catch (e: Exception) {
                        Log.e("ERROR", "ERROR IN CODE: $e")
                        // this is the line that prints out the location in
                        // the code where the error occurred.
                        e.printStackTrace()
                    }
                }
            }

            is AddEditTodoEvent.EnteredTitle -> {
                _taskEntry.value = taskEntry.value.copy(
                    taskName = event.value
                )
            }

            is AddEditTodoEvent.EnteredDescription -> {
                _taskEntry.value = taskEntry.value.copy(
                    desc = event.value
                )
            }

            is AddEditTodoEvent.EnteredDate -> {
                _taskEntry.value = taskEntry.value.copy(
                    date = event.value
                )
            }

            is AddEditTodoEvent.EnteredDuration -> {
                _taskEntry.value = taskEntry.value.copy(
                    duration = event.value
                )
            }

            is AddEditTodoEvent.EnteredPriority -> {
                _taskEntry.value = taskEntry.value.copy(
                    priority = event.value
                )
            }
            is AddEditTodoEvent.DeleteNote -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        useCases.deleteTask(
                            taskEntry.value.id!!
                        )

                        Log.v(TAG, "Task Deleted")
                    } catch (e: Exception) {
                        Log.e("ERROR", "ERROR IN CODE: $e")
                        // this is the line that prints out the location in
                        // the code where the error occurred.
                        e.printStackTrace()
                    }
                }
            }

        }

    }
}