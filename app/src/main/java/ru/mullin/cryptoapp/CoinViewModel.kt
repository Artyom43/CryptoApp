package ru.mullin.cryptoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.mullin.cryptoapp.api.ApiFactory
import ru.mullin.cryptoapp.database.AppDatabase
import ru.mullin.cryptoapp.pojo.CoinPriceInfo
import ru.mullin.cryptoapp.pojo.CoinPriceInfoRawData
import java.util.concurrent.TimeUnit

class CoinViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()

    init {
        loadData()
    }

    fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 10)
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(separator = ",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fsyms = it) }
            .map { getPriceListFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    db.coinPriceInfoDao().insertPriceList(it)
                    Log.d("TEST_OF_LOADING_DATA", "Success: $it")
                },
                {
                    Log.d("TEST_OF_LOADING_DATA", "Failure: ${it.message}")
                }
            )

        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData
    ): List<CoinPriceInfo> {
        val resultList = mutableListOf<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return resultList
        jsonObject.keySet().forEach { key ->
            val currencyJson = jsonObject.getAsJsonObject(key)
            currencyJson.keySet().forEach { currencyKey ->
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                resultList.add(priceInfo)
            }
        }
        return resultList
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}