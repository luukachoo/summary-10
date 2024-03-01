package com.example.summary10.presentation.screen.bottom_sheet_from_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summary10.data.remote.util.Resource
import com.example.summary10.domain.use_case.GetAccountsUseCase
import com.example.summary10.presentation.event.from_bottom_sheet.FromBottomSheetEvents
import com.example.summary10.presentation.mapper.toPresentationModel
import com.example.summary10.presentation.state.from_bottom_sheet.FromSheetViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FromAccountViewModel @Inject constructor(private val getAccountsUseCase: GetAccountsUseCase) :
    ViewModel() {

    private val _screenState = MutableStateFlow(FromSheetViewState())
    val screenState get() = _screenState

    fun onEvent(event: FromBottomSheetEvents) {
        when (event) {
            is FromBottomSheetEvents.FetchAccounts -> fetchAccounts()
            is FromBottomSheetEvents.ResetErrorMessage -> updateErrorMessage(null)
        }
    }

    private fun fetchAccounts() {
        viewModelScope.launch {
            getAccountsUseCase().collect { res ->
                _screenState.update { it.copy(isLoading = true) }
                when (res) {
                    is Resource.Error -> updateErrorMessage(res.errorMessage)
                    is Resource.Success -> {
                        _screenState.update {
                            it.copy(
                                accounts = res.data.map { domainModel -> domainModel.toPresentationModel() },
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