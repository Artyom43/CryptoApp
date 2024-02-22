package ru.mullin.cryptoapp.domain

import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke() {
        coinRepository.loadData()
    }
}