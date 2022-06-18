package com.example.lifeline.presentation.today

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifeline.domain.model.TaskData
import com.example.lifeline.domain.model.TaskType
import com.example.lifeline.domain.use_case.UseCases
import com.himanshoe.kalendar.common.data.KalendarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

private const val TAG = "TodayViewModel"

@HiltViewModel
class TodayViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _state = mutableStateOf(TodayState())
    val state: State<TodayState> = _state

    private val _calendarState = mutableStateOf<MutableList<KalendarEvent>>(mutableListOf())
    val calendarState: State<List<KalendarEvent>> = _calendarState

    private val _duration = mutableStateOf(0)
    val duration: State<Int> = _duration

    private var getTasksJob: Job? = null


    init {
        getTaskAll()
    }

    fun getAllTasksForCalendar(): List<KalendarEvent> {
        viewModelScope.launch(Dispatchers.IO) {
            getTasksJob?.cancel()
            getTasksJob = useCases.getAllTasks().onEach { task ->
                for (t in task) {
                    if (t.taskType == TaskType.DEADLINE)
                        _calendarState.value.add(KalendarEvent(LocalDate.parse(SimpleDateFormat("yyyy-MM-dd").format(t.date)), "Sample"))
                }

            }.launchIn(viewModelScope)
        }
        return calendarState.value
    }
    // getAllTask
    fun getTaskAll(date: Date = Calendar.getInstance().time): List<TaskData> {
        viewModelScope.launch(Dispatchers.IO) {
            getTasksJob?.cancel()
            getTasksJob = useCases.getTasksByDate(date).onEach { task ->
                _state.value = state.value.copy(
                    tasks = task
                )
            }
                .launchIn(viewModelScope)
            getTasksJob = useCases.getAllTasks().onEach { task ->
                for (t in task) {
                    if (t.taskType == TaskType.DEADLINE)
                        _calendarState.value.add(KalendarEvent(LocalDate.parse(SimpleDateFormat("yyyy-MM-dd").format(t.date)), "Sample"))
                }

            }.launchIn(viewModelScope)
        }
        return state.value.tasks
    }

    // getTodoTodayTask
    fun getTodoTask(
        date: Date = Calendar.getInstance().time,
        type: TaskType = TaskType.TODO
    ): List<TaskData> {
        viewModelScope.launch(Dispatchers.IO) {
            getTasksJob?.cancel()
            getTasksJob = useCases.getTaskTypeWithDate(date, type).onEach { task ->
                _state.value = state.value.copy(
                    tasks = task
                )
            }
                .launchIn(viewModelScope)
        }

        return state.value.tasks
    }

    fun getDuration(): Int {
        _duration.value = 0
        state.value.tasks.forEach { task ->
            if (!task.isChecked)
                _duration.value += task.duration
        }
        return _duration.value
    }

    fun onEvent(event: TodayEvent) {
        when (event) {
            is TodayEvent.ToggleButton -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        useCases.markedTask(
                            event.isChecked, event.id
                        )
                        Log.e("TodayViewModel", "Task Checked")

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
