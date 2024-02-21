package ru.mullin.cryptoapp.domain

import androidx.lifecycle.LiveData

class GetCoinInfoListUseCase(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(): LiveData<List<CoinInfo>> = coinRepository.getCoinInfoList()
}