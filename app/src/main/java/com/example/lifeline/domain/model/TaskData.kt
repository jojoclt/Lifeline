package com.example.lifeline.domain.model

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskData(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "is_checked") var isChecked: Boolean = false,
    @ColumnInfo(name = "name") var taskName: String,
    @ColumnInfo(name = "type") var taskType: TaskType? = null,
    @ColumnInfo(name = "date") var date: String?= null,
    @ColumnInfo(name = "duration") var duration: String?= null,
    @ColumnInfo(name = "time") var time: String?= null,
    @ColumnInfo(name = "priority") var priority: Priority?= null,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "linked_id") var ptr: Int? = null
)

enum class TaskType {
    TODO, DEADLINE
}

enum class Priority {
    ESPRESSO, MILK, ICE
}

data class Link(
    var ptr: Int,
    var taskType: TaskType
)

data class PriorityRes(
    @DrawableRes val img: Int,
    var priority: Priority
)