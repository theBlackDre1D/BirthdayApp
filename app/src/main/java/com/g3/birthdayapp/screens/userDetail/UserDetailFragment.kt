package com.g3.birthdayapp.screens.userDetail

import android.content.Context
import android.view.LayoutInflater
import androidx.navigation.fragment.navArgs
import com.g3.base.extensions.onClick
import com.g3.base.screens.fragment.BaseFragment
import com.g3.base.screens.fragment.BaseFragmentHandler
import com.g3.birthdayapp.databinding.UserDetailFragmentBinding
import com.g3.birthdayapp.extensions.loadImageFromUrl
import com.g3.birthdayapp.models.User


class UserDetailFragment : BaseFragment<UserDetailFragmentBinding, UserDetailFragmentHandler>() {

    private val args: UserDetailFragmentArgs by navArgs()
    private val user: User
        get() = args.user

    override fun setBinding(layoutInflater: LayoutInflater) = UserDetailFragmentBinding.inflate(layoutInflater)

    override fun onFragmentLoadingFinished(binding: UserDetailFragmentBinding, context: Context) {
        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            userNameTV.text = user.name.first
            userAgeTV.text = user.dob.age.toString()

            user.picture?.thumbnail?.let { url ->
                this.userIV.loadImageFromUrl(url)
            }

            goBackB.onClick {
                handler.navigateBackFromUserDetailScreen()
            }
        }
    }
}

interface UserDetailFragmentHandler : BaseFragmentHandler {
    fun navigateBackFromUserDetailScreen()
}