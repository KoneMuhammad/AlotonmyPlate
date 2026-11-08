package com.example.alotonmyplate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun ShowAScreen() {
    val multipleScreens = remember { mutableStateListOf<@Composable () -> Unit>() }

    multipleScreens.add {
        PrioritiesScreen(multipleScreens)
    }
    multipleScreens.forEachIndexed { index, function ->
        PrioritiesScreen(multipleScreens, index)
    }
    multipleScreens[0].invoke()
}