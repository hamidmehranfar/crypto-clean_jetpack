package com.app.cryptocurrency.di

import android.content.Context
import androidx.room.Room
import com.app.cryptocurrency.common.Constants
import com.app.cryptocurrency.data.remote.AppApi
import com.app.cryptocurrency.data.repositry.CoinUserRepositoryImpl
import com.app.cryptocurrency.domain.repositry.CoinUserRepository
import com.app.cryptocurrency.domain.repositry.HistoryDao
import com.app.cryptocurrency.domain.repositry.HistoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): AppApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HistoryDatabase {
        return Room.databaseBuilder(
            context,
            HistoryDatabase::class.java,
            "history_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoinDao(db: HistoryDatabase): HistoryDao = db.historyDao()

    @Provides
    @Singleton
    fun provideCoinRepository(api: AppApi, dio : HistoryDao): CoinUserRepository {
        return CoinUserRepositoryImpl(api, dio)
    }
}