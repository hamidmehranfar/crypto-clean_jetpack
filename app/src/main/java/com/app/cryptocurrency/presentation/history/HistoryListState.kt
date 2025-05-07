package com.app.cryptocurrency.presentation.history

import com.app.cryptocurrency.domain.model.HistoryEntity

data class HistoryListState(
    val isLoading: Boolean = false,
    val histories: List<HistoryEntity> = emptyList(),
    val error: String = ""
)