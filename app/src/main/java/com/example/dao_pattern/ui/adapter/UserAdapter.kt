package com.example.dao_pattern.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.dao_pattern.R
import com.example.dao_pattern.data.User
import com.example.dao_pattern.databinding.UserItemBinding
import com.example.dao_pattern.ui.MainActivity

class UserAdapter(
    private val users: List<User>,
    private val listener: MainActivity.OptionMenuClickListener
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(
        view: View,
        binding: UserItemBinding,
        listener: MainActivity.OptionMenuClickListener
    ) : RecyclerView.ViewHolder(view) {
        private val binding: UserItemBinding
        private var index: Int = 0
        private val listener: MainActivity.OptionMenuClickListener

        init {
            this.binding = binding
            this.listener = listener
            this.binding.imgOption.setOnClickListener { onOptionMenuClick() }
        }

        private fun onOptionMenuClick() {
            val popupMenu = PopupMenu(binding.root.context, binding.imgOption)
            popupMenu.inflate(R.menu.option_menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_item_update -> {
                        listener.update(index)
                        true
                    }

                    R.id.menu_item_delete -> {
                        listener.delete(index)
                        true
                    }

                    else -> false
                }
            }
            popupMenu.show()
        }

        fun bindData(user: User) {
            binding.textName.text = user.name
            binding.textEmail.text = user.email
        }

        fun updateIndex(index: Int) {
            this.index = index

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root, binding, listener)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(users[position])
        holder.updateIndex(position)
    }
}