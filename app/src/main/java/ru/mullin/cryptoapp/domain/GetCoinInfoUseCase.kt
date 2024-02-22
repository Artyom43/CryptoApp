package ru.mullin.cryptoapp.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(fromSymbol: String): LiveData<CoinInfo> =
        coinRepository.getCoinInfo(fromSymbol)
}