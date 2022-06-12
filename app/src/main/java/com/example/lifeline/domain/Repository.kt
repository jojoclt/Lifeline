package com.example.lifeline.domain

import com.example.lifeline.domain.model.TaskData
import com.example.lifeline.domain.model.TaskType
import kotlinx.coroutines.flow.Flow
import java.util.*

interface Repository {

    fun getAllTasks(): Flow<List<TaskData>>

    suspend fun getTasksByType(taskType: TaskType): TaskData?

    suspend fun getTasksById(id: Int): TaskData?

    suspend fun getTasksByDate(date: Date): Flow<List<TaskData>>

    suspend fun getTaskTypeWithDate(date: Date, type: TaskType): Flow<List<TaskData>>

    suspend fun editTask(taskData: TaskData)

    suspend fun deleteTask(id: Int)

    suspend fun markedTask(isComplete: Boolean, id: Int)

    suspend fun updateLink(ptr: Int, id: Int)


//    suspend fun getLink(id: Int): Link

    suspend fun getUnplannedDeadlines(): Flow<List<TaskData>>

}