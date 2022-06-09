package com.example.lifeline.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lifeline.domain.model.TaskData

@Database(entities = [TaskData::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun TaskDao(): TaskDao

    companion object {
        private var instance: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            if (instance == null) {
                synchronized(TaskDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context,
                            TaskDatabase::class.java,
                            "task.db"
                        )
                            .build()
                    }
                }
            }
            return instance!!
        }
    }
}