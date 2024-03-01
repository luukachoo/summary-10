package com.example.summary10.data.remote.network.mapper

import com.example.summary10.data.remote.network.model.AccountDto
import com.example.summary10.domain.model.GetAccount

fun AccountDto.toDomainModel() = GetAccount(
    accountName = accountName,
    accountNumber = accountNumber,
    balance = balance ?: 0,
    cardLogo = cardLogo,
    cardType = cardType,
    id = id,
    valuteType = valuteType
)