package com.example.summary10.data.remote.util

sealed class Resource<out D : Any> {
    data class Success<out D : Any>(val data: D) : Resource<D>()
    data class Error<out D : Any>(val errorMessage: String) : Resource<D>()
}