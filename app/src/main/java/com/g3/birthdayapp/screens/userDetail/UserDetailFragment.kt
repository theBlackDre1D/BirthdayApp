package com.g3.birthdayapp.screens.userDetail

import android.content.Context
import android.view.LayoutInflater
import androidx.navigation.fragment.navArgs
import com.g3.base.screens.fragment.BaseFragment
import com.g3.base.screens.fragment.BaseFragmentHandler
import com.g3.birthdayapp.databinding.UserDetailFragmentBinding


class UserDetailFragment : BaseFragment<UserDetailFragmentBinding, UserDetailFragmentHandler>() {

    private val args: UserDetailFragmentArgs by navArgs()
//    private val user: User
//        get() = args.user

    override fun setBinding(layoutInflater: LayoutInflater) = UserDetailFragmentBinding.inflate(layoutInflater)

    override fun onFragmentLoadingFinished(binding: UserDetailFragmentBinding, context: Context) {

    }
}

interface UserDetailFragmentHandler : BaseFragmentHandler {

}