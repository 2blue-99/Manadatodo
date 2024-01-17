package com.blue.daily

import com.blue.model.Todo
import java.time.LocalDate

sealed interface BottomSheetUiState {
    data object Down : BottomSheetUiState
    data class  Up(val todoUiState: TodoUiState) : BottomSheetUiState
}

sealed interface TodoUiState{
    val todo: Todo

    data class Default(
        override val todo: Todo = Todo("", "", "", false)
    ) : TodoUiState

    data class Exist(
        override val todo: Todo
    ) : TodoUiState
}