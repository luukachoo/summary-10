package com.example.summary10.presentation.state.to_bottom_sheet

import com.example.summary10.presentation.model.Account

data class ToFragmentState(
    val account: Account? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
