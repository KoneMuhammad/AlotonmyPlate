package com.example.alotonmyplate

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.alotonmyplate.ui.theme.AlotonmyPlateTheme



class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val multipleScreens = remember { mutableStateListOf<indexHolder>() }

            multipleScreens.forEachIndexed { index, holder ->
                holder.index = index
            }

            indexHolder().PrioritiesScreen(multipleScreens)
        }
    }
}
// made a index hes being displayed
// him adding to the place, then him deciding to return what was added not right
// becuase hes going back to caller to the

// so he stores that variable on his stack
// pushes to goes to another something:
// that other something puts on the stack,
// i take from the first stack and move it to the second stack
//4 stacks with values of pointers to the heap base address
// 4 heap base addresses with indexes of themsleves
