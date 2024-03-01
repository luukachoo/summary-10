package com.example.summary10.domain.model

data class GetAccount (
    val accountName: String,
    val accountNumber: String,
    val balance: Int,
    val cardLogo: String?,
    val cardType: String,
    val id: Int,
    val valuteType: String
)