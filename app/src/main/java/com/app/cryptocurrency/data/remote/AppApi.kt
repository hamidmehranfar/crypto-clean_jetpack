package com.app.cryptocurrency.data.remote


import com.app.cryptocurrency.data.remote.dto.BuySellRequest
import com.app.cryptocurrency.data.remote.dto.CoinDto
import com.app.cryptocurrency.data.remote.dto.LoginRequest
import com.app.cryptocurrency.data.remote.dto.SignupRequest
import com.app.cryptocurrency.domain.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface AppApi {

    @GET("/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/coin/{symbol}")
    suspend fun getCoinById(@Path("symbol") symbol: String): CoinDto

    @POST("/login")
    @Headers("Content-Type: application/json")
    suspend fun login(@Body request: LoginRequest): User

    @POST("/signup")
    @Headers("Content-Type: application/json")
    suspend fun signup(@Body request: SignupRequest): User

    @POST("/buy")
    suspend fun buyCrypto(@Body request: BuySellRequest)

    @POST("/sell")
    suspend fun sellCrypto(@Body request: BuySellRequest)
}