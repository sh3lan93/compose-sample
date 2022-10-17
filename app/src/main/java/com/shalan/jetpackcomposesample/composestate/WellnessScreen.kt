package com.shalan.jetpackcomposesample.composelayout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Checkbox
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codelabs.state.WellnessTask
import com.shalan.jetpackcomposesample.composestate.WellnessViewModel
import com.shalan.jetpackcomposesample.ui.theme.JetPackComposeSampleTheme


@Composable
fun WellnessScreen(modifier: Modifier = Modifier, viewmodel: WellnessViewModel = viewModel()) {
    Column(modifier = modifier) {
        WaterCount()
        WellnessTaskList(list = viewmodel.tasks, onClose = {
            viewmodel.remove(it)
        })
    }
}

@Composable
fun WaterCount(modifier: Modifier = Modifier) {

    var count by remember {
        mutableStateOf(0)
    }

    StatelessCounter(count, onClick = { count += 1 }, modifier)
}

@Composable
private fun StatelessCounter(count: Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (count > 0) {
            Text(text = "You've had $count glasses")

        }
        Button(
            onClick = onClick,
            modifier = Modifier.padding(8.dp),
            enabled = count < 10
        ) {
            Text(text = "Add One")
        }
    }
}

@Preview(name = "WaterCountPreview")
@Composable
fun WaterCountPreview() {
    JetPackComposeSampleTheme {
        WaterCount()
    }
}

@Composable
fun WellnessTaskItem(
    label: String,
    checked: Boolean,
    onCheckChanged: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = label,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Checkbox(checked = checked, onCheckedChange = onCheckChanged)
        IconButton(onClick = onClose) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Composable
fun WellnessTaskItem(taskName: String, onClose: () -> Unit, modifier: Modifier = Modifier) {
    var isChecked by rememberSaveable {
        mutableStateOf(false)
    }
    WellnessTaskItem(
        label = taskName,
        checked = isChecked,
        onCheckChanged = { isChecked = it },
        onClose = onClose,
        modifier = modifier
    )

}

@Composable
fun WellnessTaskList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask> = remember {
        tasksItems
    },
    onClose: (item: WellnessTask) -> Unit
) {
    LazyColumn(modifier = modifier, state = rememberLazyListState()) {
        items(items = list, key = { it.id }) { item ->
            WellnessTaskItem(taskName = item.label, onClose = {
                onClose.invoke(item)
            })
        }
    }
}

@Preview(name = "WellnessTaskListPreview")
@Composable
fun WellnessTaskListPreview() {
    WellnessTaskList(onClose = {

    })
}

val tasksItems = List<WellnessTask>(30) { index ->
    WellnessTask(id = index, label = "Task #$index")
}