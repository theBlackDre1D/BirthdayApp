package com.g3.birthdayapp.screens.usersList

import android.content.Context
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.g3.base.screens.fragment.BaseFragment
import com.g3.base.screens.fragment.BaseFragmentHandler
import com.g3.base.uiState.UIState
import com.g3.birthdayapp.R
import com.g3.birthdayapp.databinding.UsersListFragmentBinding
import com.g3.birthdayapp.models.User
import com.g3.birthdayapp.models.UsersResponse
import org.koin.android.viewmodel.ext.android.viewModel


class UsersListFragment : BaseFragment<UsersListFragmentBinding, UsersListFragmentHandler>(), UsersAdapterHandler {

    private val viewModel: UsersListFragmentViewModel by viewModel()
    private val usersAdapter: UsersAdapter by lazy { UsersAdapter(this) }

    override fun setBinding(layoutInflater: LayoutInflater) = UsersListFragmentBinding.inflate(layoutInflater)

    override fun onFragmentLoadingFinished(binding: UsersListFragmentBinding, context: Context) {
        binding.usersRV.adapter = usersAdapter

        setupListener()
        viewModel.getUsers()
    }

    private fun setupListener() {
        viewModel.users.observe(viewLifecycleOwner, { state ->
            when (state) {
                is UIState.Success -> handleUsersDownloadSuccess(state.value)
                is UIState.Loading -> showLoading(true)
                is UIState.Error -> handleUsersDownloadError()
            }
        })
    }

    private fun handleUsersDownloadSuccess(usersResponse: UsersResponse) {
        showLoading(false)
        val adapterItems = mutableListOf<UsersAdapterItem>()
        usersResponse.users.forEach { user ->
            val adapterItem = UsersAdapterItem(user)
            adapterItems.add(adapterItem)
        }
        usersAdapter.injectItems(adapterItems)
    }

    private fun handleUsersDownloadError() {
        showLoading(false)

        showSnackBar(binding.root, R.string.error__users_download)
    }

    private fun showLoading(show: Boolean) {
        binding.loadingV.isVisible = show
    }

    override fun onUserClick(user: User) = handler.fromUsersListToUserDetailScreen(user)
}

interface UsersListFragmentHandler : BaseFragmentHandler {
    fun fromUsersListToUserDetailScreen(user: User)
}