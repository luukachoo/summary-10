package com.example.summary10.presentation.state.from_bottom_sheet

import com.example.summary10.presentation.model.Account

data class FromSheetViewState(
    val accounts: List<Account>? = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
