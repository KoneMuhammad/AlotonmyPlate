@file:JvmName("FirstScreenKt")

package com.example.alotonmyplate

import android.R.attr.maxHeight
import android.R.attr.maxWidth
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavController
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


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
                .background(color = Color(0xFFB7D5CA))
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
                ),
contentAlignment = Alignment.Center
        )
        {
            //change colour for each screen its their thing
            // but darker,
            //change colour of that bottom row its consistent
            //accross all screens
            //fix swiping
            //
            Text(
                maxLines = 10,
                fontSize = 64.sp,
                textAlign = TextAlign.Center,
                text = "",
                fontFamily = graphitiBackground,
                color = Color(0xFF09100E).copy(alpha = 0.35f),
                autoSize = TextAutoSize.StepBased(
                    minFontSize = 18.sp,
                    maxFontSize = 64.sp
                )
            )

            listOfComposables.forEach { content ->
                content()
            }
            Row(
                modifier = Modifier.fillMaxWidth(0.43f)
                    .fillMaxHeight(0.03f)
                    .offset(y = maxHeight * 0.9f)
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

val graphitiBackground = FontFamily(Font(R.font.permanentMarker_regular))
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun test(){
    Box(modifier = Modifier.fillMaxSize()
        .background(Color(0xFFB7D5CA)),
        contentAlignment = Alignment.Center){
        Text(
            maxLines = 10,
            fontSize = 64.sp,
            textAlign = TextAlign.Center,
            text = "",
            fontFamily = graphitiBackground,
            color = Color(0xFF09100E).copy(alpha = 0.35f),
            autoSize = TextAutoSize.StepBased(
                minFontSize = 18.sp,
                maxFontSize = 64.sp
            )
        )
    }
}


