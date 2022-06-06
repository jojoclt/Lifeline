package com.example.lifeline.di

import android.app.Application
import com.example.lifeline.data.local.TaskDatabase
import com.example.lifeline.data.repository.RepositoryImpl
import com.example.lifeline.domain.Repository
import com.example.lifeline.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): TaskDatabase {
        return TaskDatabase.getDatabase(application)
    }

    @Singleton
    @Provides
    fun provideRepository(
        database: TaskDatabase
    ): Repository = RepositoryImpl(database)

    @Singleton
    @Provides
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            getAllTasks = GetAllTasks(repository),
            getTasksByType = GetTasksByType(repository),
            getTaskById = GetTaskById(repository),
            editTask = EditTask(repository),
            deleteTask = DeleteTask(repository),
            markedTask = MarkedTask(repository),
            updateLink = UpdateLink(repository),
            getLink = GetLink(repository),
            getUnplannedDeadlines = GetUnplannedDeadlines(repository)
        )
    }
}