package ru.mullin.cryptoapp.domain

import androidx.lifecycle.LiveData

class GetCoinInfoUseCase(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(fromSymbol: String): LiveData<CoinInfo> =
        coinRepository.getCoinInfo(fromSymbol)
}