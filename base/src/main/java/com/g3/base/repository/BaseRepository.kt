package com.g3.base.repository

import com.g3.base.uiState.UIState

abstract class BaseRepository {
    protected suspend fun <T> runTask(task: suspend () -> T): UIState<T> {
        return try {
            val result = task()
            return UIState.Success(result)
        } catch (e: Exception) {
            UIState.Error(e.localizedMessage)
        }
    }
}