package com.example.lifeline.data.repository

import com.example.lifeline.data.local.TaskDatabase
import com.example.lifeline.domain.Repository
import com.example.lifeline.domain.model.TaskData
import com.example.lifeline.domain.model.TaskType
import kotlinx.coroutines.flow.Flow
import java.util.*

class RepositoryImpl constructor(
    taskDatabase: TaskDatabase
) : Repository {

    private var taskDao = taskDatabase.TaskDao()

    override fun getAllTasks(): Flow<List<TaskData>> =
        taskDao.getAllTasks()


    override suspend fun getTasksByType(taskType: TaskType): TaskData? =
        taskDao.getTasksByType(taskType)

    override suspend fun getTasksByDate(date: Date): Flow<List<TaskData>> =
        taskDao.getTasksByDate(date)


    override suspend fun getTasksById(id: Int): TaskData? =
        taskDao.getTaskById(id)


    override suspend fun editTask(taskData: TaskData) {
        taskDao.editTask(taskData)
    }

    override suspend fun deleteTask(id: Int) {
        taskDao.deleteTask(id)
    }

    override suspend fun markedTask(isComplete: Boolean, id: Int) {
        taskDao.markedTask(isComplete, id)

//        val curTask = getLink(id)
//        if (curTask.taskType == TaskType.DEADLINE && curTask.ptr != -1) taskDao.markedTask(isComplete, curTask.ptr)
    }

    override suspend fun updateLink(ptr: Int, id: Int) {
        taskDao.updateLink(ptr, id)
    }

//    override suspend fun getLink(id: Int): Link =
//        taskDao.getLink(id)

    override suspend fun getUnplannedDeadlines(): Flow<List<TaskData>> =
        taskDao.getUnplannedTask(TaskType.DEADLINE)

}