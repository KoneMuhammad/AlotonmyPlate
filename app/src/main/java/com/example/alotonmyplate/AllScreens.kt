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
@Composable
fun allScreens(locationOnePriorities: MutableList<Priority>,
               locationTwoPriorities: MutableList<Priority>,
               locationThreePriorities: MutableList<Priority>){
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
