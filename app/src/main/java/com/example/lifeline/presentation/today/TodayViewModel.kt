package com.example.lifeline.presentation.today

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifeline.domain.model.TaskData
import com.example.lifeline.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

private const val TAG = "TodayViewModel"

@HiltViewModel
class TodayViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _state = mutableStateOf(TodayState())
    val state: State<TodayState> = _state

    private var getTasksJob: Job? = null


    init {
        getTasks()
    }

    fun getTasks(date: Date = Calendar.getInstance().time): List<TaskData> {
        viewModelScope.launch(Dispatchers.IO) {
            getTasksJob?.cancel()
            getTasksJob = useCases.getTasksByDate(date).onEach { task ->
                _state.value = state.value.copy(
                    tasks = task
                )
            }
                .launchIn(viewModelScope)
        }
        return state.value.tasks
    }

}
