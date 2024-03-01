package com.example.summary10.data.remote.service

import com.example.summary10.data.remote.network.model.AccountDto
import retrofit2.Response
import retrofit2.http.GET

interface AccountService {
    @GET("5c74ec0e-5cc1-445e-b64b-b76b286b215f")
    suspend fun getAccounts(): Response<List<AccountDto>>
}