package com.g3.birthdayapp.screens.main

import android.view.LayoutInflater
import com.g3.base.screens.activity.BaseActivity
import com.g3.birthdayapp.R
import com.g3.birthdayapp.databinding.MainActivityBinding
import com.g3.birthdayapp.extensions.navigateSafe
import com.g3.birthdayapp.models.User
import com.g3.birthdayapp.screens.userDetail.UserDetailFragmentHandler
import com.g3.birthdayapp.screens.usersList.UsersListFragmentDirections
import com.g3.birthdayapp.screens.usersList.UsersListFragmentHandler


class MainActivity : BaseActivity<MainActivityBinding, Nothing>(), UsersListFragmentHandler,
    UserDetailFragmentHandler {

    override fun setNavigationGraph() = R.id.mainNavigationContainer
    override fun setBinding(layoutInflater: LayoutInflater) = MainActivityBinding.inflate(layoutInflater)
    override fun onActivityLoadingFinished(binding: MainActivityBinding) { }

    override fun fromUsersListToUserDetailScreen(user: User) {
        navController?.navigateSafe(UsersListFragmentDirections.actionUsersListToUserDetail(user))
    }

    override fun onGoBackClick() {
        navController?.navigateUp()
    }
}