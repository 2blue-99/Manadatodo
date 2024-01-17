package com.blue.model

data class Todo(
    val id: Int,
    val date: String,
    val title: String,
    val content: String,
    val isDone: Boolean
)