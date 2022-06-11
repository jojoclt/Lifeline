package com.example.lifeline.presentation.today

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifeline.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

const val TAG = "TodayViewModel"
@HiltViewModel
class TodayViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _state = mutableStateOf(TodayState())
    val state: State<TodayState> = _state

    private var getTasksJob: Job? = null


    init {
        viewModelScope.launch(Dispatchers.IO) {
            getTasks()
        }
    }
    private suspend fun getTasks() {
        getTasksJob?.cancel()
        getTasksJob = useCases.getTasksByDate(Calendar.getInstance().time).onEach { task ->
            _state.value = state.value.copy(
                tasks = task
            )
        }
            .launchIn(viewModelScope)
    }
}
