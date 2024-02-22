package ru.mullin.cryptoapp.data.mapper

import com.google.gson.Gson
import ru.mullin.cryptoapp.data.database.CoinInfoDbModel
import ru.mullin.cryptoapp.data.network.model.CoinInfoDto
import ru.mullin.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import ru.mullin.cryptoapp.data.network.model.CoinNamesListDto
import ru.mullin.cryptoapp.domain.CoinInfo
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class CoinMapper @Inject constructor() {

    fun mapDtoToDbModel(dto: CoinInfoDto) = with(dto) {
        CoinInfoDbModel(
            fromSymbol = fromSymbol,
            toSymbol = toSymbol,
            price = price,
            lastUpdate = lastUpdate,
            highDay = highDay,
            lowDay = lowDay,
            lastMarket = lastMarket,
            imageUrl = BASE_IMAGE_URL + imageUrl
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
            lastUpdate = convertTimeStampToTime(lastUpdate),
            highDay = highDay,
            lowDay = lowDay,
            lastMarket = lastMarket,
            imageUrl = imageUrl
        )
    }

    private fun convertTimeStampToTime(timeStamp: Long?): String {
        timeStamp?.let {
            val stamp = Timestamp(timeStamp * 1000)
            val date = Date(stamp.time)
            val pattern = "HH:mm:ss"
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(date)
        }
        return ""
    }

    companion object {
        const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }

}