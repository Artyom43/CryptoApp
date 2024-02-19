package ru.mullin.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import io.reactivex.disposables.CompositeDisposable

class CoinPriceListActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private val viewModel: CoinViewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        viewModel.priceList.observe(this) {
            Log.d("TEST_OF_LOADING_DATA", "Success in activity")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}