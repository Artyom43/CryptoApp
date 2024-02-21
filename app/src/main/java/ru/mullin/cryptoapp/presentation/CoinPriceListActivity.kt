package ru.mullin.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.mullin.cryptoapp.presentation.adapter.CoinInfoAdapter
import ru.mullin.cryptoapp.databinding.ActivityCoinPriceListBinding

class CoinPriceListActivity : AppCompatActivity() {
    private val viewModel: CoinViewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }
    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this) {
            val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity, it.fromSymbol)
            startActivity(intent)
        }

        binding.rvCoinPriceList.adapter = adapter
        viewModel.priceList.observe(this) {
            adapter.coinInfoList = it
        }
    }
}