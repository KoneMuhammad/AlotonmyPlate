package com.example.alotonmyplate

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

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
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Cyan)
            .combinedClickable(
            onDoubleClick = { incramentor() },
            interactionSource = null,
            indication = null,
            onClick = { }
        )
        //child is not moving inside the parent
        //almost as if upon new drawings the childs position in offset cant change
        //
    )
    {
        //box has to allow for child to resize himeslf
        //composable happen
        // parent measure child measure,
        //child draw then parent
        //child needs parents constraints before drawing
        //P-m -> C-m || C-draw P-draw
        listOfComposables.forEach { content ->
            content()

        }
    }
}
