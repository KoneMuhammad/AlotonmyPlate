@file:JvmName("FirstScreenKt")

package com.example.alotonmyplate.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.alotonmyplate.ui.aPriorityButtonFive
import com.example.alotonmyplate.ui.aPriorityButtonFour
import com.example.alotonmyplate.ui.aPriorityButtonOne
import com.example.alotonmyplate.ui.aPriorityButtonSix
import com.example.alotonmyplate.ui.aPriorityButtonThree
import com.example.alotonmyplate.ui.aPriorityButtonTwo
import com.example.alotonmyplate.database.Priority
import com.example.alotonmyplate.ui.boldonse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.time.Duration.Companion.seconds
inline fun <reified T : Any> NavController.navigateKeepingState(
    destination: T
) {
    navigate(destination) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }

        launchSingleTop = true
        restoreState = true
    }
}
@Composable
fun FirstScreen(
    hasUserClicked: MutableState<Boolean>,
    navigator: NavController,
    prioritiesForUi: SnapshotStateList<Priority>
) {
    val scope = rememberCoroutineScope()

    fun addPriority() {
        if (prioritiesForUi.size >= 6) return

        val nextCount =
            (prioritiesForUi.maxOfOrNull { it.count } ?: 0) + 1

        prioritiesForUi.add(
            Priority(
                name = "",
                location = 1,
                count = nextCount,
                x = 0f,
                y = 0f
            )
        )
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB7D5CA))
            .pointerInput(navigator) {
                val minSwipeDistance = 80.dp.toPx()
                val horizontalDominance = 1.5f

                var totalX = 0f
                var totalY = 0f

                detectDragGestures(
                    onDragStart = {
                        totalX = 0f
                        totalY = 0f
                    },
                    onDrag = { _, dragAmount ->
                        totalX += dragAmount.x
                        totalY += dragAmount.y
                    },
                    onDragEnd = {
                        val isHorizontalSwipe =
                            abs(totalX) >= minSwipeDistance &&
                                    abs(totalX) >
                                    abs(totalY) * horizontalDominance

                        if (isHorizontalSwipe) {
                            if (totalX > 0f) {
                                navigator.navigateKeepingState(fourthScreen)
                            } else {
                                navigator.navigateKeepingState(secondScreen)
                            }
                        }

                        totalX = 0f
                        totalY = 0f
                    },
                    onDragCancel = {
                        totalX = 0f
                        totalY = 0f
                    }
                )
            }
            .combinedClickable(
                onDoubleClick = { addPriority() },
                interactionSource = null,
                indication = null,
                onClick = {}
            ),
        contentAlignment = Alignment.Center
    ) {
        // Sort the display order without modifying the list
        // during composition. Each button gets the actual list index.
        val orderedIndices = prioritiesForUi.indices
            .sortedBy { prioritiesForUi[it].count }
            .take(6)

        orderedIndices.forEachIndexed { buttonNumber, listIndex ->
            when (buttonNumber) {
                0 -> aPriorityButtonOne(listIndex, prioritiesForUi)
                1 -> aPriorityButtonTwo(listIndex, prioritiesForUi)
                2 -> aPriorityButtonThree(listIndex, prioritiesForUi)
                3 -> aPriorityButtonFour(listIndex, prioritiesForUi)
                4 -> aPriorityButtonFive(listIndex, prioritiesForUi)
                5 -> aPriorityButtonSix(listIndex, prioritiesForUi)
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.25f)
                .fillMaxHeight(0.06f)
                .offset(y = maxHeight * 0.3f)
                .clip(RoundedCornerShape(percent = 15))
                .clickable(
                    enabled = !hasUserClicked.value,
                    onClick = {
                        scope.launch {
                            hasUserClicked.value = true
                            delay(5_000L)
                            hasUserClicked.value = false
                        }
                    }
                )
                .background(
                    if (!hasUserClicked.value) {
                        Color(0xFF2EB521)
                    } else {
                        Color.Gray
                    }
                )
        ) {
            Text(
                text = "Save",
                style = TextStyle(
                    fontFamily = boldonse,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(0.43f)
                .fillMaxHeight(0.03f)
                .offset(y = maxHeight * 0.4f)
                .clip(RoundedCornerShape(percent = 50))
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(4) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .fillMaxWidth(0.1f)
                        .clip(CircleShape)
                        .background(
                            if (index == 0) {
                                Color(0xFF2EB521)
                            } else {
                                Color.Black
                            }
                        )
                )
            }
        }
    }
}