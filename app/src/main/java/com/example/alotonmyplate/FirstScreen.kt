@file:JvmName("FirstScreenKt")

package com.example.alotonmyplate

import android.R.attr.maxHeight
import android.R.attr.maxWidth
import android.R.attr.text
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
    fun FirstScreen(navigator: NavController) {

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val iseditable = remember { mutableStateOf(false) }

    val doThis = KeyboardActionHandler {
        iseditable.value = false
        keyboardController?.hide()
    }

        val isendOScreen = remember { mutableStateOf(false) }
        val listOfComposables = remember {
            mutableStateListOf<@Composable () -> Unit>() }
        val incrament = remember { mutableStateOf(0) }
        fun incramentor() {
            if (incrament.value == 6) {
                return;
            }
            listOfComposables.add { aPriorityButton() }
            incrament.value++ }
    var text by remember { mutableStateOf("Super Important") }

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
            Button(modifier = Modifier.size(80.dp)
                .offset(x = maxWidth * 0.15f, y = maxHeight * - 0.3f)
                .background(color = Color(0xFF00999F)),
                onClick = {
                    iseditable.value = true
                    focusRequester.requestFocus()
                    keyboardController?.show()
                }
            ) {
                Text("change bg"
                , textAlign = TextAlign.Center,
                    autoSize = TextAutoSize.StepBased(
                        minFontSize = 8.sp,
                    maxFontSize = 20.sp,
                    ))
            }
                BasicTextField(
                    modifier = Modifier
                        .focusRequester(focusRequester),
                    textStyle = TextStyle(
                        fontSize = 64.sp,
                        fontFamily = graphitiBackground,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                    ),
                    enabled =  iseditable.value,
                    onKeyboardAction = doThis,
                    state = rememberTextFieldState(initialText = "Notes"),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
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

val graphitiBackground = FontFamily(Font(R.font.permanentmarker_regular))
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun test() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {

    }
}
// i dont want it to be editable
// either button, or/**
/** read only, or editable, editable is hwole thing untouched i like that
 * no hold, make it on like swipe up maybe? nahh
 */
// now persist information, on screen change,
// persist on app close
// make button look better


