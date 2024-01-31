package com.blue.database.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blue.model.Todo

@Entity(tableName = "TodoList")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo var supaId: Long = 0,
    @ColumnInfo val updateDateTime: String,
    @ColumnInfo var isSynced: Boolean,
    @ColumnInfo val isDeleted: Boolean,
    @ColumnInfo val title: String,
    @ColumnInfo val content: String,
    @ColumnInfo val date: String,
    @ColumnInfo val isDone: Boolean,

)

fun TodoEntity.asTodo(): Todo =
    Todo(id = id, supa_id = supaId, date = date, title = title, content = content, isDone = isDone)
