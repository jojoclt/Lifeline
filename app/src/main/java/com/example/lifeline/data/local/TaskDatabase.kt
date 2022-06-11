package com.example.lifeline.data.local

import android.content.Context
import androidx.room.*
import com.example.lifeline.domain.model.TaskData
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Database(entities = [TaskData::class], version = 1)
@TypeConverters(Converters::class)
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

class Converters {
    @Suppress("NOTHING_TO_INLINE")
    private inline fun <T : Enum<T>> T.toInt(): Int = this.ordinal

    private inline fun <reified T : Enum<T>> Int.toEnum(): T = enumValues<T>()[this]


//    @TypeConverter fun fromTaskTypeToInt(value: TaskType) = value.toInt()
//    @TypeConverter fun intToTaskType(value: Int) = value.toEnum<TaskType>()

    @TypeConverter fun fromDateToString(value: Date) : String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.US)
        return dateFormat.format(value)
    }

    @TypeConverter fun fromStringToDate(value: String) : Date {
        return SimpleDateFormat("yyyy/MM/dd", Locale.US).parse(value)!!
    }
}
