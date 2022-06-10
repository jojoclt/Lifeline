package com.example.lifeline.domain.use_case

import com.example.lifeline.domain.Repository
import java.util.*

class GetTasksByDate(
    private val repository: Repository
) {
    suspend operator fun invoke(date: Date) {
        repository.getTasksByDate(date)
    }
}
