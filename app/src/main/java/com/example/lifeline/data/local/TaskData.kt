package com.example.lifeline.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lifeline.domain.model.Priority
import com.example.lifeline.domain.model.TaskType
import java.util.*

@Entity
data class TaskData(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "is_checked") var isChecked: Boolean = false,
    @ColumnInfo(name = "name") var taskName: String,
    @ColumnInfo(name = "type") var taskType: TaskType? = null,
    @ColumnInfo(name = "date") var date: Date?= null,
    @ColumnInfo(name = "duration") var duration: String?= null,
    @ColumnInfo(name = "time") var time: String?= null,
    @ColumnInfo(name = "priority") var priority: Priority?= null,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "linked_id") var ptr: Int? = null
)
