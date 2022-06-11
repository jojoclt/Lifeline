package com.example.lifeline.domain.model

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lifeline.R
import java.util.*

@Entity
data class TaskData(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "is_checked") var isChecked: Boolean = false,
    @ColumnInfo(name = "name") var taskName: String,
    @ColumnInfo(name = "type") var taskType: TaskType,
    @ColumnInfo(name = "date") var date: Date,
    @ColumnInfo(name = "duration") var duration: Int = 0, // task to do
    @ColumnInfo(name = "time") var time: String = "", // deadline
    @ColumnInfo(name = "priority") var priority: Priority,
    @ColumnInfo(name = "description") var description: String = "",
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

val priorityList = listOf(R.drawable.p_coffee, R.drawable.p_milk, R.drawable.p_ice)