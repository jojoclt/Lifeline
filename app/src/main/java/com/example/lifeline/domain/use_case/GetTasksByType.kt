package com.example.lifeline.domain.use_case

import com.example.lifeline.domain.Repository
import com.example.lifeline.domain.model.TaskData
import com.example.lifeline.domain.model.TaskType

class GetTasksByType(
    private val repository: Repository
) {
    suspend operator fun invoke(taskType: TaskType): TaskData {
        return repository.getTasksByType(taskType)!!
    }
}
