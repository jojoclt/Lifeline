package com.example.lifeline.domain.use_case

import com.example.lifeline.domain.Repository

class GetTaskById(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int) {
        repository.getTasksById(id)
    }
}
