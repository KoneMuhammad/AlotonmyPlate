package com.example.alotonmyplate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

@Composable
fun ComposableScreens(){
    val arrayOfComposabels = remember { mutableStateListOf<@Composable () -> Unit >() }

}


