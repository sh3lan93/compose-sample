package com.shalan.jetpackcomposesample.composestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shalan.jetpackcomposesample.composelayout.WellnessScreen
import com.shalan.jetpackcomposesample.ui.theme.JetPackComposeSampleTheme

class ComposeStateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.background
                ) {
                    ComposeStateRoot()
                }
            }
        }
    }
}

@Composable
fun ComposeStateRoot() {
    WellnessScreen()
}

@Preview(name = "DefaultPreview", showBackground = true)
@Composable
fun DefaultPreview() {
    JetPackComposeSampleTheme {
        ComposeStateRoot()
    }
}

