package ru.mullin.cryptoapp

import android.app.Application
import androidx.work.Configuration
import ru.mullin.cryptoapp.data.workers.RefreshDataWorkerFactory
import ru.mullin.cryptoapp.di.DaggerAppComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: RefreshDataWorkerFactory

    val component by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}