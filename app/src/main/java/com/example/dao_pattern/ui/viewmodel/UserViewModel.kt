package com.example.dao_pattern.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dao_pattern.dao.UserDao
import com.example.dao_pattern.data.User

// _users: local >< users: global
class UserViewModel private constructor() : ViewModel() {
    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    private val userDAO: UserDao = UserDao.getInstance()
    val users: LiveData<List<User>> = _users

    init {
        _users.value = userDAO.getAll()
    }

    fun addNewUser(name: String, email: String) {
        userDAO.save(User(name, email))
        _users.value = userDAO.getAll()
    }

    fun removeUser(index: Int) {
        val user = userDAO.get(index)
        userDAO.delete(user!!)
        _users.value = userDAO.getAll()
    }

    fun updateUser(index: Int, name: String, email: String) {
        val user = userDAO.get(index)
        userDAO.update(user!!, arrayOf(name, email))
        _users.value = userDAO.getAll()
    }

    companion object {
        private val viewModel = UserViewModel()

        fun getInstance(): UserViewModel {
            return viewModel
        }

    }
}