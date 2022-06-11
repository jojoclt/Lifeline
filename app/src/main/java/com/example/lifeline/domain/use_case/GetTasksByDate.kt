package com.example.lifeline.domain.use_case

import com.example.lifeline.domain.Repository
import com.example.lifeline.domain.model.TaskData
import kotlinx.coroutines.flow.Flow
import java.util.*

class GetTasksByDate(
    private val repository: Repository
) {
    suspend operator fun invoke(date: Date): Flow<List<TaskData>> {
        return repository.getTasksByDate(date)
    }
}
