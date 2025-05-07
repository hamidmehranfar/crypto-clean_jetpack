package com.app.cryptocurrency.presentation.authentication

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cryptocurrency.common.Resource
import com.app.cryptocurrency.domain.use_case.login.LoginUseCase
import com.app.cryptocurrency.domain.use_case.signup.SignupUseCase
import com.app.cryptocurrency.presentation.home.UserViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignupLoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signupUseCase: SignupUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(SignupLoginState())
    val state: State<SignupLoginState> = _state

    fun login(email: String, password: String) {
        loginUseCase(email, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SignupLoginState(user = result.data)
                }
                is Resource.Error -> {
                    _state.value = SignupLoginState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = SignupLoginState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun signup(name: String, email: String, password: String) {
        signupUseCase(name = name, email = email, password =  password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SignupLoginState(user = result.data)
                }
                is Resource.Error -> {
                    _state.value = SignupLoginState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = SignupLoginState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}