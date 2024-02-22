package ru.mullin.cryptoapp

import android.app.Application
import ru.mullin.cryptoapp.di.DaggerAppComponent

class CoinApp : Application() {

    val component by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }
}