package com.g3.base.uiState


sealed class UIState<out T> {
    data class Error(val message: String?) : UIState<Nothing>() {
        constructor(exception: Exception) : this(exception.localizedMessage)
    }
    data class Success<T>(val value: T) : UIState<T>()
    object Loading : UIState<Nothing>()

    fun getValueOrNull(): T? {
        return when (this) {
            is Error, Loading -> null
            is Success -> this.value
        }
    }
}