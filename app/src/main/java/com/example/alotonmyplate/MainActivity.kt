package com.example.alotonmyplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.room3.Room
import androidx.sqlite.driver.AndroidSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
        }

        enableEdgeToEdge()
        setContent {
            allScreens(talkToDb)
        }
    }

}
