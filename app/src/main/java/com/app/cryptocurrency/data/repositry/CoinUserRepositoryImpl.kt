package com.app.cryptocurrency.data.repositry

import android.util.Log
import com.app.cryptocurrency.data.remote.AppApi
import com.app.cryptocurrency.data.remote.dto.BuySellRequest
import com.app.cryptocurrency.data.remote.dto.CoinDto
import com.app.cryptocurrency.data.remote.dto.LoginRequest
import com.app.cryptocurrency.data.remote.dto.SignupRequest
import com.app.cryptocurrency.domain.model.HistoryEntity
import com.app.cryptocurrency.domain.model.User
import com.app.cryptocurrency.domain.repositry.CoinUserRepository
import com.app.cryptocurrency.domain.repositry.HistoryDao
import javax.inject.Inject

class CoinUserRepositoryImpl @Inject constructor(
    private val api: AppApi,
    private val dao: HistoryDao
) : CoinUserRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(symbol: String): CoinDto {
        return api.getCoinById(symbol)
    }

    override suspend fun loginUser(email: String, password: String): User {
        return api.login(LoginRequest(email = email, password = password))
    }

    override suspend fun signupUser(name: String, email: String, password: String): User {
        return api.signup(SignupRequest(name, email, password))
    }

    override suspend fun buyCrypto(email:String, symbol:String, amount:Double, walletAddress:String){
        Log.e("HamidTest", "data are here")
        api.buyCrypto(BuySellRequest(email= email,amount= amount,
            symbol= symbol, wallet_address = walletAddress))
    }

    override suspend fun sellCrypto(email:String, symbol:String, amount:Double, walletAddress:String){
        api.sellCrypto(BuySellRequest(email= email,amount= amount,
            symbol= symbol, wallet_address = walletAddress))
    }

    override suspend fun addHistory(history: HistoryEntity){
        dao.insertHistory(history)
    }

    override suspend fun getHistories() : List<HistoryEntity>{
        return dao.getHistories()
    }
}