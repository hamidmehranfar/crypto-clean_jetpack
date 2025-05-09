package com.app.cryptocurrency.data.remote.dto

import com.app.cryptocurrency.domain.model.User

object UserHolder {
    private var currentUser: User? = null

    fun setUser(user: User) {
        currentUser = user
    }

    fun getUser(): User? = currentUser

    fun clearUser() {
        currentUser = null
    }
}