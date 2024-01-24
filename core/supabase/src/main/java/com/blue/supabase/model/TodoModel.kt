package com.blue.supabase.model

import com.blue.model.Todo
import kotlinx.serialization.Serializable

@Serializable
data class TodoModel(
    val id: Int = 0,
    val date: String,
    val title: String,
    val content: String,
    val isDone: Boolean,
)

fun TodoModel.todoEntityToTodo(): Todo =
    Todo(id = id, date = date, title = title, content = content, isDone = isDone)
