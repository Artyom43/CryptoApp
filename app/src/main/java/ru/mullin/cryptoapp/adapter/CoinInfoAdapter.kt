package ru.mullin.cryptoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import ru.mullin.cryptoapp.R
import ru.mullin.cryptoapp.databinding.ItemCoinInfoBinding
import ru.mullin.cryptoapp.pojo.CoinPriceInfo

class CoinInfoAdapter(
    private val context: Context,
    private val onCoinClick: (CoinPriceInfo) -> Unit
) : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList: List<CoinPriceInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val itemBinding =
            ItemCoinInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CoinInfoViewHolder(itemBinding)
    }

    override fun getItemCount() = coinInfoList.size

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coinPriceInfo = coinInfoList[position]
        holder.bind(coinPriceInfo)
        holder.itemView.setOnClickListener {
            onCoinClick(coinPriceInfo)
        }
    }


    inner class CoinInfoViewHolder(
        private val itemViewBinding: ItemCoinInfoBinding
    ) : ViewHolder(itemViewBinding.root) {

        fun bind(coinPriceInfo: CoinPriceInfo) {
            with(itemViewBinding) {
                val symbolsTemplate = context.getString(R.string.symbols_template)
                val lastUpdateAtTemplate = context.getString(R.string.last_update_template)
                tvSymbols.text = String.format(
                    symbolsTemplate,
                    coinPriceInfo.fromSymbol,
                    coinPriceInfo.toSymbol
                )
                tvPrice.text = coinPriceInfo.price
                tvLastUpdate.text = String.format(
                    lastUpdateAtTemplate,
                    coinPriceInfo.getFormattedTime()
                )

                Picasso.get().load(coinPriceInfo.getFullImageUrl()).into(ivLogoCoin)
            }
        }
    }
}