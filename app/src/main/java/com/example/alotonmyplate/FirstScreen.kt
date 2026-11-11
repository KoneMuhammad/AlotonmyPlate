@file:JvmName("FirstScreenKt")

package com.example.alotonmyplate

import android.R.attr.maxHeight
import android.R.attr.maxWidth
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.test.add
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.test.imageVector.priority_high
import kotlin.math.roundToInt


@Composable
    fun FirstScreen(navigator: NavController) {
        val isendOScreen = remember { mutableStateOf(false) }

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
                .background(color = Color.Cyan)
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
                )

        )
        {
            listOfComposables.forEach { content ->
                content()
            }
            Row(
                modifier = Modifier.fillMaxWidth(0.43f)
                    .fillMaxHeight(0.03f)
                    .offset(x = maxWidth * 0.3f, y = maxHeight * 0.9f)
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun previewComposable() {
    val navigator = rememberNavController()

        val isendOScreen = remember { mutableStateOf(false) }

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
                .background(color = Color.Cyan)
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
                )

        )
        {
            val priorityName: MutableState<String> = remember { mutableStateOf("real interger ") }
            val keyboardController = LocalSoftwareKeyboardController.current
            val index: MutableState<Int> = remember { mutableStateOf(0) }

            val currentIconContainerColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF00999F)) }
            val currentRowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
            val currentIconColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
            val currentRowBorderColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }
            val currentShadowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }

            val mostRecentPointerLocation = remember { mutableStateOf<Offset>(Offset.Zero) }
            val isShadowElevation = remember { mutableStateOf(false) }

            val boldonse = FontFamily(
                Font(resId = R.font.boldonse_regular)
            ) //////////////////////////////////////////////////

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
                    .offset(x = 90.dp, y = 300.dp)
                    .dropShadow(
                        shape = RoundedCornerShape(percent = 15),
                        shadow = addShadow()
                    )
                    .pointerInput(Unit ){
                        detectDragGesturesAfterLongPress(
                            onDragStart = { isShadowElevation.value = true; },
                            onDragEnd = {isShadowElevation.value = false}
                        ) {
                                change, dragAmount ->
                            mostRecentPointerLocation.value += dragAmount
                        }
                    }
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.055f)///////////////////////////////////
                    //.clip(RoundedCornerShape(percent = 10))///////////////
                    .background(color = currentRowColor.value)
                    .border( width = 1.4.dp, ///////////////////////////////////
                        color = currentRowBorderColor.value,
                        shape = RoundedCornerShape(percent = 10))/////////////////
                    .clickable(true, onClick = {keyboardController?.show()}),
               // horizontalArrangement = Arrangement.SpaceEvenly,//////////////////////////
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.fillMaxHeight(1f)//////////////
                    .clickable( onClick = {changeColor() }) /////////////////////
                    .fillMaxWidth(0.23f)/////////
                   // .clip(RoundedCornerShape(percent = 15))/////
                    .background(color = currentIconContainerColor.value),
                    contentAlignment = Alignment.Center,
                )
                {
                    Icon(modifier = Modifier.fillMaxHeight(),////////////////
                        imageVector = priority_high,
                        contentDescription = "Exlamation Point",
                        tint = currentIconColor.value
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))//////////////////
                Text(priorityName.value, color = Color(0xFF153637),
                    fontFamily = boldonse,          ///////////////////////
                    fontWeight = FontWeight.Normal,
                    maxLines = 3, /////////////////////////////////////
                    softWrap = true, ///////////////////////////////////
                    autoSize = TextAutoSize.StepBased(
                        minFontSize = 6.sp, ////////////////////////////
                        maxFontSize = 18.sp /////////////////////////////
                    )
                    )
                Spacer(modifier = Modifier.width(5.dp)) /////////////////////////
            }
            Row(
                modifier = Modifier.fillMaxWidth(0.43f)
                    .fillMaxHeight(0.03f)
                    .offset(x = maxWidth * 0.3f, y = maxHeight * 0.9f)
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


