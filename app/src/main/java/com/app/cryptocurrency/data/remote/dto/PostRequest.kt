package com.app.cryptocurrency.data.remote.dto

data class LoginRequest(
    val email: String,
    val password: String
)

data class SignupRequest(
    val name: String,
    val email: String,
    val password: String
)

data class BuySellRequest(
    val email: String,
    val amount: Double,
    val symbol: String,
    val wallet_address: String,
)