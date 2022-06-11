package com.example.lifeline.data.local

import android.icu.util.Calendar
import com.example.lifeline.domain.model.Priority
import com.example.lifeline.domain.model.TaskData
import com.example.lifeline.domain.model.TaskType

val dummy = listOf(
    TaskData(
        id = 1,
        taskName = "FIRST TASK",
        taskType = TaskType.TODO,
        date = Calendar.getInstance().time,
        priority = Priority.MILK
    ),
    TaskData(
        id = 2,
        taskName = "Second TASK",
        taskType = TaskType.TODO,
        date = Calendar.getInstance().time,
        priority = Priority.ESPRESSO
    ),
    TaskData(
        id = 3,
        taskName = "TASK",
        taskType = TaskType.TODO,
        date = Calendar.getInstance().time,
        priority = Priority.ICE
    )
)

