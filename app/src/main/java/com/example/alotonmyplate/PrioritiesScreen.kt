package com.example.alotonmyplate

import android.R
import android.health.connect.datatypes.units.Percentage
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.test.add
import com.example.test.imageVector.priority_high

// place a max of priorties screen
// add animation on navigation
// add colour picker for each screen let them be pastel like colours
// add the ability to swipte right or left for screens

class indexHolder {
    var index: Int = 0

    @Composable
    fun PrioritiesScreen(arrayOfComposables: SnapshotStateList<indexHolder>,) {
        val visible = remember { mutableStateOf(true) }

        val theyAdded = remember { mutableStateOf(false) }

        val listOfComposables = remember {
            mutableStateListOf<@Composable () -> Unit>()
        }
        val raiseMaxScreensFlag = remember { mutableStateOf(false) }
        val incrament = remember { mutableStateOf(0) }

        fun incramentor() {
            if (incrament.value == 6) {
                return;
            }
            listOfComposables.add { aPriorityButton() }
            incrament.value++
        }

        if (theyAdded.value == true) {
            visible.value = false
            return arrayOfComposables[index + 1].PrioritiesScreen(arrayOfComposables)

        }
        AnimatedVisibility(
            visible = visible.value,
            exit = slideOutHorizontally() + fadeOut()
        ) {
        BoxWithConstraints(
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
                modifier = Modifier.fillMaxHeight(0.2f)
                    .fillMaxWidth(0.2f)
                    .offset(x = 30.dp, y = 30.dp),
                containerColor = Color(0xFFC9ECED),
                shape = RoundedCornerShape(percent = 15),
                onClick = {
                    if(arrayOfComposables.size >= 4) {
                        raiseMaxScreensFlag.value = true
                    }
                    else {
                        arrayOfComposables.add(indexHolder())
                        theyAdded.value = true
                    }
                }) {
                Icon(
                    imageVector = add,
                    contentDescription = "Exlamation Point",

                )
                if(raiseMaxScreensFlag.value == true){
                    Card(modifier = Modifier.offset(x = maxWidth * 0.5f, y = maxHeight * 0.5f)
                        .fillMaxWidth(0.35f)
                        .fillMaxHeight(0.2f)
                        .background(shape = RoundedCornerShape(percent = 15),
                            color = Color(0xFFB88000))

                    ) {
                        Text("No you cant have more than 4 Screens")
                    }
                }
            }
            listOfComposables.forEach { content ->
                content()

            }
        }
    }
}
}
