package com.example.alotonmyplate

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun PrioritiesScreen(){
    val arraycount = remember { mutableStateListOf<Int>() }

    Box(modifier = Modifier.combinedClickable(
        onDoubleClick = { }) {}
    )
    {

    }
}

