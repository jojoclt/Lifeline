package com.example.lifeline.domain.use_case

import com.example.lifeline.domain.Repository

class MarkedTask(
    private val repository: Repository
) {
    suspend operator fun invoke(isComplete: Boolean, id: Int) {
        repository.markedTask(isComplete, id)
    }
}
