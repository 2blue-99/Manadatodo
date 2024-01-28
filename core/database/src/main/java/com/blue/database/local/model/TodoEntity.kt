package com.blue.database.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blue.model.Todo

@Entity(tableName = "TodoList")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo var updateDateTime: String,
    @ColumnInfo var title: String,
    @ColumnInfo var content: String,
    @ColumnInfo var isDone: Boolean,
)

fun TodoEntity.toTodoModel(): Todo =
    Todo(id = id, date = updateDateTime, title = title, content = content, isDone = isDone)
