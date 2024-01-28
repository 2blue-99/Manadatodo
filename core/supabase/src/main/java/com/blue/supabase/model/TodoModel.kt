package com.blue.supabase.model

import com.blue.model.Todo
import kotlinx.serialization.Serializable

@Serializable
data class TodoModel(
    val row_id: Long,
    val date: String,
    val title: String,
    val content: String,
    val isDone: Boolean,
)

fun TodoModel.toTodoModel(): Todo =
    Todo(id = row_id, date = date, title = title, content = content, isDone = isDone)
