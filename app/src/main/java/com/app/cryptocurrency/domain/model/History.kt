package com.app.cryptocurrency.domain.model

import androidx.room.Entity

@Entity(
    tableName = "histories",
    primaryKeys = ["time", "name"]
)
data class HistoryEntity(
    val time: Long,
    val image: String,
    val name: String,
    val type: String,
    val amount: Double,
    val price: Double
)