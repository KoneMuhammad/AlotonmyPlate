package com.example.alotonmyplate

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

// so now i make a stack idk amount and that stack
//calls gpu through dma, which has address space and simple read and write command
// inthis case i write the composable to that destination and
// and then it shows on screen
// and i read from gpu to get gesture and it stores inside me
// and i do somethinwith that data
@Composable
fun PrioritiesScreen(){
    val priortybuttonCount: MutableState<Int> = remember { mutableStateOf(0) }
    Box(modifier = Modifier.combinedClickable(
        onDoubleClick = {priortybuttonCount++ }) {}
    ){

    }
}
