package com.g3.birthdayapp.screens.usersList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.g3.birthdayapp.repositories.UsersRepository


private const val INC = "name,dob"

class UsersListFragmentViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    val users = usersRepository.getUsers(100, INC).asLiveData()

}