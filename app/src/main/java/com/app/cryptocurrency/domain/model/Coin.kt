package com.app.cryptocurrency.domain.model

data class Coin(
    val id: String,
    val name: String,
    val imageUrl: String,
    val symbol: String,
    val price: Double,
)
