package com.example.alotonmyplate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember



@Composable
fun ShowAScreen() {
    val multipleScreens = remember { mutableStateListOf<indexHolder>() }

    multipleScreens.add(indexHolder())

    multipleScreens.forEachIndexed { index, holder ->
        holder.index = index
    }

    multipleScreens[0].PrioritiesScreen(multipleScreens)

}
// that way
// conflict on state change of this
// add new one and then idk