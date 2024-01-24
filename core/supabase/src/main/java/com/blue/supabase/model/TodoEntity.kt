package com.blue.supabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blue.model.Todo

@Entity(tableName = "TodoList")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo var date: String,
    @ColumnInfo var title: String,
    @ColumnInfo var content: String,
    @ColumnInfo var isDone: Boolean,
)

fun TodoEntity.todoEntityToTodo(): Todo =
    Todo(id = id, date = date, title = title, content = content, isDone = isDone)
