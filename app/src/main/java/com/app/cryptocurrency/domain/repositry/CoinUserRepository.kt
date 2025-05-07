package com.app.cryptocurrency.domain.repositry

import com.app.cryptocurrency.data.remote.dto.CoinDto
import com.app.cryptocurrency.domain.model.HistoryEntity
import com.app.cryptocurrency.domain.model.User


interface CoinUserRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(symbol: String): CoinDto

    suspend fun loginUser(email:String, password:String): User

    suspend fun signupUser(name: String ,email:String, password:String): User

    suspend fun buyCrypto(email:String, symbol:String, amount:Double, walletAddress:String)

    suspend fun sellCrypto(email:String, symbol:String, amount:Double, walletAddress:String)

    suspend fun addHistory(history: HistoryEntity)

    suspend fun getHistories() : List<HistoryEntity>
}