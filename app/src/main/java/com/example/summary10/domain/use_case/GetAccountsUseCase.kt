package com.example.summary10.domain.use_case

import com.example.summary10.domain.repository.AccountRepository
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(private val repository: AccountRepository) {
    suspend operator fun invoke() =
        repository.getAccounts()
}