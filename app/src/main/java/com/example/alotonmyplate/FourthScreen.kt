package com.example.alotonmyplate


import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.test.add

@Composable
fun FourthScreen(navigator: NavController) {

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
            .background(color = Color(0xFFB6D8D4))
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if(dragAmount < 0 ){
                        //animation that sais last screen
                    }
                    else{
                        navigator.navigate(thirdScreen)
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
        Text(
            maxLines = 10,
            fontSize = 64.sp,
            textAlign = TextAlign.Center,
            text = "",
            fontFamily = graphitiBackground,
            color = Color(0xFF101109).copy(alpha = 0.35f),
            autoSize = TextAutoSize.StepBased(
                minFontSize = 18.sp,
                maxFontSize = 64.sp
            )
        )

        listOfComposables.forEach { content ->
            content()

        }
        Row(
            modifier = Modifier.fillMaxWidth(0.43f)
                .fillMaxHeight(0.03f)
                .offset( y = maxHeight * 0.9f)
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
                            if (index == 3) Color.Green else Color.Black
                        )
                )
            }
        }
    }
}