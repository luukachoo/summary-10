package com.example.summary10.data.remote.network.model

import com.squareup.moshi.Json

data class AccountDto(
    @Json(name = "account_name")
    val accountName: String,
    @Json(name = "account_number")
    val accountNumber: String,
    @Json(name = "balance")
    val balance: Int?,
    @Json(name = "card_logo")
    val cardLogo: String?,
    @Json(name = "card_type")
    val cardType: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "valute_type")
    val valuteType: String
)