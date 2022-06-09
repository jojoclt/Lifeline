package com.example.lifeline.data.local

import com.example.lifeline.domain.model.Priority
/*
enum class InJar(){
    COFFEE, MILK, ICE,
}*/


data class SampleTask(
    val name: String,
    val type: String, //"MILK", "ICE", "COFFEE"
    //val timeuse: Long,
)
object SampleList {

    val simpletasklist = listOf(
        SampleTask("Buy Milk", "MILK"),
        SampleTask("Buy Peanut", "ICE"),
        SampleTask("Do laundry", "ICE"),
        SampleTask("Finish Software studio project","COFFEE"),
        SampleTask("Look at professor profile", "COFFEE"),
        SampleTask("Wake KK and Audrey up", "MILK"),
        SampleTask( "LOL", "MILK")
    )
}