package com.example.summary10.presentation.event.to_bottom_sheet

sealed class ToFragmentEvents {
    data class FetchAccount(val cardNumber: String, val personalNumber: String, val phoneNumber: String) : ToFragmentEvents()
    data object ResetErrorMessage : ToFragmentEvents()
}