package com.app.cryptocurrency.presentation.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.cryptocurrency.common.Resource
import com.app.cryptocurrency.domain.use_case.history.HistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HistoryListViewModel @Inject constructor(
    private val historyUseCase: HistoryUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HistoryListState())
    val state: State<HistoryListState> = _state

    init {
        getHistory()
    }

    fun getHistory() {
        historyUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = HistoryListState(histories = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = HistoryListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = HistoryListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}