package com.app.cryptocurrency.domain.repositry

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.cryptocurrency.domain.model.HistoryEntity

@Dao
interface HistoryDao {

    @Query("SELECT * FROM histories")
    suspend fun getHistories(): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: HistoryEntity)
}