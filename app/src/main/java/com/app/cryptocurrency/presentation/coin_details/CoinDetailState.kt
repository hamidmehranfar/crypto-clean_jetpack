package com.app.cryptocurrency.presentation.coin_details

import com.app.cryptocurrency.domain.model.Coin

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: Coin? = null,
    val error: String = ""
)

data class CoinOperationState(
    val isLoading: Boolean = false,
    val success: Boolean? = null,
    val error: String = ""
)
