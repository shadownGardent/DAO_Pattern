package com.example.dao_pattern.newuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.dao_pattern.R
import com.example.dao_pattern.databinding.ActivityNewUserBinding
import com.example.dao_pattern.ui.viewmodel.UserViewModel

class NewUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCancel.setOnClickListener { finish() }
        binding.btnAdd.setOnClickListener {
            val name = binding.txtUserName.text.toString()
            val email = binding.txtUserEmail.text.toString()
            val viewModel = UserViewModel.getInstance()
            viewModel.addNewUser(name, email)
            finish()
        }
    }
}