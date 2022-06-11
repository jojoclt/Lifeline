package com.example.lifeline.presentation.today
//
//import android.util.Log
//import androidx.compose.runtime.State
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.lifeline.domain.model.TaskData
//import com.example.lifeline.domain.model.TaskType
//import com.example.lifeline.domain.use_case.UseCases
//import com.example.lifeline.presentation.task.AddEditTodoEvent
//import com.example.lifeline.presentation.task.AddEditTodoState
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//const val TAG = "TodayViewModel"
//@HiltViewModel
//class TodayViewModel @Inject constructor(
//    private val useCases: UseCases,
//    savedStateHandle: SavedStateHandle
//) : ViewModel() {
//
//    private val _tasks = mutableStateOf(TodayState())
//    val tasks: State<TodayState> = _tasks
//
//    private var currentTaskId: Int? = null
//
//    init {
//
//    }
//
//    fun onEvent(event: AddEditTodoEvent) {
//        when (event) {
//            is AddEditTodoEvent.SaveNote -> {
//                viewModelScope.launch(Dispatchers.IO) {
//                    try {
//                        useCases.editTask(
//                            TaskData(
//                                id = currentTaskId,
//                                taskName = taskEntry.value.taskName,
//                                date = taskEntry.value.date,
//                                duration = taskEntry.value.duration,
//                                priority = taskEntry.value.priority,
//                                description = taskEntry.value.desc,
//                                taskType = TaskType.TODO
//                            )
//
//                        )
//                        Log.v(TAG, "finish adding to database")
//                    }
//                    catch (e: Exception) {
//                        Log.e("ERROR", "ERROR IN CODE: $e")
//                        // this is the line that prints out the location in
//                        // the code where the error occurred.
//                        e.printStackTrace()
//                    }
//                }
//            }
//
//            is AddEditTodoEvent.EnteredTitle -> {
//                _taskEntry.value = taskEntry.value.copy(
//                    taskName = event.value
//                )
//            }
//
//            is AddEditTodoEvent.EnteredDescription -> {
//                _taskEntry.value = taskEntry.value.copy(
//                    desc = event.value
//                )
//            }
//
//            is AddEditTodoEvent.EnteredDate -> {
//                _taskEntry.value = taskEntry.value.copy(
//                    date = event.value
//                )
//            }
//
//            is AddEditTodoEvent.EnteredDuration -> {
//                _taskEntry.value = taskEntry.value.copy(
//                    duration = event.value
//                )
//            }
//
//            is AddEditTodoEvent.EnteredPriority -> {
//                _taskEntry.value = taskEntry.value.copy(
//                    priority = event.value
//                )
//            }
//
//        }
//
//    }
