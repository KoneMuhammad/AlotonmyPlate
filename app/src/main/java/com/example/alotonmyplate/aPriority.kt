package com.example.alotonmyplate

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

val iconColorArray: Array<Color> = arrayOf(Color(),Color(),Color())
@Composable
fun aPriorityButton(){
    val priorityName: MutableState<String> = remember { mutableStateOf("") }
    // they should all share the same keyboard state so that its only one
    val keyboardController = LocalSoftwareKeyboardController.current
    val buttonColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFD0BCFF)) }

    val index: MutableState<Int> = remember { mutableStateOf(0) }
    fun changeColor(): Color {
        if (index.value > 3){
            index.value = 0
        }
        val color = iconColorArray[index.value]
        index.value +=1;
        return color
    }

    Row(
        modifier = Modifier.fillMaxSize(0.5f)
            .fillMaxHeight(0.1f).
            background(color = ).
        clickable(true, onClick = {keyboardController?.show()}),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,

    ) {
        IconButton(onClick = {changeColor() }, colors =,  ) {
        }
        Text(text = priorityName.value)
    }

}
// performance it == asm instructions so thats the key
// read code
// focus on weakness and think asm instructions
// then open source