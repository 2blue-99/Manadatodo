package com.blue.mandalart.uistate

import com.blue.model.Mandalart

sealed interface MandalartUiState {
    data object Loading: MandalartUiState

    data class Error(val msg: String) : MandalartUiState

    data class Success(
        val sum: Int,
        val mandalart: List<Mandalart>
    ): MandalartUiState
}