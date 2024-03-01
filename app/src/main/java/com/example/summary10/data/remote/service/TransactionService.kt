package com.example.summary10.data.remote.service

import com.example.summary10.data.remote.network.model.AccountDto
import retrofit2.Response

interface TransactionService {
    suspend fun getAccount(): Response<AccountDto>
}