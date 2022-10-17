package com.shalan.jetpackcomposesample.composestate

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.codelabs.state.WellnessTask

class WellnessViewModel : ViewModel() {

    private val _tasks = tasksItems.toMutableStateList()

    val tasks: List<WellnessTask>
        get() = _tasks

    fun remove(item: WellnessTask){
        _tasks.remove(item)
    }
}

val tasksItems = List<WellnessTask>(30) { index ->
    WellnessTask(id = index, label = "Task #$index")
}