package com.example.lifeline.domain.use_case

data class UseCases(
    val getAllTasks: GetAllTasks,
    val getTasksByType: GetTasksByType,
    val getTaskById: GetTaskById,
    val getTasksByDate: GetTasksByDate,
    val getTaskTypeWithDate: GetTaskTypeWithDate,
    val editTask: EditTask,
    val deleteTask: DeleteTask,
    val markedTask: MarkedTask,
    val updateLink: UpdateLink,
    val getLink: GetLink,
    val getUnplannedDeadlines: GetUnplannedDeadlines
)
