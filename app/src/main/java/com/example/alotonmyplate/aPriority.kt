package com.example.alotonmyplate

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

//populate colors
val iconContainerolors: Array<Color> = arrayOf(Color(0xFF00999F),Color(0xFFB88000),Color(0xFFA61113))
val rowColors: Array<Color> = arrayOf(Color(0xFFC9ECED),Color(0xFFFEF2D8),Color(0xFFFDD7D8))

@Composable
fun aPriorityButton(){
    val priorityName: MutableState<String> = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val index: MutableState<Int> = remember { mutableStateOf(0) }

    val currentIconContainerColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF00999F)) }
    val currentRowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }

    fun changeColor() {
        if (index.value > 3){
            index.value = 0
        }
        val iconColor = iconContainerolors[index.value]
        val rowColor = rowColors[index.value]

        currentIconContainerColor.value = iconColor
        currentRowColor.value = rowColor
        index.value +=1;

    }

    Row(
        modifier = Modifier.fillMaxSize(0.5f)
            .fillMaxHeight(0.1f).
            background(color = currentRowColor.value).
        clickable(true, onClick = {keyboardController?.show()}),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,

    ) {
        IconButton(onClick = {changeColor() },
            colors = IconButtonDefaults.iconButtonColors(
            containerColor = currentIconContainerColor.value,
                contentColor = ,))
        {
            Icon(imageVector =
            )
        }
    }
}

// contribute to fluffy chat
// look for other open source repositories and make a small