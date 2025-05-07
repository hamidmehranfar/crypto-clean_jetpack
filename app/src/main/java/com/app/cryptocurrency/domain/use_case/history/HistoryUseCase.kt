package com.app.cryptocurrency.domain.use_case.history

import com.app.cryptocurrency.common.Resource
import com.app.cryptocurrency.domain.model.HistoryEntity
import com.app.cryptocurrency.domain.repositry.CoinUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HistoryUseCase @Inject constructor(
    private val repository: CoinUserRepository
) {
    operator fun invoke(): Flow<Resource<List<HistoryEntity>>> = flow {
        try {
            emit(Resource.Loading<List<HistoryEntity>>())
            val histories = repository.getHistories()
            emit(Resource.Success<List<HistoryEntity>>(histories))
        } catch(e: HttpException) {
            emit(Resource.Error<List<HistoryEntity>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<HistoryEntity>>("Couldn't reach server. Check your internet connection."))
        }
    }
}