package com.example.summary10.data.remote.repository

import com.example.summary10.data.remote.network.mapper.asResource
import com.example.summary10.data.remote.network.mapper.toDomainModel
import com.example.summary10.data.remote.service.TransactionService
import com.example.summary10.data.remote.util.Resource
import com.example.summary10.data.remote.util.ResponseHandler
import com.example.summary10.domain.model.GetAccount
import com.example.summary10.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val service: TransactionService,
    private val responseHandler: ResponseHandler
) : TransactionRepository {

    override suspend fun getAccount(): Flow<Resource<GetAccount>> {
        return responseHandler.handleApiCall {
            service.getAccount()
        }.asResource { it.toDomainModel() }
    }

}