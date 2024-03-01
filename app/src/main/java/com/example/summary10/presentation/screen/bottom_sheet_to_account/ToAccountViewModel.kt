package com.example.summary10.presentation.screen.bottom_sheet_to_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summary10.data.remote.util.Resource
import com.example.summary10.domain.use_case.GetTransactionAccountUseCase
import com.example.summary10.presentation.event.to_bottom_sheet.ToFragmentEvents
import com.example.summary10.presentation.mapper.toPresentationModel
import com.example.summary10.presentation.state.to_bottom_sheet.ToFragmentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class ToAccountViewModel @Inject constructor(
    private val getTransactionAccountUseCase: GetTransactionAccountUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow(ToFragmentState())
    val screenState get() = _screenState

    fun onEvent(event: ToFragmentEvents) {
        when (event) {
            is ToFragmentEvents.FetchAccount -> fetchAccount(
                event.cardNumber,
                event.personalNumber,
                event.phoneNumber
            )

            ToFragmentEvents.ResetErrorMessage -> updateErrorMessage(null)
        }
    }

    private fun fetchAccount(cardNumber: String, personalNumber: String, phoneNumber: String) {
        viewModelScope.launch {
            getTransactionAccountUseCase(cardNumber, personalNumber, phoneNumber).collect { res ->
                _screenState.update { it.copy(isLoading = true) }
                when (res) {
                    is Resource.Error -> updateErrorMessage(res.errorMessage)
                    is Resource.Success -> {
                        _screenState.update {
                            it.copy(
                                account = res.data.toPresentationModel(),
                                isLoading = false,
                            )
                        }
                    }
                }
            }
        }
    }


    private fun updateErrorMessage(message: String?) {
        _screenState.update { it.copy(errorMessage = message) }
    }
}