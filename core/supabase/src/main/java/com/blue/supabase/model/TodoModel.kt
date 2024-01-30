package com.blue.supabase.model

import com.blue.model.Todo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoModel(
    val date: String,
    val title: String,
    val content: String,
    val isDone: Boolean,
    val user_id: String = "",
    val update_date_time: String,
    val is_deleted: Boolean,
    val id: Long = 0
)

//fun TodoModel.toTodo(): Todo =
//    Todo(id = local_id, date = date, title = title, content = content, isDone = isDone)


//fun TodoModel.toTodoEntity(): Todo =
//    Todo(id = local_id, date = date, title = title, content = content, isDone = isDone)