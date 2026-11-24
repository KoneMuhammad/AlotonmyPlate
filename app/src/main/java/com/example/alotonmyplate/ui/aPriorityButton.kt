package com.example.alotonmyplate.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alotonmyplate.R
import com.example.alotonmyplate.database.Priority
import com.example.test.fingerprint
import com.example.test.imageVector.priority_high
import java.util.random.RandomGenerator
import kotlin.math.roundToInt
import kotlin.text.get

val iconContainerolors: Array<Color> = arrayOf(Color(0xFF00999F),Color(0xFFB88000),Color(0xFFA61113))
val rowColors: Array<Color> = arrayOf(Color(0xFFC9ECED),Color(0xFFFEF2D8),Color(0xFFFDD7D8))

val rowBorderColors: Array<Color> = arrayOf(Color(0xFF153637),Color(0xFF4B3402),Color(0xFF490405))

val iconColors: Array<Color> = arrayOf(Color(0xFFC9ECED),Color(0xFFFEF2D8),Color(0xFFFDD7D8))

val ShadowColors: Array<Color> = arrayOf(Color(0xFFC9ECED),Color(0xFFFEF2D8),Color(0xFFFDD7D8))

val boldonse = FontFamily(
    Font(resId = R.font.boldonse_regular)
)
@Composable
fun aPriorityButtonOne(
    indexFromParent: Int,
    priorityarray: SnapshotStateList<Priority>
) {
    val colorIndex = priorityarray[indexFromParent].colorIndex
    var isShadowElevation by remember { mutableStateOf(false) }

    val textMeasurer = rememberTextMeasurer()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val text = priorityarray[indexFromParent].name

    val currentIconContainerColor = iconContainerolors[colorIndex]
    val currentRowColor = rowColors[colorIndex]
    val currentIconColor = iconColors[colorIndex]
    val currentRowBorderColor = rowBorderColors[colorIndex]

    val currentShadowColor =
        if (colorIndex == 0) {
            Color(0xFF153637)
        } else {
            ShadowColors[colorIndex]
        }

    val fontSize = (
            (16 downTo 11).firstOrNull { size ->
                val result = textMeasurer.measure(
                    text = AnnotatedString(text),
                    style = TextStyle(
                        fontFamily = boldonse,
                        fontSize = size.sp
                    ),
                    constraints = Constraints(
                        maxWidth = 250,
                        maxHeight = 150
                    )
                )

                !result.hasVisualOverflow
            } ?: 11
            ).sp

    val shadow = if (isShadowElevation) {
        Shadow(
            radius = 10.dp,
            spread = (-5).dp,
            color = currentShadowColor,
            offset = DpOffset(0.dp, 20.dp)
        )
    } else {
        Shadow(
            radius = 0.dp,
            spread = 0.dp,
            color = currentShadowColor,
            offset = DpOffset(0.dp, 0.dp)
        )
    }

    Row(
        modifier = Modifier
            .offset {
                val priority = priorityarray[indexFromParent]

                IntOffset(
                    x = priority.x.roundToInt(),
                    y = priority.y.roundToInt()
                )
            }
            .dropShadow(
                shape = RoundedCornerShape(percent = 15),
                shadow = shadow
            )
            .fillMaxWidth(0.7f)
            .fillMaxHeight(0.055f)
            .background(currentRowColor)
            .border(
                width = 1.4.dp,
                color = currentRowBorderColor
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .clickable {
                    val current = priorityarray[indexFromParent]

                    priorityarray[indexFromParent] = current.copy(
                        colorIndex = (current.colorIndex + 1) % 3
                    )
                }
                .fillMaxWidth(0.2f)
                .background(currentIconContainerColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.fillMaxHeight(),
                imageVector = priority_high,
                contentDescription = "Exclamation Point",
                tint = currentIconColor
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        BasicTextField(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.7f),
            value = text,
            onValueChange = { userText ->
                val current = priorityarray[indexFromParent]

                priorityarray[indexFromParent] = current.copy(
                    name = userText
                )
            },
            maxLines = 3,
            singleLine = false,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            ),
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = false,
                imeAction = ImeAction.Done
            ),
            textStyle = TextStyle(
                color = Color(0xFF153637),
                fontFamily = boldonse,
                fontWeight = FontWeight.Normal,
                fontSize = fontSize,
                lineHeight = 14.sp
            )
        )

        Spacer(modifier = Modifier.width(5.dp))

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .pointerInput(priorityarray, indexFromParent) {
                    detectDragGesturesAfterLongPress(
                        onDragStart = {
                            isShadowElevation = true
                        },
                        onDragEnd = {
                            isShadowElevation = false
                        },
                        onDragCancel = {
                            isShadowElevation = false
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()

                            val current = priorityarray[indexFromParent]

                            priorityarray[indexFromParent] = current.copy(
                                x = current.x + dragAmount.x,
                                y = current.y + dragAmount.y
                            )
                        }
                    )
                }
                .fillMaxWidth()
                .background(currentIconContainerColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.fillMaxHeight(),
                imageVector = fingerprint,
                contentDescription = "Drag priority",
                tint = currentIconColor
            )
        }
    }
}
@Composable
fun aPriorityButtonTwo(
    indexFromParent: Int,
    priorityarray: SnapshotStateList<Priority>
) {
    aPriorityButtonOne(indexFromParent, priorityarray)
}

@Composable
fun aPriorityButtonThree(
    indexFromParent: Int,
    priorityarray: SnapshotStateList<Priority>
) {
    aPriorityButtonOne(indexFromParent, priorityarray)
}

@Composable
fun aPriorityButtonFour(
    indexFromParent: Int,
    priorityarray: SnapshotStateList<Priority>
) {
    aPriorityButtonOne(indexFromParent, priorityarray)
}

@Composable
fun aPriorityButtonFive(
    indexFromParent: Int,
    priorityarray: SnapshotStateList<Priority>
) {
    aPriorityButtonOne(indexFromParent, priorityarray)
}

@Composable
fun aPriorityButtonSix(
    indexFromParent: Int,
    priorityarray: SnapshotStateList<Priority>
) {
    aPriorityButtonOne(indexFromParent, priorityarray)
}