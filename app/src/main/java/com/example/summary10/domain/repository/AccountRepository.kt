package com.example.summary10.domain.repository

import com.example.summary10.data.remote.util.Resource
import com.example.summary10.domain.model.GetAccount
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getAccounts(): Flow<Resource<List<GetAccount>>>
}