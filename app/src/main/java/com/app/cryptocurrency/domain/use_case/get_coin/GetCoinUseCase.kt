package com.app.cryptocurrency.domain.use_case.get_coin

import com.app.cryptocurrency.common.Resource
import com.app.cryptocurrency.data.remote.dto.toCoin
import com.app.cryptocurrency.domain.model.Coin
import com.app.cryptocurrency.domain.repositry.CoinUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinUserRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<Coin>> = flow {
        try {
            emit(Resource.Loading<Coin>())
            val coin = repository.getCoinById(coinId).toCoin()
            emit(Resource.Success<Coin>(coin))
        } catch(e: HttpException) {
            emit(Resource.Error<Coin>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<Coin>("Couldn't reach server. Check your internet connection."))
        }
    }
}