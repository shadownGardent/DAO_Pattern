package com.example.dao_pattern.dao

interface Dao<T> {
    fun get(id : Int): T?
    fun getAll(): List<T>
    fun save(t: T)
    fun update(t: T, params: Array<String>)
    fun delete(t: T)
}