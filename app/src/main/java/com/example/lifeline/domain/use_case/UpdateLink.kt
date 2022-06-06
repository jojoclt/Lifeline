package com.example.lifeline.domain.use_case

import com.example.lifeline.domain.Repository

class UpdateLink(
    private val repository: Repository
) {
    suspend operator fun invoke(ptr: Int, id: Int) {
        repository.updateLink(ptr, id)
    }
}
