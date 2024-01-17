package com.blue.daily.uiState

import com.blue.model.Todo

sealed interface DailyUiState {
    data object Loading : DailyUiState

    data class Error(val msg: String) : DailyUiState

    data class Success(
        val today: String,
        val totalCnt: Int,
        val doneCnt: Int,
        val todoList: List<Todo>
    ) : DailyUiState
}