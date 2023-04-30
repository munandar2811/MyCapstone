package com.example.core.data.remote

sealed class ApiResponse<out R> {
    object Loading:ApiResponse<Nothing>()
    data class Success<out T > (val data:T):ApiResponse<T>()
    object Empty:ApiResponse<Nothing>()
    data class Error(val message:String):ApiResponse<Nothing>()
}