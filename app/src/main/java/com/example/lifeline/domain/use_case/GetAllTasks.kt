package com.example.lifeline.domain.use_case

import com.example.lifeline.domain.Repository
import com.example.lifeline.domain.model.Task

class GetAllTasks(
    private val repository: Repository
) {
    operator fun invoke() {
        repository.getAllTasks()
    }
}