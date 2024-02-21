package ru.mullin.cryptoapp.data.repo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import ru.mullin.cryptoapp.data.database.AppDatabase
import ru.mullin.cryptoapp.data.mapper.CoinMapper
import ru.mullin.cryptoapp.data.workers.RefreshDataWorker
import ru.mullin.cryptoapp.domain.CoinInfo
import ru.mullin.cryptoapp.domain.CoinRepository

class CoinRepositoryImpl(
    private val application: Application
) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val mapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceList()) { listDbModel ->
            listDbModel.map { dbModel ->
                mapper.mapDbModelToEntity(dbModel)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}