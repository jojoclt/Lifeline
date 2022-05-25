package com.example.lifeline.data.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.lifeline.data.local.TaskData
import com.example.lifeline.data.local.TaskDatabase
import com.example.lifeline.domain.Repository

class RepositoryImpl constructor(
    taskDatabase: TaskDatabase
) : Repository {

    private var TaskDao = taskDatabase.TaskDao()


}