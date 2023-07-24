package com.example.dao_pattern.dao

import com.example.dao_pattern.data.User

class UserDao private constructor() : Dao<User> {
    private val users: MutableList<User> = ArrayList()

    init {
        users.add(User("Hoa", "hoaxinhgai@xmail.com"))
        users.add(User("Mai", "maimezai@xmail.com"))
        users.add(User("Quang", "quanghacker@xmail.com"))
        users.add(User("Thắng", "thangsuperman@xmail.com"))
    }

    override fun get(id: Int): User? {
        return if (id < 0 || id >= users.size) null else users[id]
    }

    override fun getAll(): List<User> {
        return users
    }

    override fun delete(t: User) {
        users.remove(t)
    }

    override fun update(t: User, params: Array<String>) {
        t.name = params[0]
        t.email = params[1]
    }

    override fun save(t: User) {
        users.add(t)
    }

    companion object {
        private val userDao = UserDao()

        fun getInstance(): UserDao {
            return userDao
        }
    }
}
