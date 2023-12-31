package com.example.xchat.presentation.utils

import androidx.navigation.NavDirections

class XchatDataClass {

    sealed class DataResult<out T> {
        data class Success<T>(val data: T?) : DataResult<T>()
        data class Error(val error: Throwable?) : DataResult<Nothing>()
        object Loading : DataResult<Nothing>()
    }

    sealed class ListResult<out E> {
        data class Success<E>(val list: List<E>) : ListResult<E>()
        data class Error(val error: String?) : ListResult<Nothing>()
        object Loading : ListResult<Nothing>()
    }

    sealed class AuthenticationState {
        object Success : AuthenticationState()
        data class Error(val error: String?) : AuthenticationState()
        object Loading : AuthenticationState()
    }

    sealed class GenericActions {
        data class Navigate(val destination: NavDirections) : GenericActions()
    }
}