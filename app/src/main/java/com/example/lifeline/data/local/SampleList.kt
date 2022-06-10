package com.example.lifeline.data.local

import com.example.lifeline.domain.model.Priority
/*
enum class InJar(){
    COFFEE, MILK, ICE,
}*/


data class SampleTask(
    val name: String,
    val priority: Priority
)
object SampleList {

    val simpletasklist = listOf(
        SampleTask("Do laundry", Priority.MILK),
        SampleTask("Buy Milk", Priority.ESPRESSO),
        SampleTask("Buy Peanut", Priority.ICE),
        SampleTask("Finish Software studio project",Priority.ESPRESSO),
        SampleTask("Look at professor profile", Priority.ESPRESSO),
        SampleTask("Wake KK and Audrey up", Priority.MILK),
    )
}