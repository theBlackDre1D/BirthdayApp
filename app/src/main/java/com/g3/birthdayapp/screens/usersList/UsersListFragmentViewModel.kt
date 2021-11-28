package com.g3.birthdayapp.screens.usersList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.g3.base.extensions.doInCoroutine
import com.g3.base.uiState.UIState
import com.g3.birthdayapp.models.UsersResponse
import com.g3.birthdayapp.repositories.UsersRepository
import kotlinx.coroutines.flow.collect


private const val INC = "name,dob"

class UsersListFragmentViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    val users = MutableLiveData<UIState<UsersResponse>>()

    fun getUsers() {
        doInCoroutine {
            val result = usersRepository.getUsersSortedByDate(100, INC)
            result.collect {
                users.postValue(it)
            }
        }
    }
}