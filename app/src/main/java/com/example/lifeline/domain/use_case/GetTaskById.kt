package com.example.lifeline.domain.use_case

import com.example.lifeline.domain.Repository
import com.example.lifeline.domain.model.TaskData

class GetTaskById(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int): TaskData {
        return repository.getTasksById(id)!!
    }
}
