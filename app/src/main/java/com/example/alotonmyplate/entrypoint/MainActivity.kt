package com.hatspoon.importantnow.entrypoint

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.lifecycleScope
import androidx.room3.Room
import androidx.sqlite.driver.AndroidSQLiteDriver
import com.example.alotonmyplate.database.AppDatabase
import com.example.alotonmyplate.ui.screens.LocationFourPriority
import com.example.alotonmyplate.ui.screens.LocationOnePriority
import com.example.alotonmyplate.ui.screens.LocationThreePriority
import com.example.alotonmyplate.ui.screens.LocationTwoPriority
import com.example.alotonmyplate.database.Priority
import com.example.alotonmyplate.ui.screens.navigationContainer
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

        val firstScreenPriorityState =  mutableStateListOf<Priority>()
        val secondScreenPriorityState = mutableStateListOf<Priority>()
        val thirdScreenPriorityState = mutableStateListOf<Priority>()
        val fourthScreenPriorityState = mutableStateListOf<Priority>()

        var all_The_Screens_Priorities: List<Priority> = listOf()

        lifecycleScope.launch {
            all_The_Screens_Priorities = withContext(Dispatchers.IO){ talkToDb.getAll()
            }
            Log.i("Database", "${all_The_Screens_Priorities}")

            for(priority in all_The_Screens_Priorities){
                when(priority.location){
                    1 -> firstScreenPriorityState.add(priority)
                    2 -> secondScreenPriorityState.add(priority)
                    3 -> thirdScreenPriorityState.add(priority)
                    4 -> fourthScreenPriorityState.add(priority)
                }
            }
        }
        enableEdgeToEdge()
        setContent {

            val isSaveTrue = rememberSaveable { mutableStateOf(false) }

            val firstScreen = remember { firstScreenPriorityState }
            val secondScreen = remember { secondScreenPriorityState }
            val thirdScreen = remember { thirdScreenPriorityState }
            val fourthScreen = remember { fourthScreenPriorityState }

                LaunchedEffect(isSaveTrue.value) {

                    if (isSaveTrue.value) {
                        val talkBackToDatabaseArray: MutableList<Priority> = mutableListOf()

                        firstScreen.forEach { eachone ->
                            talkBackToDatabaseArray.add(eachone)
                        }
                        secondScreen.forEach { eachone ->
                            talkBackToDatabaseArray.add(eachone)
                        }
                        thirdScreen.forEach { eachone ->
                            talkBackToDatabaseArray.add(eachone)
                        }
                        fourthScreen.forEach { eachone ->
                            talkBackToDatabaseArray.add(eachone)
                        }


                        Log.i("TalkTo", "${talkBackToDatabaseArray}")
                        withContext(Dispatchers.IO) {
                            talkToDb.updateDatabase(
                                talkBackToDatabaseArray
                            )
                        }
                    }
                }

            navigationContainer(isSaveTrue,
                firstScreen,
                secondScreen,
                thirdScreen,
                fourthScreen
            )
        }
    }
}
