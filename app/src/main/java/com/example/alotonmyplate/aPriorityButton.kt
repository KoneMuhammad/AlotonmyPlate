package com.example.alotonmyplate

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
import androidx.compose.ui.geometry.Offset
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
import com.example.test.fingerprint
import com.example.test.imageVector.priority_high
import kotlin.math.roundToInt

val iconContainerolors: Array<Color> = arrayOf(Color(0xFF00999F),Color(0xFFB88000),Color(0xFFA61113))
val rowColors: Array<Color> = arrayOf(Color(0xFFC9ECED),Color(0xFFFEF2D8),Color(0xFFFDD7D8))

val rowBorderColors: Array<Color> = arrayOf(Color(0xFF153637),Color(0xFF4B3402),Color(0xFF490405))

val iconColors: Array<Color> = arrayOf(Color(0xFFC9ECED),Color(0xFFFEF2D8),Color(0xFFFDD7D8))

val ShadowColors: Array<Color> = arrayOf(Color(0xFFC9ECED),Color(0xFFFEF2D8),Color(0xFFFDD7D8))

val boldonse = FontFamily(
    Font(resId = R.font.boldonse_regular)
)
@Composable
fun aPriorityButtonOne(indexFromParent: Int, priorityPaent: SnapshotStateList<Priority>){
    val priorityElement = priorityPaent[indexFromParent]

    val index: MutableState<Int> = remember { mutableStateOf(0) }
    val focusRequest = remember { FocusRequester() }
    val currentIconContainerColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF00999F)) }
    val currentRowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentIconColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentRowBorderColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }
    val currentShadowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }
    var text by rememberSaveable { mutableStateOf("Priority") }

    val isShadowElevation = remember { mutableStateOf(false) }
    val textMeasurer = rememberTextMeasurer()
    var fontSize by remember { mutableStateOf(16.sp) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    fun addShadow(): Shadow{
        if(isShadowElevation.value == true){
            return Shadow(
                radius = 10.dp,
                spread = -5.dp,
                color = currentShadowColor.value,
                offset = DpOffset(0.dp, 20.dp)
            )
        } else{
            return Shadow(
                radius = 0.dp,
                spread = 0.dp,
                color = currentShadowColor.value,
                offset = DpOffset(0.dp, 0.dp)
            )
        }
    }

    fun changeColor() {
        if (index.value > 2) {
            index.value = 0
        }
        currentIconContainerColor.value = iconContainerolors[index.value]
        currentRowColor.value = rowColors[index.value]
        currentIconColor.value = iconColors[index.value]
        currentRowBorderColor.value = rowBorderColors[index.value]
        currentShadowColor.value = ShadowColors[index.value]

        index.value += 1
    }
    Row(
        modifier = Modifier
            .offset(
            {
                IntOffset(x = priorityElement?.x?.roundToInt() ?: 0,
                    y = priorityElement?.y?.roundToInt() ?: 0)
            }
        )
            .dropShadow(
                shape = RoundedCornerShape(percent = 15),
                shadow = addShadow()
            )
                .fillMaxWidth(0.7f)
            .fillMaxHeight(0.055f)
            .background(color = currentRowColor.value)
            .border(  width = 1.4.dp,
                color = currentRowBorderColor.value,
                )
            .clickable(true, onClick = {keyboardController?.show()}),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.fillMaxHeight(1f)
            .clickable( onClick = {changeColor() })
            .fillMaxWidth(0.2f)
            .background(color = currentIconContainerColor.value),
            contentAlignment = Alignment.Center
        )
        {
            Icon(modifier = Modifier.fillMaxHeight(),
                imageVector = priority_high,
                contentDescription = "Exlamation Point",
                tint = currentIconColor.value
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        BasicTextField(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.7f)
                .focusRequester(focusRequest)
                .focusable()
                .clickable {
                    focusRequest.requestFocus()
                },
            maxLines = 3,
            singleLine = false,
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
                keyboardController?.hide()
            },
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = false,
                imeAction = ImeAction.Done
            ),
            value = text,
            onValueChange = { userText ->
                val newSize = (16 downTo 11).firstOrNull { size ->
                    val result = textMeasurer.measure(
                        text = AnnotatedString(userText),
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

                fontSize = newSize.sp
                text = userText
                priorityElement?.name = userText
            },
            textStyle = TextStyle(
                color = Color(0xFF153637),
                fontFamily = boldonse,
                fontWeight = FontWeight.Normal,
                fontSize = fontSize,
                lineHeight = 14.sp
            )
        )
        /
        Spacer(modifier = Modifier.width(5.dp))
        Box(modifier = Modifier.fillMaxHeight(1f)
            .pointerInput(Unit ){
                detectDragGesturesAfterLongPress(
                    onDragStart = { isShadowElevation.value = true; },
                    onDragEnd = {isShadowElevation.value = false}
                ) {
                        change, dragAmount ->
                    priorityElement?.x = (priorityElement.x) + dragAmount.x
                    priorityElement?.y = (priorityElement.y) + dragAmount.y
                }
            }
            .fillMaxWidth(1f)
            .background(color = currentIconContainerColor.value),
            contentAlignment = Alignment.Center
        )
        {
            Icon(modifier = Modifier.fillMaxHeight(),
                imageVector = fingerprint,
                contentDescription = "Thumb Move",
                tint = currentIconColor.value
            )
        }
    }
}

@Composable
fun aPriorityButtonTwo(indexFromParent: Int, priorityPaent: SnapshotStateList<Priority>){
    val priorityElement = priorityPaent[indexFromParent]

    val index: MutableState<Int> = remember { mutableStateOf(0) }
    val focusRequest = remember { FocusRequester() }
    val currentIconContainerColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF00999F)) }
    val currentRowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentIconColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentRowBorderColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }
    val currentShadowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }
    var text by rememberSaveable { mutableStateOf("Priority") }

    val isShadowElevation = remember { mutableStateOf(false) }
    val textMeasurer = rememberTextMeasurer()
    var fontSize by remember { mutableStateOf(16.sp) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    fun addShadow(): Shadow{
        if(isShadowElevation.value == true){
            return Shadow(
                radius = 10.dp,
                spread = -5.dp,
                color = currentShadowColor.value,
                offset = DpOffset(0.dp, 20.dp)
            )
        } else{
            return Shadow(
                radius = 0.dp,
                spread = 0.dp,
                color = currentShadowColor.value,
                offset = DpOffset(0.dp, 0.dp)
            )
        }
    }

    fun changeColor() {
        if (index.value > 2) {
            index.value = 0
        }
        currentIconContainerColor.value = iconContainerolors[index.value]
        currentRowColor.value = rowColors[index.value]
        currentIconColor.value = iconColors[index.value]
        currentRowBorderColor.value = rowBorderColors[index.value]
        currentShadowColor.value = ShadowColors[index.value]

        index.value += 1
    }
    Row(
        modifier = Modifier
            .offset(
                {
                    IntOffset(x = priorityElement?.x?.roundToInt() ?: 0,
                        y = priorityElement?.y?.roundToInt() ?: 0)
                }
            )
            .dropShadow(
                shape = RoundedCornerShape(percent = 15),
                shadow = addShadow()
            )
            .fillMaxWidth(0.7f)
            .fillMaxHeight(0.055f)
            .background(color = currentRowColor.value)
            .border(  width = 1.4.dp,
                color = currentRowBorderColor.value,
            )
            .clickable(true, onClick = {keyboardController?.show()}),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.fillMaxHeight(1f)
            .clickable( onClick = {changeColor() })
            .fillMaxWidth(0.2f)
            .background(color = currentIconContainerColor.value),
            contentAlignment = Alignment.Center
        )
        {
            Icon(modifier = Modifier.fillMaxHeight(),
                imageVector = priority_high,
                contentDescription = "Exlamation Point",
                tint = currentIconColor.value
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        BasicTextField(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.7f)
                .focusRequester(focusRequest)
                .focusable()
                .clickable {
                    focusRequest.requestFocus()
                },
            maxLines = 3,
            singleLine = false,
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
                keyboardController?.hide()
            },
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = false,
                imeAction = ImeAction.Done
            ),
            value = text,
            onValueChange = { userText ->
                val newSize = (16 downTo 11).firstOrNull { size ->
                    val result = textMeasurer.measure(
                        text = AnnotatedString(userText),
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

                fontSize = newSize.sp
                text = userText
                priorityElement?.name = userText
            },
            textStyle = TextStyle(
                color = Color(0xFF153637),
                fontFamily = boldonse,
                fontWeight = FontWeight.Normal,
                fontSize = fontSize,
                lineHeight = 14.sp
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Box(modifier = Modifier.fillMaxHeight(1f)
            .pointerInput(Unit ){
                detectDragGesturesAfterLongPress(
                    onDragStart = { isShadowElevation.value = true; },
                    onDragEnd = {isShadowElevation.value = false}
                ) {
                        change, dragAmount ->
                    priorityElement?.x += dragAmount.x
                    priorityElement?.y += dragAmount.y

                }
            }
            .fillMaxWidth(1f)
            .background(color = currentIconContainerColor.value),
            contentAlignment = Alignment.Center
        )
        {
            Icon(modifier = Modifier.fillMaxHeight(),
                imageVector = fingerprint,
                contentDescription = "Thumb Move",
                tint = currentIconColor.value
            )
        }
    }
}

@Composable
fun aPriorityButtonThree(indexFromParent: Int, priorityPaent: SnapshotStateList<Priority>){
    val priorityElement = priorityPaent[indexFromParent]

    val index: MutableState<Int> = remember { mutableStateOf(0) }
    val focusRequest = remember { FocusRequester() }
    val currentIconContainerColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF00999F)) }
    val currentRowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentIconColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentRowBorderColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }
    val currentShadowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }
    var text by rememberSaveable { mutableStateOf("Priority") }

    val isShadowElevation = remember { mutableStateOf(false) }
    val textMeasurer = rememberTextMeasurer()
    var fontSize by remember { mutableStateOf(16.sp) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    fun addShadow(): Shadow{
        if(isShadowElevation.value == true){
            return Shadow(
                radius = 10.dp,
                spread = -5.dp,
                color = currentShadowColor.value,
                offset = DpOffset(0.dp, 20.dp)
            )
        } else{
            return Shadow(
                radius = 0.dp,
                spread = 0.dp,
                color = currentShadowColor.value,
                offset = DpOffset(0.dp, 0.dp)
            )
        }
    }

    fun changeColor() {
        if (index.value > 2) {
            index.value = 0
        }
        currentIconContainerColor.value = iconContainerolors[index.value]
        currentRowColor.value = rowColors[index.value]
        currentIconColor.value = iconColors[index.value]
        currentRowBorderColor.value = rowBorderColors[index.value]
        currentShadowColor.value = ShadowColors[index.value]

        index.value += 1
    }
    Row(
        modifier = Modifier
            .offset(
                {
                    IntOffset(x = priorityElement?.x?.roundToInt() ?: 0,
                        y = priorityElement?.y?.roundToInt() ?: 0)
                }
            )
            .dropShadow(
                shape = RoundedCornerShape(percent = 15),
                shadow = addShadow()
            )
            .fillMaxWidth(0.7f)
            .fillMaxHeight(0.055f)
            .background(color = currentRowColor.value)
            .border(  width = 1.4.dp,
                color = currentRowBorderColor.value,
            )
            .clickable(true, onClick = {keyboardController?.show()}),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.fillMaxHeight(1f)
            .clickable( onClick = {changeColor() })
            .fillMaxWidth(0.2f)
            .background(color = currentIconContainerColor.value),
            contentAlignment = Alignment.Center
        )
        {
            Icon(modifier = Modifier.fillMaxHeight(),
                imageVector = priority_high,
                contentDescription = "Exlamation Point",
                tint = currentIconColor.value
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        BasicTextField(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.7f)
                .focusRequester(focusRequest)
                .focusable()
                .clickable {
                    focusRequest.requestFocus()
                },
            maxLines = 3,
            singleLine = false,
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
                keyboardController?.hide()
            },
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = false,
                imeAction = ImeAction.Done
            ),
            value = text,
            onValueChange = { userText ->
                val newSize = (16 downTo 11).firstOrNull { size ->
                    val result = textMeasurer.measure(
                        text = AnnotatedString(userText),
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

                fontSize = newSize.sp
                text = userText
                priorityElement?.name = userText
            },
            textStyle = TextStyle(
                color = Color(0xFF153637),
                fontFamily = boldonse,
                fontWeight = FontWeight.Normal,
                fontSize = fontSize,
                lineHeight = 14.sp
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Box(modifier = Modifier.fillMaxHeight(1f)
            .pointerInput(Unit ){
                detectDragGesturesAfterLongPress(
                    onDragStart = { isShadowElevation.value = true; },
                    onDragEnd = {isShadowElevation.value = false}
                ) {
                        change, dragAmount ->
                    priorityElement?.x += dragAmount.x
                    priorityElement?.y += dragAmount.y

                }
            }
            .fillMaxWidth(1f)
            .background(color = currentIconContainerColor.value),
            contentAlignment = Alignment.Center
        )
        {
            Icon(modifier = Modifier.fillMaxHeight(),
                imageVector = fingerprint,
                contentDescription = "Thumb Move",
                tint = currentIconColor.value
            )
        }
    }
}

@Composable
fun aPriorityButtonFour(indexFromParent: Int, priorityPaent: SnapshotStateList<Priority>){
    val priorityElement = priorityPaent[indexFromParent]

    val index: MutableState<Int> = remember { mutableStateOf(0) }
    val focusRequest = remember { FocusRequester() }
    val currentIconContainerColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF00999F)) }
    val currentRowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentIconColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentRowBorderColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }
    val currentShadowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }
    var text by rememberSaveable { mutableStateOf("Priority") }

    val isShadowElevation = remember { mutableStateOf(false) }
    val textMeasurer = rememberTextMeasurer()
    var fontSize by remember { mutableStateOf(16.sp) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    fun addShadow(): Shadow{
        if(isShadowElevation.value == true){
            return Shadow(
                radius = 10.dp,
                spread = -5.dp,
                color = currentShadowColor.value,
                offset = DpOffset(0.dp, 20.dp)
            )
        } else{
            return Shadow(
                radius = 0.dp,
                spread = 0.dp,
                color = currentShadowColor.value,
                offset = DpOffset(0.dp, 0.dp)
            )
        }
    }

    fun changeColor() {
        if (index.value > 2) {
            index.value = 0
        }
        currentIconContainerColor.value = iconContainerolors[index.value]
        currentRowColor.value = rowColors[index.value]
        currentIconColor.value = iconColors[index.value]
        currentRowBorderColor.value = rowBorderColors[index.value]
        currentShadowColor.value = ShadowColors[index.value]

        index.value += 1
    }
    Row(
        modifier = Modifier
            .offset(
                {
                    IntOffset(x = priorityElement?.x?.roundToInt() ?: 0,
                        y = priorityElement?.y?.roundToInt() ?: 0)
                }
            )
            .dropShadow(
                shape = RoundedCornerShape(percent = 15),
                shadow = addShadow()
            )
            .fillMaxWidth(0.7f)
            .fillMaxHeight(0.055f)
            .background(color = currentRowColor.value)
            .border(  width = 1.4.dp,
                color = currentRowBorderColor.value,
            )
            .clickable(true, onClick = {keyboardController?.show()}),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.fillMaxHeight(1f)
            .clickable( onClick = {changeColor() })
            .fillMaxWidth(0.2f)
            .background(color = currentIconContainerColor.value),
            contentAlignment = Alignment.Center
        )
        {
            Icon(modifier = Modifier.fillMaxHeight(),
                imageVector = priority_high,
                contentDescription = "Exlamation Point",
                tint = currentIconColor.value
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        BasicTextField(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.7f)
                .focusRequester(focusRequest)
                .focusable()
                .clickable {
                    focusRequest.requestFocus()
                },
            maxLines = 3,
            singleLine = false,
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
                keyboardController?.hide()
            },
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = false,
                imeAction = ImeAction.Done
            ),
            value = text,
            onValueChange = { userText ->
                val newSize = (16 downTo 11).firstOrNull { size ->
                    val result = textMeasurer.measure(
                        text = AnnotatedString(userText),
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

                fontSize = newSize.sp
                text = userText
                priorityElement?.name = userText
            },
            textStyle = TextStyle(
                color = Color(0xFF153637),
                fontFamily = boldonse,
                fontWeight = FontWeight.Normal,
                fontSize = fontSize,
                lineHeight = 14.sp
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Box(modifier = Modifier.fillMaxHeight(1f)
            .pointerInput(Unit ){
                detectDragGesturesAfterLongPress(
                    onDragStart = { isShadowElevation.value = true; },
                    onDragEnd = {isShadowElevation.value = false}
                ) {
                        change, dragAmount ->
                    priorityElement?.x += dragAmount.x
                    priorityElement?.y += dragAmount.y

                }
            }
            .fillMaxWidth(1f)
            .background(color = currentIconContainerColor.value),
            contentAlignment = Alignment.Center
        )
        {
            Icon(modifier = Modifier.fillMaxHeight(),
                imageVector = fingerprint,
                contentDescription = "Thumb Move",
                tint = currentIconColor.value
            )
        }
    }
}

@Composable
fun aPriorityButtonFive(indexFromParent: Int, priorityPaent: SnapshotStateList<Priority>){
    val priorityElement = priorityPaent[indexFromParent]

    val index: MutableState<Int> = remember { mutableStateOf(0) }
    val focusRequest = remember { FocusRequester() }
    val currentIconContainerColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF00999F)) }
    val currentRowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentIconColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentRowBorderColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }
    val currentShadowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }
    var text by rememberSaveable { mutableStateOf("Priority") }

    val isShadowElevation = remember { mutableStateOf(false) }
    val textMeasurer = rememberTextMeasurer()
    var fontSize by remember { mutableStateOf(16.sp) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    fun addShadow(): Shadow{
        if(isShadowElevation.value == true){
            return Shadow(
                radius = 10.dp,
                spread = -5.dp,
                color = currentShadowColor.value,
                offset = DpOffset(0.dp, 20.dp)
            )
        } else{
            return Shadow(
                radius = 0.dp,
                spread = 0.dp,
                color = currentShadowColor.value,
                offset = DpOffset(0.dp, 0.dp)
            )
        }
    }

    fun changeColor() {
        if (index.value > 2) {
            index.value = 0
        }
        currentIconContainerColor.value = iconContainerolors[index.value]
        currentRowColor.value = rowColors[index.value]
        currentIconColor.value = iconColors[index.value]
        currentRowBorderColor.value = rowBorderColors[index.value]
        currentShadowColor.value = ShadowColors[index.value]

        index.value += 1
    }
    Row(
        modifier = Modifier
            .offset(
                {
                    IntOffset(x = priorityElement?.x?.roundToInt() ?: 0,
                        y = priorityElement?.y?.roundToInt() ?: 0)
                }
            )
            .dropShadow(
                shape = RoundedCornerShape(percent = 15),
                shadow = addShadow()
            )
            .fillMaxWidth(0.7f)
            .fillMaxHeight(0.055f)
            .background(color = currentRowColor.value)
            .border(  width = 1.4.dp,
                color = currentRowBorderColor.value,
            )
            .clickable(true, onClick = {keyboardController?.show()}),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.fillMaxHeight(1f)
            .clickable( onClick = {changeColor() })
            .fillMaxWidth(0.2f)
            .background(color = currentIconContainerColor.value),
            contentAlignment = Alignment.Center
        )
        {
            Icon(modifier = Modifier.fillMaxHeight(),
                imageVector = priority_high,
                contentDescription = "Exlamation Point",
                tint = currentIconColor.value
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        BasicTextField(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.7f)
                .focusRequester(focusRequest)
                .focusable()
                .clickable {
                    focusRequest.requestFocus()
                },
            maxLines = 3,
            singleLine = false,
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
                keyboardController?.hide()
            },
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = false,
                imeAction = ImeAction.Done
            ),
            value = text,
            onValueChange = { userText ->
                val newSize = (16 downTo 11).firstOrNull { size ->
                    val result = textMeasurer.measure(
                        text = AnnotatedString(userText),
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

                fontSize = newSize.sp
                text = userText
                priorityElement?.name = userText
            },
            textStyle = TextStyle(
                color = Color(0xFF153637),
                fontFamily = boldonse,
                fontWeight = FontWeight.Normal,
                fontSize = fontSize,
                lineHeight = 14.sp
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Box(modifier = Modifier.fillMaxHeight(1f)
            .pointerInput(Unit ){
                detectDragGesturesAfterLongPress(
                    onDragStart = { isShadowElevation.value = true; },
                    onDragEnd = {isShadowElevation.value = false}
                ) {
                        change, dragAmount ->
                    priorityElement?.x += dragAmount.x
                    priorityElement?.y += dragAmount.y

                }
            }
            .fillMaxWidth(1f)
            .background(color = currentIconContainerColor.value),
            contentAlignment = Alignment.Center
        )
        {
            Icon(modifier = Modifier.fillMaxHeight(),
                imageVector = fingerprint,
                contentDescription = "Thumb Move",
                tint = currentIconColor.value
            )
        }
    }
}

@Composable
fun aPriorityButtonSix(indexFromParent: Int, priorityPaent: SnapshotStateList<Priority>){
    val priorityElement = priorityPaent[indexFromParent]

    val index: MutableState<Int> = remember { mutableStateOf(0) }
    val focusRequest = remember { FocusRequester() }
    val currentIconContainerColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF00999F)) }
    val currentRowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentIconColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentRowBorderColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }
    val currentShadowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }
    var text by rememberSaveable { mutableStateOf("Priority") }

    val isShadowElevation = remember { mutableStateOf(false) }
    val textMeasurer = rememberTextMeasurer()
    var fontSize by remember { mutableStateOf(16.sp) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    fun addShadow(): Shadow{
        if(isShadowElevation.value == true){
            return Shadow(
                radius = 10.dp,
                spread = -5.dp,
                color = currentShadowColor.value,
                offset = DpOffset(0.dp, 20.dp)
            )
        } else{
            return Shadow(
                radius = 0.dp,
                spread = 0.dp,
                color = currentShadowColor.value,
                offset = DpOffset(0.dp, 0.dp)
            )
        }
    }

    fun changeColor() {
        if (index.value > 2) {
            index.value = 0
        }
        currentIconContainerColor.value = iconContainerolors[index.value]
        currentRowColor.value = rowColors[index.value]
        currentIconColor.value = iconColors[index.value]
        currentRowBorderColor.value = rowBorderColors[index.value]
        currentShadowColor.value = ShadowColors[index.value]

        index.value += 1
    }
    Row(
        modifier = Modifier
            .offset(
                {
                    IntOffset(x = priorityElement?.x?.roundToInt() ?: 0,
                        y = priorityElement?.y?.roundToInt() ?: 0)
                }
            )
            .dropShadow(
                shape = RoundedCornerShape(percent = 15),
                shadow = addShadow()
            )
            .fillMaxWidth(0.7f)
            .fillMaxHeight(0.055f)
            .background(color = currentRowColor.value)
            .border(  width = 1.4.dp,
                color = currentRowBorderColor.value,
            )
            .clickable(true, onClick = {keyboardController?.show()}),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.fillMaxHeight(1f)
            .clickable( onClick = {changeColor() })
            .fillMaxWidth(0.2f)
            .background(color = currentIconContainerColor.value),
            contentAlignment = Alignment.Center
        )
        {
            Icon(modifier = Modifier.fillMaxHeight(),
                imageVector = priority_high,
                contentDescription = "Exlamation Point",
                tint = currentIconColor.value
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        BasicTextField(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.7f)
                .focusRequester(focusRequest)
                .focusable()
                .clickable {
                    focusRequest.requestFocus()
                },
            maxLines = 3,
            singleLine = false,
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
                keyboardController?.hide()
            },
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = false,
                imeAction = ImeAction.Done
            ),
            value = text,
            onValueChange = { userText ->
                val newSize = (16 downTo 11).firstOrNull { size ->
                    val result = textMeasurer.measure(
                        text = AnnotatedString(userText),
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

                fontSize = newSize.sp
                text = userText
                priorityElement?.name = userText
            },
            textStyle = TextStyle(
                color = Color(0xFF153637),
                fontFamily = boldonse,
                fontWeight = FontWeight.Normal,
                fontSize = fontSize,
                lineHeight = 14.sp
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Box(modifier = Modifier.fillMaxHeight(1f)
            .pointerInput(Unit ){
                detectDragGesturesAfterLongPress(
                    onDragStart = { isShadowElevation.value = true; },
                    onDragEnd = {isShadowElevation.value = false}
                ) {
                        change, dragAmount ->
                    priorityElement?.x += dragAmount.x
                    priorityElement?.y += dragAmount.y

                }
            }
            .fillMaxWidth(1f)
            .background(color = currentIconContainerColor.value),
            contentAlignment = Alignment.Center
        )
        {
            Icon(modifier = Modifier.fillMaxHeight(),
                imageVector = fingerprint,
                contentDescription = "Thumb Move",
                tint = currentIconColor.value
            )
        }
    }
}


