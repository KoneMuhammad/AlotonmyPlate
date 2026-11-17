package com.example.alotonmyplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.room3.Room
import androidx.sqlite.driver.AndroidSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun store_Priority_Into_SubLists(priorityList: List<Priority>){
    for (priority in priorityList){
        when (priority.location){
            1 -> LocationOnePriority.add(priority)
            2 -> LocationTwoPriority.add(priority)
            3 -> LocationThreePriority.add(priority)
        }
    }
}
class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val databaseTalker = Room.databaseBuilder<AppDatabase>(applicationContext, "priority")
            .setDriver(AndroidSQLiteDriver())
            .build()
        val talkToDb = databaseTalker.PriorityFunctions()

        var all_The_Screens_Priorities: List<Priority> = listOf()

        lifecycleScope.launch {
            all_The_Screens_Priorities =  withContext(Dispatchers.IO){ talkToDb.getAll() }
            store_Priority_Into_SubLists(all_The_Screens_Priorities)
        }

        enableEdgeToEdge()
        setContent {
            allScreens()
        }
    }

}
