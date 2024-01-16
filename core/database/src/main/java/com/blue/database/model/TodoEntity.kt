package com.blue.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TodoList")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo var title: String,
    @ColumnInfo var content: String,
    @ColumnInfo val check: Boolean,
)
