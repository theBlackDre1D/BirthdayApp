package com.g3.birthdayapp.repositories

import com.g3.base.uiState.UIState
import com.g3.birthdayapp.api.RandomUserApi
import com.g3.birthdayapp.models.UsersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsersRepository(private val randomUserApi: RandomUserApi) {

    suspend fun getUsers(usersCount: Int, inc: String): Flow<UIState<UsersResponse>> {
        return flow {
            try {
                emit(UIState.Loading)

                val result = randomUserApi.getRandomUsers(usersCount, inc)
                emit(UIState.Success(result))
            } catch (e: Exception) {
                emit(UIState.Error(e.localizedMessage))
            }
        }
    }
}