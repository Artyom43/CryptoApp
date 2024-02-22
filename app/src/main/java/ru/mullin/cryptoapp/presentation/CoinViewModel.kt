package ru.mullin.cryptoapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.mullin.cryptoapp.domain.CoinInfo
import ru.mullin.cryptoapp.domain.CoinRepository
import ru.mullin.cryptoapp.domain.GetCoinInfoListUseCase
import ru.mullin.cryptoapp.domain.GetCoinInfoUseCase
import ru.mullin.cryptoapp.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase,
    private val repository: CoinRepository
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()


    init {
        loadDataUseCase()
    }

    fun getDetailInfo(fSym: String): LiveData<CoinInfo> {
        return getCoinInfoUseCase(fSym)
    }

}