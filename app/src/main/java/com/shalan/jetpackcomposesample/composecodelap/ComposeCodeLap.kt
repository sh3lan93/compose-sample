package com.shalan.jetpackcomposesample.composecodelap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shalan.jetpackcomposesample.R
import com.shalan.jetpackcomposesample.ui.theme.JetPackComposeSampleTheme

class ComposeCodeLap : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeSampleTheme {
                var shouldShowOnBoarding by rememberSaveable {
                    mutableStateOf(true)
                }
                if (shouldShowOnBoarding)
                    OnBoardingScreen {
                        shouldShowOnBoarding = false
                    }
                else
                    ComposeCodeLapRoot()
            }
        }
    }
}

@Composable
fun OnBoardingScreen(onStartClicked: () -> Unit = {}) {

    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to our application")

            Button(onClick = onStartClicked, modifier = Modifier.padding(24.dp)) {
                Text(text = "Start")
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComposeCodeLapRoot(names: List<String> = List(1000) { "$it" }) {
    Surface(color = MaterialTheme.colorScheme.background) {
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            stickyHeader {
                Text(text = "A Sticky Header")
            }
            items(names) { item ->
                Greeting2(name = item)
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(all = 24.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = "Hello")
                Text(text = name, style = MaterialTheme.typography.titleMedium)
                if (isExpanded)
                    Text(
                        text = ("Compose ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4),
                    )
            }

            IconButton(onClick = {
                isExpanded = isExpanded.not()
            }) {
                Icon(
                    contentDescription = if (isExpanded.not()) stringResource(id = R.string.show_more) else stringResource(
                        id = R.string.show_less
                    ),
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    JetPackComposeSampleTheme {
        ComposeCodeLapRoot()
    }
}

@Preview(showBackground = true, name = "Onboarding Screen")
@Composable
fun OnBoardingScreenPreview() {
    JetPackComposeSampleTheme {
        OnBoardingScreen()
    }
}