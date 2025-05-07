package com.app.cryptocurrency.domain.repositry

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.cryptocurrency.domain.model.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}