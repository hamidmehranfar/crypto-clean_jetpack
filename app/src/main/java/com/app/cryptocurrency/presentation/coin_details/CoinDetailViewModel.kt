package com.app.cryptocurrency.presentation.coin_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cryptocurrency.common.Constants
import com.app.cryptocurrency.common.Resource
import com.app.cryptocurrency.data.remote.dto.TabType
import com.app.cryptocurrency.domain.model.Coin
import com.app.cryptocurrency.domain.use_case.buy_sell_coin.BuySellUseCase
import com.app.cryptocurrency.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val buySellUseCase: BuySellUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    private val _operationState = mutableStateOf(CoinOperationState())
    val operationState: State<CoinOperationState> = _operationState

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun buySellRequest(email:String, amount:Double, coin: Coin,
                              address: String, type:TabType) {
        buySellUseCase(email = email, amount = amount , coin= coin,
            walletAddress = address, type = type).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _operationState.value = CoinOperationState(success = result.data)
                }
                is Resource.Error -> {
                    _operationState.value = CoinOperationState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _operationState.value = CoinOperationState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}