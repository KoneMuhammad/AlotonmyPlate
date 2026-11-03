package com.example.alotonmyplate

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun PrioritiesScreen() {
    val listOfComposables = remember {
        mutableStateListOf<@Composable () -> Unit>()
    }
    val incrament = remember { mutableStateOf(0) }

    fun incramentor() {
        if (incrament.value == 6) {
            return;
        }
        listOfComposables.add { aPriorityButton() }
        incrament.value++
    }
    Box(
        modifier = Modifier.combinedClickable(
        onDoubleClick = { incramentor() }
        , interactionSource = )
    )
    {
        listOfComposables.forEach { content ->
            content()

        }
    }
}


