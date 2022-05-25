package com.example.lifeline.util

enum class FABScreen(val title: String) {
    Todo(title = "Add Task"),
    Deadline(title = "Add Deadline");

    companion object {
        fun fromRoute(route: String?): FABScreen =
            when (route?.substringBefore("/")) {
                Todo.name -> Todo
                Deadline.name -> Deadline
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}