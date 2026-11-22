package com.example.alotonmyplate.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import androidx.navigation.compose.composable
import com.example.alotonmyplate.database.Priority

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
val LocationFourPriority = mutableListOf<Priority>()
@Composable
fun navigationContainer(isSave: MutableState<Boolean>,
    locationOnePriorities: SnapshotStateList<Priority>,
                        locationTwoPriorities: SnapshotStateList<Priority>,
                        locationThreePriorities: SnapshotStateList<Priority>,
                        locationFourPriorities: SnapshotStateList<Priority>){

    val mover = rememberNavController()
    NavHost(navController = mover, startDestination = firstScreen){
      composable<firstScreen>{
          FirstScreen(isSave, mover, locationOnePriorities)
          }
        composable<secondScreen>{
            SecondScreen(isSave, mover, locationTwoPriorities)
        }
        composable<thirdScreen>{
            ThirdScreen(isSave, mover, locationThreePriorities)
        }
        composable<fourthScreen>{
            FourthScreen(isSave, mover, locationFourPriorities)
        }
        }
    }
