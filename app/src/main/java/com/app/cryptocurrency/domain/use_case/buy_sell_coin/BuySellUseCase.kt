package com.app.cryptocurrency.domain.use_case.buy_sell_coin

import android.util.Log
import com.app.cryptocurrency.common.Resource
import com.app.cryptocurrency.data.remote.dto.TabType
import com.app.cryptocurrency.domain.model.Coin
import com.app.cryptocurrency.domain.model.HistoryEntity
import com.app.cryptocurrency.domain.repositry.CoinUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BuySellUseCase @Inject constructor(
    private val repository: CoinUserRepository
) {
    operator fun invoke(email:String, amount: Double, coin: Coin,
                        walletAddress:String, type:TabType): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            if(type == TabType.Buy){
                repository.buyCrypto(email= email, amount = amount,
                    symbol = coin.symbol, walletAddress = walletAddress)
            }
            else{
                repository.sellCrypto(email= email, amount = amount,
                    symbol = coin.symbol, walletAddress = walletAddress)
            }
            repository.addHistory(HistoryEntity(time = System.currentTimeMillis(), name = coin.name ,
                price = coin.price, amount = amount, type = type.name, image = coin.imageUrl))

            emit(Resource.Success<Boolean>(true))
        } catch(e: HttpException) {
            Log.e("HamidTest", "error : ${e.message()}")
            emit(Resource.Error<Boolean>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<Boolean>("Couldn't reach server. Check your internet connection."))
        }
    }
}