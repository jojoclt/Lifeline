package com.example.lifeline.domain.use_case

import com.example.lifeline.data.local.TaskData
import com.example.lifeline.domain.Repository

class EditTask(
    private val repository: Repository
) {
    suspend operator fun invoke(taskData: TaskData) {
        repository.editTask(taskData)
    }
}
