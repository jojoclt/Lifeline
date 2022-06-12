package com.example.lifeline.domain.use_case

import com.example.lifeline.domain.Repository
import com.example.lifeline.domain.model.TaskData
import kotlinx.coroutines.flow.Flow

class GetUnplannedDeadlines(
    private val repository: Repository
) {
    suspend operator fun invoke(): Flow<List<TaskData>> {
        return repository.getUnplannedDeadlines()
    }
}