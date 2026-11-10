package com.example.alotonmyplate


import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.navigation.NavController
import com.example.test.add

@Composable
fun ThirdScreen(navigator: NavController) {

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
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if(dragAmount < 0 ){
                        navigator.navigate(fourthScreen)
                    }
                    else{
                        navigator.navigate(secondScreen)
                    }
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
            modifier = Modifier.fillMaxHeight(0.1f)
                .fillMaxWidth(0.2f)
                .offset(x = maxWidth * 0.8f, y = maxHeight * 0.1f),
            containerColor = Color(0xFFC9ECED),
            shape = RoundedCornerShape(percent = 15),
            onClick = {
                navigator.navigate(fourthScreen)
            }) {
            Icon(
                imageVector = add,
                contentDescription = "exclamation Point",

                )
        }
        listOfComposables.forEach { content ->
            content()

        }
    }
}