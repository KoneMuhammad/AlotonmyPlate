package com.example.alotonmyplate

import android.R
import android.health.connect.datatypes.units.Percentage
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
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

class indexHolder {
    var index: Int = 0

    @Composable
    fun PrioritiesScreen(arrayOfComposables: SnapshotStateList<indexHolder>) {

        val listOfComposables = remember {
            mutableStateListOf<@Composable () -> Unit>()
        }
        val incrament = remember { mutableStateOf(0) }
        val isElementAdded = remember { mutableStateOf(false) }

        fun incramentor() {
            if (incrament.value == 6) {
                return;
            }
            listOfComposables.add { aPriorityButton() }
            incrament.value++

        }
        if (isElementAdded.value == true){
            isElementAdded.value = false
           return  arrayOfComposables[index + 1].PrioritiesScreen(arrayOfComposables)
        }

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
            Text(
                text = "Screens = ${arrayOfComposables.size}",
                modifier = Modifier.offset(x = maxWidth * 0.8f, y = maxHeight * 0.05f)
            )

            FloatingActionButton(
                modifier = Modifier.fillMaxHeight(0.1f)
                    .fillMaxWidth(0.2f)
                    .offset(x = maxWidth * 0.8f, y = maxHeight * 0.1f),
                containerColor = Color(0xFFC9ECED),
                shape = RoundedCornerShape(percent = 15),
                onClick = {
                    if(arrayOfComposables.size <= 3){
                  arrayOfComposables.add(indexHolder())
                    isElementAdded.value = true}
                    else{

                    }
                }) {
                Icon(
                    imageVector = add,
                    contentDescription = "Exlamation Point",

                    )
            }
             /**   if(){
                    Card(modifier = Modifier.offset(x = maxWidth * 0.5f, y = maxHeight * 0.5f)
                        .fillMaxWidth(0.35f)
                        .fillMaxHeight(0.2f)
                        .background(shape = RoundedCornerShape(percent = 15),
                            color = Color(0xFFB88000))

                    ) {
                        Text("No you cant have more than 4 Screens")}
              */
            listOfComposables.forEach { content ->
                content()

            }
        }
    }
}
