package com.example.lifeline.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lifeline.domain.model.TaskData
import com.example.lifeline.domain.model.TaskType
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM TaskData")
    fun getAllTasks(): Flow<List<TaskData>>

    @Query("SELECT * From TaskData WHERE type = :taskType")
    fun getTasksByType(taskType: TaskType): TaskData?

    @Query("SELECT * FROM TaskData WHERE id = :id")
    fun getTaskById(id: Int): TaskData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun editTask(taskData: TaskData)

    @Query("DELETE FROM TaskData WHERE id = :id")
    fun deleteTask(id: Int)

    @Query("UPDATE TaskData SET is_checked = :isComplete WHERE id = :id")
    fun markedTask(isComplete: Boolean, id: Int)

    @Query("UPDATE TaskData SET linked_id = :ptr WHERE id = :id")
    fun updateLink(ptr: Int, id: Int)

//    @Query("SELECT linked_id, type FROM TaskData WHERE id = :id")
//    fun getLink(id: Int): Link

    @Query("SELECT * FROM TaskData WHERE linked_id = -1 AND type = :type")
    fun getUnplannedTask(type: TaskType): Flow<List<TaskData>>
}