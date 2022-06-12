package com.example.lifeline.domain.use_case

import com.example.lifeline.domain.Repository
import com.example.lifeline.domain.model.TaskData
import com.example.lifeline.domain.model.TaskType
import kotlinx.coroutines.flow.Flow
import java.util.*

class GetTaskTypeWithDate(private val repository: Repository) {
    suspend operator fun invoke(date: Date, type: TaskType): Flow<List<TaskData>> {
        return repository.getTaskTypeWithDate(date, type)
    }
}
