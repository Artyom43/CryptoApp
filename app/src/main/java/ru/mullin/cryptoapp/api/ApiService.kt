package ru.mullin.cryptoapp.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.mullin.cryptoapp.pojo.CoinInfoListOfData
import ru.mullin.cryptoapp.pojo.CoinPriceInfoRawData

interface ApiService {

    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = MY_API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tsym: String = CURRENCY,
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = MY_API_KEY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fsyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tsyms: String = CURRENCY,
    ): Single<CoinPriceInfoRawData>


    companion object {
        private const val MY_API_KEY =
            "dbbda3c6a3f85a83fc856c14557bdd0af955ddfc964885d60d942c0f2bef20ac"
        private const val CURRENCY = "USD"

        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
    }
}