package com.example.summary10.presentation.mapper

import com.example.summary10.domain.model.GetAccount
import com.example.summary10.presentation.model.Account

fun GetAccount.toPresentationModel() = Account(
    accountName = accountName,
    accountNumber = accountNumber,
    balance = balance,
    cardLogo = cardLogo,
    cardType = cardType,
    id = id,
    valuteType = valuteType
)