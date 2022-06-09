package com.example.lifeline.domain.use_case

import com.example.lifeline.domain.Repository
import com.example.lifeline.domain.model.TaskData

class EditTask(
    private val repository: Repository
) {
    suspend operator fun invoke(taskData: TaskData) {
        repository.editTask(taskData)
    }
}
