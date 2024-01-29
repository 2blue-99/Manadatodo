package com.blue.supabase.model

import com.blue.model.Todo
import kotlinx.serialization.Serializable

@Serializable
data class TodoModel(
    val id: Long,
    val date: String,
    val title: String,
    val content: String,
    val isDone: Boolean,
    val user_id: String="",
    val update_date_time: String,
    val is_deleted: Boolean,
    val local_id: Long,
)

fun TodoModel.toTodo(): Todo =
    Todo(id = local_id, date = date, title = title, content = content, isDone = isDone)


//fun TodoModel.toTodoEntity(): Todo =
//    Todo(id = local_id, date = date, title = title, content = content, isDone = isDone)