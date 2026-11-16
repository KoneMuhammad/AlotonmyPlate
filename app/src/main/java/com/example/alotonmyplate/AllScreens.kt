package com.example.alotonmyplate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import androidx.navigation.compose.composable

@Serializable
object firstScreen
@Serializable
object secondScreen
@Serializable
object thirdScreen
@Serializable
object fourthScreen

val LocationOnePriority = mutableListOf<Priority>()
val LocationTwoPriority = mutableListOf<Priority>()
val LocationThreePriority = mutableListOf<Priority>()

fun store_Priority_Into_SubLists(priorityList: List<Priority>){
    for (priority in priorityList){
       when (priority.location){
           1 -> LocationOnePriority.add(priority)
           2 -> LocationTwoPriority.add(priority)
           3 -> LocationThreePriority.add(priority)
       }
    }
}
@Composable
fun allScreens(database: PriorityFunctions){
    val rememberPreviousPriorities = remember { mutableStateOf<List<Priority>>(listOf()) }

    val mover = rememberNavController()
    NavHost(navController = mover, startDestination = firstScreen){
      composable<firstScreen>{
          FirstScreen(mover)
          }
        composable<secondScreen>{
            SecondScreen(mover)
        }
        composable<thirdScreen>{
            ThirdScreen(mover)
        }
        composable<fourthScreen>{
            FourthScreen(mover)
        }

        }
    }
