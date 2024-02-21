package ru.mullin.cryptoapp.domain

class LoadDataUseCase(
    private val coinRepository: CoinRepository
) {

    operator fun invoke() {
        coinRepository.loadData()
    }
}