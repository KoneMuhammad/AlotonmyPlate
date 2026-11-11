@file:JvmName("FirstScreenKt")

package com.example.alotonmyplate

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.test.add
import androidx.compose.foundation.shape.CircleShape


@Composable
    fun FirstScreen(navigator: NavController) {
        val isendOScreen = remember { mutableStateOf(false) }

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
                .background(color = Color.Cyan)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                        if(dragAmount < 0 ){
                            navigator.navigate(secondScreen)
                        }
                        else{
                            isendOScreen.value = true
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
            listOfComposables.forEach { content ->
                content()
            }
            Row(
                modifier = Modifier.fillMaxWidth(0.43f)
                    .fillMaxHeight(0.03f)
                    .offset(x = maxWidth * 0.3f, y = maxHeight * 0.9f)
                    .clip(shape = RoundedCornerShape(percent = 50))
                    .background(color = Color.LightGray),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically

            ) {
                repeat(4) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .fillMaxWidth(0.1f)
                            .clip(shape = CircleShape)
                            .background(
                                if (index == 0) Color.Green else Color.Black
                            )
                    )
                }
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun previewComposable() {
    BoxWithConstraints(
        modifier = Modifier.size(200.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.43f)
                .fillMaxHeight(0.03f)
                .offset(x = maxWidth * 0.3f, y = maxHeight * 0.9f)
                .clip(shape = RoundedCornerShape(percent = 50))
                .background(color = Color.LightGray),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically

        ) {
            repeat(4) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .fillMaxWidth(0.1f)
                        .clip(shape = CircleShape)
                        .background(
                            if (index == 0) Color.Green else Color.Black
                        )
                )
            }
        }
    }
}