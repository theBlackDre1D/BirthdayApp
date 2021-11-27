package com.g3.birthdayapp.screens.usersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.g3.base.extensions.onClick
import com.g3.base.recyclerView.BaseAdapterItem
import com.g3.base.recyclerView.BaseRecyclerViewAdapter
import com.g3.base.recyclerView.BaseViewHolder
import com.g3.birthdayapp.databinding.UserItemBinding
import com.g3.birthdayapp.extensions.loadImageFromUrl
import com.g3.birthdayapp.models.User


class UsersAdapter(
    private val handler: UsersAdapterHandler
) : BaseRecyclerViewAdapter<UsersAdapterViewHolder, UsersAdapterItem>() {

    override fun createAdapterViewHolder(parent: ViewGroup, viewType: Int): UsersAdapterViewHolder {
        return UsersAdapterViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun bindAdapterViewHolder(holder: UsersAdapterViewHolder, adapterItem: UsersAdapterItem, position: Int) {
        holder.binding.apply {
            adapterItem.user.picture?.thumbnail?.let { url ->
                userIV.loadImageFromUrl(url)
            }
            userNameTV.text = "${adapterItem.user.name.first} ${adapterItem.user.name.last}"
            userBirthdateTV.text = adapterItem.user.dob.date

            root.onClick {
                handler.onUserClick(adapterItem.user)
            }
        }
    }

    fun injectItems(newAdapterItems: List<UsersAdapterItem>) {
        val diffResult = DiffUtil.calculateDiff(UsersAdapterDiffUtils(this.adapterItems, newAdapterItems))
        this.adapterItems.clear()
        this.adapterItems.addAll(newAdapterItems)
        diffResult.dispatchUpdatesTo(this)
    }

    class UsersAdapterDiffUtils(private val oldItems: List<UsersAdapterItem>, private val newItems: List<UsersAdapterItem>) : DiffUtil.Callback() {

        override fun getOldListSize() = oldItems.size
        override fun getNewListSize() = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldPositionItem = this.oldItems[oldItemPosition]
            val newPositionItem = this.newItems[newItemPosition]

            // better would be compare some id of user
            return oldPositionItem.user == newPositionItem.user
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldPositionItem = this.oldItems[oldItemPosition]
            val newPositionItem = this.newItems[newItemPosition]

            return oldPositionItem == newPositionItem
        }

    }
}

class UsersAdapterViewHolder(val binding: UserItemBinding) : BaseViewHolder(binding.root)
class UsersAdapterItem(val user: User) : BaseAdapterItem()

interface UsersAdapterHandler {
    fun onUserClick(user: User)
}