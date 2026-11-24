package com.example.alotonmyplate.ui.screens


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alotonmyplate.database.Priority
import com.example.alotonmyplate.ui.aPriorityButtonFive
import com.example.alotonmyplate.ui.aPriorityButtonFour
import com.example.alotonmyplate.ui.aPriorityButtonOne
import com.example.alotonmyplate.ui.aPriorityButtonSix
import com.example.alotonmyplate.ui.aPriorityButtonThree
import com.example.alotonmyplate.ui.aPriorityButtonTwo
import com.example.alotonmyplate.ui.boldonse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun FourthScreen(hasUserClicked: MutableState<Boolean>, navigator: NavController, prioritiesForUi: SnapshotStateList<Priority>) {

    val scope = rememberCoroutineScope()

    if(prioritiesForUi.size != 0 && prioritiesForUi.size != 1) {
        prioritiesForUi.sortBy { it.count }
    }

    val incrament = remember { mutableIntStateOf(0) }
    val isendOScreen = remember { mutableStateOf(false) }

    val listOfComposables = remember {
        mutableStateListOf<@Composable (index: Int) -> Unit>() }

    fun incramentor() {
        if (incrament.intValue <= 6) {
            incrament.intValue++
            when (incrament.intValue) {
                1 -> listOfComposables.add { index ->
                    if(prioritiesForUi.isEmpty()){
                        prioritiesForUi.add(Priority(name = "", location = 1, count = 1, x = 0f, y = 0f))
                        Log.i("OnCLick", "${prioritiesForUi.size}")
                    }
                    aPriorityButtonOne(index, prioritiesForUi)
                }

                2 -> listOfComposables.add { index ->
                    if(prioritiesForUi.size == 1){
                        prioritiesForUi.add(Priority(name = "", location = 4, count = 2, x = 0f, y = 0f))
                    }
                    aPriorityButtonTwo(index, prioritiesForUi)
                }

                3 -> listOfComposables.add { index ->
                    if(prioritiesForUi.size == 2){
                        prioritiesForUi.add(Priority(name = "", location = 4, count = 3, x = 0f, y = 0f))
                    }
                    aPriorityButtonThree(index, prioritiesForUi)
                }

                4 -> listOfComposables.add { index ->
                    if(prioritiesForUi.size == 3){
                        prioritiesForUi.add(Priority(name = "", location = 4, count = 4, x = 0f, y = 0f))
                    }
                    aPriorityButtonFour(index, prioritiesForUi)
                }

                5 -> listOfComposables.add { index ->
                    if(prioritiesForUi.size == 4){
                        prioritiesForUi.add(Priority(name = "", location = 4, count = 5, x = 0f, y = 0f))
                    }
                    aPriorityButtonFive(index, prioritiesForUi)
                }

                6 -> listOfComposables.add { index ->
                    if(prioritiesForUi.size == 5){
                        prioritiesForUi.add(Priority(name = "", location = 4, count = 6, x = 0f, y = 0f))
                    }
                    aPriorityButtonSix(index, prioritiesForUi)
                }
            }
        }
    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFB6D8D4))
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.25f)
                .fillMaxHeight(0.06f)
                .offset(y = maxHeight * 0.3f)
                .clip(shape = RoundedCornerShape(percent = 15))
                .clickable(enabled = !hasUserClicked.value,
                    onClick = {
                        scope.launch {
                            hasUserClicked.value = true
                            delay(5.seconds)
                            hasUserClicked.value = false
                        }
                    })
                .background(color =
                    if(hasUserClicked.value == false){
                        Color(0xFF2EB521)
                    }else {
                        Color.Gray
                    }
                )
        ) {
            Text("Save",
                style = TextStyle
                    (fontFamily = boldonse,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,

                    ))
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
                            if (index == 0) Color(0xFF24881B) else Color.Black
                        )
                )
            }
        }
    }
}