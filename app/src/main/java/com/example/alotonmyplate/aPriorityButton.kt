package com.example.alotonmyplate

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun aPriorityButton(){
    val priorityName: MutableState<String> = remember { mutableStateOf("this random text") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val index: MutableState<Int> = remember { mutableStateOf(0) }

    val currentIconContainerColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF00999F)) }
    val currentRowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentIconColor: MutableState<Color> = remember { mutableStateOf(Color(0xFFC9ECED)) }
    val currentRowBorderColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }
    val currentShadowColor: MutableState<Color> = remember { mutableStateOf(Color(0xFF153637)) }

    val mostRecentPointerLocation = remember { mutableStateOf<Offset>(Offset.Zero) }
    val isShadowElevation = remember { mutableStateOf(false) }

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
                IntOffset(x = mostRecentPointerLocation.value.x.roundToInt(),
                    y = mostRecentPointerLocation.value.y.roundToInt())
            }
        )
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
            .fillMaxHeight(0.055f)
            .background(color = currentRowColor.value)
            .border(  width = 1.4.dp,
                color = currentRowBorderColor.value,
                shape = RoundedCornerShape(percent = 10))
            .clickable(true, onClick = {keyboardController?.show()}),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.fillMaxHeight(1f)
            .clickable( onClick = {changeColor() })
            .fillMaxWidth(0.23f)
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
        Text(priorityName.value, color = Color(0xFF153637),
            fontFamily = boldonse,
            fontWeight = FontWeight.Normal,
            maxLines = 3,
            softWrap = true,
            autoSize = TextAutoSize.StepBased(
                minFontSize = 6.sp,
                maxFontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
    }
}

@Preview(showBackground = true,)
@Composable
fun previewaPriorityButton(){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(300.dp)
        .width(300.dp)
        .background(color = Color(0xFFD1E5DC)))
    {
        aPriorityButton()
    }
}
