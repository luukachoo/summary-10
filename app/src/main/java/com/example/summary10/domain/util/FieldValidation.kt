package com.example.summary10.domain.util

import javax.inject.Inject

class FieldValidation @Inject constructor() {
    fun isAccountNumberValid(accountNumber: String): Boolean {
        return accountNumber.length == 23
    }

    fun isUserIdValid(personalNumber: String): Boolean {
        return personalNumber.length == 11
    }

    fun isPhoneNumberValid(phoneNumber: String): Boolean {
        val sanitizedPhoneNumber = phoneNumber.replace("-", "")
        return sanitizedPhoneNumber.length == 9
    }
}