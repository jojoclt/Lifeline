package com.example.lifeline.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lifeline.domain.model.Priority
import com.example.lifeline.domain.model.Type
import java.util.*

@Entity
data class TaskData(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "name") var taskName: String,
    @ColumnInfo(name = "type") var taskType: Type,
    @ColumnInfo(name = "date") var date: Date,
    @ColumnInfo(name = "duration") var duration: String,
    @ColumnInfo(name = "time") var time: String,
    @ColumnInfo(name = "priority") var priority: Priority,
    @ColumnInfo(name = "description") var description: String
)
