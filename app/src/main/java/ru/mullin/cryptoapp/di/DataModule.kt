package ru.mullin.cryptoapp.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.mullin.cryptoapp.data.database.AppDatabase
import ru.mullin.cryptoapp.data.database.CoinInfoDao
import ru.mullin.cryptoapp.data.repo.CoinRepositoryImpl
import ru.mullin.cryptoapp.domain.CoinRepository

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

    }
}