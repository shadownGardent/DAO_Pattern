package com.example.dao_pattern.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dao_pattern.databinding.ActivityMainBinding
import com.example.dao_pattern.newuser.NewUserActivity
import com.example.dao_pattern.ui.adapter.UserAdapter
import com.example.dao_pattern.ui.dialog.ConfirmationDialogFragment
import com.example.dao_pattern.ui.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = UserViewModel.getInstance()
        binding.recyclerUser.adapter = UserAdapter(viewModel.users.value!!,
        object: OptionMenuClickListener {
            override fun update(index: Int) {
                ConfirmationDialogFragment(Action.UPDATE, index)
                    .show(supportFragmentManager, "TAG")
            }

            override fun delete(index: Int) {
                ConfirmationDialogFragment(Action.DELETE, index)
                    .show(supportFragmentManager, "TAG")
            }
        })
        viewModel.users.observe(this) {
            binding.recyclerUser.adapter?.notifyDataSetChanged()
        }
        binding.recyclerUser.layoutManager = LinearLayoutManager(this)
        binding.fbtnAdd.setOnClickListener{
            Intent(this, NewUserActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    interface OptionMenuClickListener {
        fun update(index: Int)
        fun delete(index: Int)
    }
}