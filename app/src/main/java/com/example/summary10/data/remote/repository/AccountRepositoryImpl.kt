package com.example.summary10.data.remote.repository

import com.example.summary10.data.remote.network.mapper.asResource
import com.example.summary10.data.remote.network.mapper.toDomainModel
import com.example.summary10.data.remote.service.AccountService
import com.example.summary10.data.remote.util.Resource
import com.example.summary10.data.remote.util.ResponseHandler
import com.example.summary10.domain.model.GetAccount
import com.example.summary10.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val service: AccountService,
    private val responseHandler: ResponseHandler
) : AccountRepository {
    override suspend fun getAccounts(): Flow<Resource<List<GetAccount>>> {
        return responseHandler.handleApiCall {
            service.getAccounts()
        }.asResource {
            it.map { dto ->
                dto.toDomainModel()
            }
        }
    }
}