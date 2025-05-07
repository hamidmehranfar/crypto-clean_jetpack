package com.app.cryptocurrency.data.remote.dto


import com.app.cryptocurrency.domain.model.Coin
import com.google.gson.annotations.SerializedName

data class CoinDto(
    @SerializedName("Name")
    val id: String,
    @SerializedName("FullName")
    val name: String,
    @SerializedName("ImageUrl")
    val image: String,
    @SerializedName("Price")
    val price: Double,
)

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        symbol = id,
        imageUrl = "https://www.cryptocompare.com\\$image",
        price = String.format("%.3f", price).toDouble(),
    )
}