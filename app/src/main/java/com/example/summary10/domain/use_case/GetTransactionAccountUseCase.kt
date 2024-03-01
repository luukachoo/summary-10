package com.example.summary10.domain.use_case

import com.example.summary10.data.remote.util.Resource
import com.example.summary10.domain.model.GetAccount
import com.example.summary10.domain.repository.TransactionRepository
import com.example.summary10.domain.util.FieldValidation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetTransactionAccountUseCase @Inject constructor(
    private val repository: TransactionRepository, private val validation: FieldValidation
) {
    suspend operator fun invoke(personalNumber: String, accountNumber: String, phoneNumber: String): Flow<Resource<GetAccount>> {

        if (validation.isAccountNumberValid(accountNumber)) {
            return flowOf(Resource.Error(errorMessage = "Invalid account number"))
        }

        if (!validation.isUserIdValid(personalNumber)) {
            return flowOf(Resource.Error(errorMessage = "Invalid personal number"))
        }

        if (!validation.isPhoneNumberValid(phoneNumber)) {
            return flowOf(Resource.Error(errorMessage = "Invalid phone number"))
        }

        return repository.getAccount()
    }
}