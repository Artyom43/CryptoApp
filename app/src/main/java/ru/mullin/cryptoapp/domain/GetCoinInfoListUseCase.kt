package ru.mullin.cryptoapp.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetCoinInfoListUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(): LiveData<List<CoinInfo>> = coinRepository.getCoinInfoList()
}