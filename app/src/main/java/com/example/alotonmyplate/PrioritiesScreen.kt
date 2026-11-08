package com.example.alotonmyplate

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput


class indexHolder {
    var index: Int = 0

    @Composable
    fun PrioritiesScreen(arrayOfComposables: MutableList<indexHolder>,) {

        val theyAdded = remember { mutableStateOf(false) }

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

        if (theyAdded.value == true) {
            return arrayOfComposables[index + 1].PrioritiesScreen(arrayOfComposables)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Cyan)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                    }
                }
                .combinedClickable(
                    onDoubleClick = { incramentor() },
                    interactionSource = null,
                    indication = null,
                    onClick = { }
                )

        )
        {
            FloatingActionButton(
                containerColor = Color(0xFFC9ECED),
                shape = RoundedCornerShape(percent = 15),
                onClick = {
                    arrayOfComposables.add(indexHolder())
                    theyAdded.value = true
                }) {
            }
            listOfComposables.forEach { content ->
                content()

            }
        }
    }
}
