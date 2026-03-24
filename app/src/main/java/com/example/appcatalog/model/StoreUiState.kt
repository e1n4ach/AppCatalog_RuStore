package com.example.appcatalog.model

sealed interface StoreUiState {
    data object Loading : StoreUiState
    data class Error(val message: String) : StoreUiState
    data object Empty : StoreUiState
    data class Success(val apps: List<AppItem>) : StoreUiState
}