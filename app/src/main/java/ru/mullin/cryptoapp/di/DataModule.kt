package ru.mullin.cryptoapp.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.mullin.cryptoapp.data.database.AppDatabase
import ru.mullin.cryptoapp.data.database.CoinInfoDao
import ru.mullin.cryptoapp.data.network.ApiFactory
import ru.mullin.cryptoapp.data.network.ApiService
import ru.mullin.cryptoapp.data.repo.CoinRepositoryImpl
import ru.mullin.cryptoapp.domain.CoinRepository

@Module
interface DataModule {

    @Binds
    @AppScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        @AppScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @Provides
        @AppScope
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

    }
}