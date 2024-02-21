package ru.mullin.cryptoapp.data.mapper

import com.google.gson.Gson
import ru.mullin.cryptoapp.data.database.CoinInfoDbModel
import ru.mullin.cryptoapp.data.network.model.CoinInfoDto
import ru.mullin.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import ru.mullin.cryptoapp.data.network.model.CoinNamesListDto
import ru.mullin.cryptoapp.domain.CoinInfo

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinInfoDto) = with(dto) {
        CoinInfoDbModel(
            fromSymbol = fromSymbol,
            toSymbol = toSymbol,
            price = price,
            lastUpdate = lastUpdate,
            highDay = highDay,
            lowDay = lowDay,
            lastMarket = lastMarket,
            imageUrl = imageUrl
        )
    }

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val resultList = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return resultList
        jsonObject.keySet().forEach { key ->
            val currencyJson = jsonObject.getAsJsonObject(key)
            currencyJson.keySet().forEach { currencyKey ->
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                resultList.add(priceInfo)
            }
        }
        return resultList
    }

    fun mapNamesListToString(namesList: CoinNamesListDto): String =
        namesList.names?.map { it.coinName?.name }?.joinToString(separator = ",") ?: ""


    fun mapDbModelToEntity(dbModel: CoinInfoDbModel) = with(dbModel) {
        CoinInfo(
            fromSymbol = fromSymbol,
            toSymbol = toSymbol,
            price = price,
            lastUpdate = lastUpdate,
            highDay = highDay,
            lowDay = lowDay,
            lastMarket = lastMarket,
            imageUrl = imageUrl
        )
    }


}