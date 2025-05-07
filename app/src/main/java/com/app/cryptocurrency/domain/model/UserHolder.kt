package com.app.cryptocurrency.domain.model

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