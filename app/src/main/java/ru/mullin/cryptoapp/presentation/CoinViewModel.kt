package ru.mullin.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.mullin.cryptoapp.data.repo.CoinRepositoryImpl
import ru.mullin.cryptoapp.domain.CoinInfo
import ru.mullin.cryptoapp.domain.CoinRepository
import ru.mullin.cryptoapp.domain.GetCoinInfoListUseCase
import ru.mullin.cryptoapp.domain.GetCoinInfoUseCase
import ru.mullin.cryptoapp.domain.LoadDataUseCase

class CoinViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application) as CoinRepository
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()


    init {
        loadDataUseCase()
    }

    fun getDetailInfo(fSym: String): LiveData<CoinInfo> {
        return getCoinInfoUseCase(fSym)
    }

}