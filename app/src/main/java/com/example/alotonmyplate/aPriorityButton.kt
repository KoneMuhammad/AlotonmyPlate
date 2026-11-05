package com.example.alotonmyplate

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.test.imageVector.priority_high
import kotlin.math.roundToInt

val iconContainerolors: Array<Color> = arrayOf(Color(0xFF00999F),Color(0xFFB88000),Color(0xFFA61113))
val rowColors: Array<Color> = arrayOf(Color(0xFFC9ECED),Color(0xFFFEF2D8),Color(0xFFFDD7D8))

val rowBorderColors: Array<Color> = arrayOf(Color(0xFF153637),Color(0xFF4B3402),Color(0xFF490405))

val iconColors: Array<Color> = arrayOf(Color(0xFFC9ECED),Color(0xFFFEF2D8),Color(0xFFFDD7D8))

@Composable
fun aPriorityButton(){
    val priorityName: MutableState<String> = remember { mutableStateOf("this random text") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val index: MutableState<Int> = remember { mutableStateOf(0) }

    val currentIconContainerColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF00999F)) }
    val currentRowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentIconColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentRowBorderColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }

    val mostRecentPointerLocation = remember { mutableStateOf<Offset>(Offset.Zero) }

    //doesnt work
    // see if statements > see crash
    fun changeColor() {
        if (index.value > 3) {
            index.value = 0
        }
            val iconContainerColor = iconContainerolors[index.value]
            val rowColor = rowColors[index.value]
            val iconColor = iconColors[index.value]
            val rowBorderColor = rowBorderColors[index.value]

            currentIconContainerColor.value = iconContainerColor
            currentRowColor.value = rowColor
            currentIconColor.value = iconColor
            currentRowBorderColor.value = rowBorderColor
            index.value += 1
    }
    Row(
        modifier = Modifier
            .offset(
            {
                IntOffset(x = mostRecentPointerLocation.value.x.roundToInt(),
                    y = mostRecentPointerLocation.value.y.roundToInt())
            }
        )
            .pointerInput(Unit ){
                detectDragGesturesAfterLongPress { change, dragAmount ->
                    mostRecentPointerLocation.value += dragAmount
                }
                   // different types of blocking etc
                // concurrency what is it for real
                // i understand data coming in from devices, and other io,
                // dont understadn the different blockings or coroutines and how it really works
            }
                //BOOM
                // when coroutine suspends
                // it frees that thread for other work,

                //types of io ie buffer etc, and how i get the---
                //data and the flow of code after before and during
                // the io stuff
                //what it is that im getting--
                // what does that data im getting represent

                //which device am i getting that data from
                // and that data before coming in me, how is it like
                // formulated and come accross to me

                //i think these two most important other is naa
                //and what it is that im setting [thing imsettig]--

                // like underneath like the thing how it relates
                // to the program,what is it in the program
                //what it does to the program
                //is it a location, is it a offsset,
                .fillMaxWidth(0.5f)
            .fillMaxHeight(0.1f)
            .clip(RoundedCornerShape(percent = 15))
            .background(color = currentRowColor.value)
            .border(  width = 0.4.dp,
                color = currentRowBorderColor.value,
                shape = RoundedCornerShape(percent = 15))
            .clickable(true, onClick = {keyboardController?.show()}),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.fillMaxHeight(0.7f)
            .fillMaxWidth(0.17f)
            .clip(RoundedCornerShape(percent = 15))
            .background(color = currentIconContainerColor.value)
            .clickable( onClick = {changeColor() })
            ,
            contentAlignment = Alignment.Center
        )
        {
            Icon(modifier = Modifier.fillMaxHeight(0.9f),
                imageVector = priority_high,
                contentDescription = "Exlamation Point",
                tint = currentIconColor.value

            )
        }
        Text(priorityName.value, color = Color(0xFF153637))
    }
}

@Preview(showBackground = true,)
@Composable
fun previewaPriorityButton(){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(300.dp)
        .width(300.dp)
        .background(color = Color(0xFFD1E5DC)))
    {
        aPriorityButton()
    }
}
// learnings
// crashes after red
// difficult to click the icon
// sizings