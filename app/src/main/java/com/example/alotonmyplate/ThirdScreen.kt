package com.example.alotonmyplate


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.test.add

@Composable
fun ThirdScreen(navigator: NavController) {
    val isReadOnly = remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }

    val listOfComposables = remember {
        mutableStateListOf<@Composable () -> Unit>()
    }
    val incrament = remember { mutableStateOf(0) }

    fun incramentor() {
        if (incrament.value == 6) {
            return;
        }
        listOfComposables.add { aPriorityButton() }
        incrament.value++

    }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFBCB6D8))
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if(dragAmount < 0 ){
                        navigator.navigate(fourthScreen)
                    }
                    else{
                        navigator.navigate(secondScreen)
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
        Button(modifier = Modifier
            .size(80.dp)
            .offset(x = maxWidth * 0.35f, y = maxHeight * -0.33f)
            .dropShadow(shape = CircleShape,
                shadow = Shadow(
                    radius = 7.dp,
                    offset = DpOffset(x = 0.dp,y= 10.dp),
                    color = Color(0xFF9D95C6),
                )),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9D95C6)
            ),
            onClick = {
                isReadOnly.value = false
                Log.i("ReadOnly","${isReadOnly.value}")
                focusRequester.requestFocus()
                Log.i("requestFocus","${focusRequester.requestFocus()}")
            }
        ) {
            Text(modifier = Modifier.fillMaxWidth(),
                text =  "rename"
                , textAlign = TextAlign.Center,
                fontSize = 17.sp)
        }
        BasicTextField(
            modifier = Modifier
                .focusRequester(focusRequester)
                .combinedClickable(
                    onDoubleClick = { incramentor() },
                    interactionSource = null,
                    indication = null,
                    onClick = { }
                ),
            textStyle = TextStyle(
                fontSize = 64.sp,
                fontFamily = graphitiBackground,
                textAlign = TextAlign.Center,
                color = Color(0xFF0A0911).copy(0.35f),
            ),
            readOnly = isReadOnly.value,
            state = rememberTextFieldState(initialText = "Notes"),
            onKeyboardAction = KeyboardActionHandler {
                isReadOnly.value = true
            },

            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )

        listOfComposables.forEach { content ->
            content()
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
                            if (index == 2) Color(0xFF28A51D) else Color.Black
                        )
                )
            }
        }
    }
}