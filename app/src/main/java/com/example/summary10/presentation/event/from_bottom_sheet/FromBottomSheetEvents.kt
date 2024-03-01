package com.example.summary10.presentation.event.from_bottom_sheet

sealed class FromBottomSheetEvents {
    data object FetchAccounts : FromBottomSheetEvents()
    data object ResetErrorMessage : FromBottomSheetEvents()
}