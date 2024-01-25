package com.blue.model

data class Todo(
    val date: String,
    val title: String,
    val content: String,
    val isDone: Boolean,
    val id: Long = 0
)