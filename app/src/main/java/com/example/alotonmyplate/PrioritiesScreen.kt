package com.example.alotonmyplate

import android.health.connect.datatypes.units.Percentage
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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


class indexHolder {
    var index: Int = 0

    @Composable
    fun PrioritiesScreen(arrayOfComposables: SnapshotStateList<indexHolder>,) {

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
                modifier = Modifier.fillMaxHeight(0.2f)
                    .fillMaxWidth(0.2f)
                    .offset(x = 30.dp, y = 30.dp),
                containerColor = Color(0xFFC9ECED),
                shape = RoundedCornerShape(percent = 15),
                onClick = {
                    arrayOfComposables.add(indexHolder())
                    theyAdded.value = true
                }) {
                Icon(
                    imageVector = add,
                    contentDescription = "Exlamation Point",

                )
            }
            listOfComposables.forEach { content ->
                content()

            }
        }
    }
}
//weakness thinkig same way i think with ram and whilest typing
// and i mess myself up taht way, so do si
// when typing think about ram and stuff
// cause you coiudla iplem

