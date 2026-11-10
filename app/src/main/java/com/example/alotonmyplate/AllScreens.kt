package com.example.alotonmyplate

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import androidx.navigation.compose.composable

@Serializable
object firstScreen
@Serializable
object secondScreen
@Serializable
object thirdtScreen
@Serializable
object fourthScreen


@Composable
fun allScreens(){
    val mover = rememberNavController()

    NavHost(navController = mover, startDestination = firstScreen){
      composable<firstScreen>{
          FirstScreen(mover)
          }
        composable<secondScreen>{
            SecondScreen(mover)
        }
        composable<thirdtScreen>{
            ThirdScreen(mover)
        }
        composable<fourthScreen>{
            FourthScreen(mover)
        }

        }
    }
