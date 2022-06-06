package com.example.lifeline.domain.use_case

import com.example.lifeline.domain.Repository
import com.example.lifeline.domain.model.TaskType

class GetUnplannedDeadlines(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        repository.getUnplannedDeadlines()
    }
}