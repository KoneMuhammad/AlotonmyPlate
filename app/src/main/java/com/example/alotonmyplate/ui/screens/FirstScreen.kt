@file:JvmName("FirstScreenKt")

package com.example.alotonmyplate.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alotonmyplate.ui.aPriorityButtonFive
import com.example.alotonmyplate.ui.aPriorityButtonFour
import com.example.alotonmyplate.ui.aPriorityButtonOne
import com.example.alotonmyplate.ui.aPriorityButtonSix
import com.example.alotonmyplate.ui.aPriorityButtonThree
import com.example.alotonmyplate.ui.aPriorityButtonTwo
import com.example.alotonmyplate.database.Priority
@Composable
    fun FirstScreen(isSave: MutableState<Boolean>, navigator: NavController, prioritiesForUi: SnapshotStateList<Priority>) {

        if(prioritiesForUi.size != 0 && prioritiesForUi.size != 1) {
            prioritiesForUi.sortBy { it.count }
        }

    val incrament = remember { mutableStateOf(0) }
    val isendOScreen = remember { mutableStateOf(false) }

    val listOfComposables = remember {
        mutableStateListOf<@Composable (index: Int) -> Unit>() }

        fun incramentor() {
            if (incrament.value <= 6) {
                incrament.value++
                when (incrament.value) {
                    1 -> listOfComposables.add { index ->
                        if(prioritiesForUi.size == 0){
                            prioritiesForUi.add(Priority(name = "", location = 1, count = 1, x = 0f, y = 0f))
                        }
                        aPriorityButtonOne(index, prioritiesForUi)
                    }

                    2 -> listOfComposables.add { index ->
                        if(prioritiesForUi.size == 1){
                            prioritiesForUi.add(Priority(name = "", location = 1, count = 2, x = 0f, y = 0f))
                        }
                        aPriorityButtonTwo(index, prioritiesForUi)
                    }

                    3 -> listOfComposables.add { index ->
                        if(prioritiesForUi.size == 2){
                            prioritiesForUi.add(Priority(name = "", location = 1, count = 3, x = 0f, y = 0f))
                        }
                        aPriorityButtonThree(index, prioritiesForUi)
                    }

                    4 -> listOfComposables.add { index ->
                        if(prioritiesForUi.size == 3){
                            prioritiesForUi.add(Priority(name = "", location = 1, count = 4, x = 0f, y = 0f))
                        }
                        aPriorityButtonFour(index, prioritiesForUi)
                    }

                    5 -> listOfComposables.add { index ->
                        if(prioritiesForUi.size == 4){
                            prioritiesForUi.add(Priority(name = "", location = 1, count = 5, x = 0f, y = 0f))
                        }
                        aPriorityButtonFive(index, prioritiesForUi)
                    }

                    6 -> listOfComposables.add { index ->
                        if(prioritiesForUi.size == 5){
                            prioritiesForUi.add(Priority(name = "", location = 1, count = 6, x = 0f, y = 0f))
                        }
                        aPriorityButtonSix(index, prioritiesForUi)
                    }
                }
            }
        }
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFB7D5CA))
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                        if (dragAmount < 0) {

                            navigator.navigate(secondScreen)
                        } else {
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
            listOfComposables.forEachIndexed { index, content ->
                content(index)
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .fillMaxHeight(0.09f)
                    .offset(y = maxHeight * 0.2f)
                    .clip(shape = RoundedCornerShape(percent = 15))
                    .clickable(onClick = {
                        isSave.value = true
                        isSave.value = false
                    })
                    .background(color =
                    if(isSave.value ==  false){
                        Color.Black
                    }else {
                        Color.Green
                    }
                    )
            ) {

                Text("Save")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.43f)
                    .fillMaxHeight(0.03f)
                    .offset(y = maxHeight * 0.4f)
                    .clip(shape = RoundedCornerShape(percent = 50))
                    .background(color = Color.Transparent),
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
                                if (index == 0) Color(0xFF2EB521) else Color.Black
                            )
                    )
                }
            }
        }
    }

@Preview
@Composable
fun checkButton(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFB7D5CA))
            .combinedClickable(
                interactionSource = null,
                indication = null,
                onClick = { }
            ),
        contentAlignment = Alignment.Center
    ){
        Button(modifier = Modifier
            .size(80.dp)
            .dropShadow(shape = CircleShape,
                shadow = Shadow(
                radius = 7.dp,
                offset = DpOffset(x = 0.dp,y= 10.dp),
                color = Color(0xFF98C3B3),
            )),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00999F)
            ),
            onClick = { }
        ) {
            Text(modifier = Modifier.fillMaxWidth(),
                text =  "rename",
                textAlign = TextAlign.Center,
                fontSize = 17.sp
                )
        }
        }
}

































//switch from that to this