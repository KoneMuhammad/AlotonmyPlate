package com.example.alotonmyplate

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
            4 -> LocationFourPriority.add(priority)
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

        enableEdgeToEdge()
        setContent {
            val firstScreenPriorityState = remember { mutableStateListOf<Priority>() }
            val secondScreenPriorityState = remember { mutableStateListOf<Priority>()}
            val thirdScreenPriorityState = remember { mutableStateListOf<Priority>() }
            val fourthScreenPriorityState = remember { mutableStateListOf<Priority>()}

            LaunchedEffect(Unit) {
                all_The_Screens_Priorities = withContext(Dispatchers.IO){ talkToDb.getAll() }

                Log.i("DatabaseList", "${all_The_Screens_Priorities}")

                for(priority in all_The_Screens_Priorities){
                    when(priority.location){
                        1 -> firstScreenPriorityState.add(priority)
                        2 -> secondScreenPriorityState.add(priority)
                        3 -> thirdScreenPriorityState.add(priority)
                        4 -> fourthScreenPriorityState.add(priority)
                    }
                }
            }
            navigationContainer(firstScreenPriorityState, secondScreenPriorityState,thirdScreenPriorityState, fourthScreenPriorityState)
        }
    }
}
// data is initially null, find a way to populate it so i can read it and write to it
// putting that inside in the firs tplace fromt he ssd i need to sort it form top to bottom
// so the actual ui elements can access their correct elemenets with ease
