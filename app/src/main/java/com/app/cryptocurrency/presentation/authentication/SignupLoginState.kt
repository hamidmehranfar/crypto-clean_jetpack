package com.app.cryptocurrency.presentation.authentication

import com.app.cryptocurrency.domain.model.User

data class SignupLoginState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
)
