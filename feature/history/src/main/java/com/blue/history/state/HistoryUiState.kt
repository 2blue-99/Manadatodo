package com.blue.history.state

import com.blue.model.Todo

sealed interface HistoryUiState {
    data object Loading: HistoryUiState

    data class Error(val msg: String): HistoryUiState

    data class Success(
        val totalCnt: Int,
        val doneCnt: Int,
        val todoList: List<Todo>
    ): HistoryUiState
}