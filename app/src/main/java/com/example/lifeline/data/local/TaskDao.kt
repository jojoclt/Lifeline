package com.example.lifeline.data.local

import androidx.room.*
import com.example.lifeline.domain.model.Priority
import com.example.lifeline.domain.model.Type
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface TaskDao {

    @Query("SELECT * FROM TaskData WHERE name = :taskName")
    fun getTaskByName(taskName: String): Flow<List<TaskData>>

    @Insert
    fun setTask(taskData: TaskData): Long

    @Query("DELETE FROM TaskData WHERE id = :id")
    fun deleteTask(id: Int)

    @Query("UPDATE TaskData SET name = :taskName, date = :date, duration = :duration, time = :time, priority = :priority, description = :description WHERE id =:id")
    fun editTask(
        id: Int,
        taskName: String,
        taskType: Type,
        date: Date,
        duration: String,
        time: String,
        priority: Priority,
        description: String
    )

}