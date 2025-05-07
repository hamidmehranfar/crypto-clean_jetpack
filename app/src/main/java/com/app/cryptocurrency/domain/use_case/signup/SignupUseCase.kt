package com.app.cryptocurrency.domain.use_case.signup

import com.app.cryptocurrency.common.Resource
import com.app.cryptocurrency.domain.model.User
import com.app.cryptocurrency.domain.repositry.CoinUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val repository: CoinUserRepository
) {
    operator fun invoke(email: String, password: String, name: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading<User>())
            val user = repository.signupUser(name ,email , password)
            emit(Resource.Success<User>(user))
        } catch(e: HttpException) {
            emit(Resource.Error<User>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<User>("Couldn't reach server. Check your internet connection."))
        }
    }
}