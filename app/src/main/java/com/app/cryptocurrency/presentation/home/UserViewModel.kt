package com.app.cryptocurrency.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.app.cryptocurrency.domain.model.User
import com.app.cryptocurrency.data.remote.dto.UserHolder
import javax.inject.Inject

class UserViewModel @Inject constructor() : ViewModel() {
    val user = mutableStateOf<User?>(null)

    init {
        // Load user from singleton when ViewModel starts
        user.value = UserHolder.getUser()
    }

    fun setUser(user: User) {
        this.user.value = user
        UserHolder.setUser(user)
    }

    fun clearUser() {
        user.value = null
        UserHolder.clearUser()
    }
}